package api.nonWorkingDays.security.service;

import api.nonWorkingDays.dto.NonWorkingDayDto;
import api.nonWorkingDays.security.entity.AppUser;
import api.nonWorkingDays.security.model.RegisterToken;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.security.repo.AppUserRepo;
import api.nonWorkingDays.security.repo.TokenRepo;
import api.nonWorkingDays.service.DataService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Test
    void registerUser() {
        // Arrange
        RegisterUserDto regUser = new RegisterUserDto("krysztofikanna@gmail.com", "haslo");

        // Act
        userService.registerUser(regUser);
        // Assert
        assertEquals("krysztofikanna@gmail.com", appUserRepo.findByUsername("krysztofikanna@gmail.com").get().getUsername());
    }

    @Test
    void enableUser() {
        // Arrange
        RegisterToken registerToken = new RegisterToken();
        registerToken.setValue("1234");
        registerToken.setUserId("1");
        tokenRepo.save(registerToken);

        AppUser appUser = new AppUser();
        appUser.setId("1");
        appUser.setUsername("Ania");
        appUser.setEnabled(false);
        appUserRepo.save(appUser);

        // Act
       userService.enableUser("1234");

        // Assert
        assertTrue(appUserRepo.findById("1").get().isEnabled());
        appUserRepo.deleteAll();
        tokenRepo.deleteAll();
    }
    @Test
    void enableUser_Empty() {
        // Arrange
        RegisterToken registerToken = new RegisterToken();
        registerToken.setValue("1234");
        registerToken.setUserId("1");
        tokenRepo.save(registerToken);

        AppUser appUser = new AppUser();
        appUser.setId("2");
        appUser.setUsername("Ania");
        appUser.setEnabled(false);
        appUserRepo.save(appUser);

        // Act
        userService.enableUser("1234");

        // Assert
        assertFalse(appUserRepo.findById("2").get().isEnabled());
        appUserRepo.deleteAll();
        tokenRepo.deleteAll();
    }
}