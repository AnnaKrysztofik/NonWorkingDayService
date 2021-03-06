package api.nonWorkingDays.security.service;

import lombok.val;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import api.nonWorkingDays.security.entity.AppUser;
import api.nonWorkingDays.security.model.RegisterToken;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.security.repo.AppUserRepo;
import api.nonWorkingDays.security.repo.TokenRepo;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private MailService mailService;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    private Environment environment;

    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService, Environment environment) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
        this.environment = environment;
    }

    @PostConstruct
    public void postConstruct()
    {
        if(!Arrays.stream(environment.getActiveProfiles()).collect(Collectors.toList()).contains("local"))
            return;

        val appUserJanusz = new AppUser();

        appUserJanusz.setId(UUID.randomUUID().toString());
        appUserJanusz.setUsername("janusz");
        appUserJanusz.setPassword(new BCryptPasswordEncoder().encode("janusz"));
        appUserJanusz.setRole("ROLE_ADMIN");
        appUserJanusz.setEnabled(true);

        appUserRepo.save(appUserJanusz);
    }

    public void registerUser(RegisterUserDto regUser) {

        val userId = UUID.randomUUID().toString();
        val appUser = new AppUser();

        appUser.setId(userId);
        appUser.setUsername(regUser.getEmail());
        appUser.setPassword(passwordEncoder.encode(regUser.getPassword()));
        appUser.setRole("ROLE_USER");

        appUserRepo.save(appUser);

        sendToken(userId,  regUser.getEmail());
    }

    private void sendToken(String userId, String email) {
        val tokenValue = UUID.randomUUID().toString();

        val token = new RegisterToken();
        token.setValue(tokenValue);
        token.setUserId(userId);
        tokenRepo.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(email, "Potwierdź logowanie", "Wpisz poniższy adres do przeglądarki: \n" + url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enableUser(String value) {
        val byValue = tokenRepo.findByValue(value);
        val appUser = appUserRepo.findById(byValue.getUserId());

        if (appUser.isEmpty())
            return;

        appUser.get().setEnabled(true);

        appUserRepo.save(appUser.get());

    }
}
