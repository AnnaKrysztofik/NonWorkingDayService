package api.nonWorkingDays.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import api.nonWorkingDays.security.model.AppUser;
import api.nonWorkingDays.security.repo.AppUserRepo;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Profile("!dev")
//@Configuration
//@Component

public class Start {
    private AppUserRepo appUserRepo;


    public Start (AppUserRepo appUserRepo/*, PasswordEncoder passwordEncoder*/) {
        this.appUserRepo = appUserRepo;


        AppUser appUserJanusz = new AppUser();
        appUserJanusz.setId(UUID.randomUUID().toString());
        appUserJanusz.setUsername("admin");
     //   appUserJanusz.setPassword(passwordEncoder.encode("admin"));
        appUserJanusz.setPassword("admin");
      //  appUserJanusz.setPassword(new BCryptPasswordEncoder().encode("admin"));
        appUserJanusz.setRole("ADMIN");
        appUserJanusz.setEnabled(true);

        AppUser appUserBogdan = new AppUser();
        appUserBogdan.setUsername("Uzytkownik");
        //appUserBogdan.setPassword(passwordEncoder.encode("Uzytkownik123"));
        //appUserBogdan.setPassword(new BCryptPasswordEncoder().encode("Uzytkownik123"));
        appUserBogdan.setPassword("Uzytkownik123");
        appUserBogdan.setRole("USER");
        appUserBogdan.setEnabled(true);

        appUserRepo.save(appUserBogdan);
        appUserRepo.save(appUserJanusz);
    }
}
