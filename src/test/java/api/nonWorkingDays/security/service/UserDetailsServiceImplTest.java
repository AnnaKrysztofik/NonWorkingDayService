package api.nonWorkingDays.security.service;

import api.nonWorkingDays.security.entity.AppUser;
import api.nonWorkingDays.security.model.MyUserDetails;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.security.repo.AppUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AppUserRepo appUserRepo;

    @Test
    void loadUserByUsername() {
        // Arrange
        AppUser appUser = new AppUser();
        appUser.setUsername("ania");
        appUser.setPassword("haslo");
        appUser.setEnabled(true);
        appUser.setRole("ROLE_USER");
        appUser.setId("1");
        appUserRepo.save(appUser);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername("ania");

        // Assert
        assertEquals(true, userDetails.isEnabled());
        assertEquals("ROLE_USER", userDetails.getAuthorities().stream().findFirst().get().toString());
        assertEquals("haslo", userDetails.getPassword());

    }
}