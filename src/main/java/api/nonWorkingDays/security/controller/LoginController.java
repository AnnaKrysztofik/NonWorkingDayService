package api.nonWorkingDays.security.controller;

import api.nonWorkingDays.security.mappers.MapperForUsers;
import api.nonWorkingDays.security.model.LoginCredentials;
import api.nonWorkingDays.security.model.LoginResponseDto;
import api.nonWorkingDays.security.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
//    private UserService userService;
//
//   public LoginController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {


//    @PostMapping("/login")
//    public LoginResponseDto login(@RequestBody LoginCredentials credentials) {
//        if(!userService.canUserLogin(MapperForUsers.map(credentials)))
//            return new LoginResponseDto(false, "Logowanie nie powiodlo sie");
//
//        return new LoginResponseDto(true, "Witaj!");
    }
}
