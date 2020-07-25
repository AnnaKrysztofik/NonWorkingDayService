package api.nonWorkingDays.security.service;

import lombok.val;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import api.nonWorkingDays.security.model.AppUser;
import api.nonWorkingDays.security.model.LoginUserDto;
import api.nonWorkingDays.security.model.RegisterToken;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.security.repo.AppUserRepo;
import api.nonWorkingDays.security.repo.TokenRepo;
import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private TokenRepo tokenRepo;
    private MailService mailService;
    private AppUserRepo appUserRepo;
 //   private PasswordEncoder passwordEncoder;

    public UserService(AppUserRepo appUserRepo, /*PasswordEncoder passwordEncoder,*/ TokenRepo tokenRepo, MailService mailService) {
        this.appUserRepo = appUserRepo;
   //     this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    public void registerUser(RegisterUserDto regUser) {

        val userId = UUID.randomUUID().toString();
        val appUser = new AppUser();

        appUser.setId(userId);
        appUser.setUsername(regUser.getEmail());
       // appUser.setPassword(passwordEncoder.encode(regUser.getPassword()));
        appUser.setPassword("{bcrypt}"+ new BCryptPasswordEncoder().encode(regUser.getPassword()));
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

    private boolean isEnabled(AppUser user){
        return  false;
    }


    public void enableUser(String value) {
        val byValue = tokenRepo.findByValue(value);
        val appUser = appUserRepo.findById(byValue.getUserId());

        if (appUser.isEmpty())
            return;

        appUser.get().setEnabled(true);

        appUserRepo.save(appUser.get());

    }

    public boolean canUserLogin(LoginUserDto loginUserDto) {

        val user =  appUserRepo.findByUsername(loginUserDto.getEmail());

        // pobrac usera z bazy
        if (user.isEmpty())
            return false;

        // sprawdzic czy jest enabled
        if (!user.get().isEnabled())
            return false;

        // porownac hasla
      //  if (!passwordEncoder.matches(loginUserDto.getPassword(), user.get().getPassword()))
        if (!loginUserDto.getPassword().equals(user.get().getPassword()))
          return false;

        return true;
    }
}
