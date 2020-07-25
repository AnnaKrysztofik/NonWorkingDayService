package api.nonWorkingDays.security.service;

import lombok.val;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import api.nonWorkingDays.security.model.AppUser;
import api.nonWorkingDays.security.model.RegisterToken;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.security.repo.AppUserRepo;
import api.nonWorkingDays.security.repo.TokenRepo;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private MailService mailService;
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    @PostConstruct
    public void postConstruct()
    {
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
        appUser.setRole("USER");

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
