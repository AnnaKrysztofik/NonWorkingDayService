package api.nonWorkingDays.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.security.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserDto regUser) {
        userService.registerUser(regUser);
        return "Rejestracja w toku. Potwierdź link w mailu";
    }

    @GetMapping("/token")
    public String signUp(@RequestParam String value) {
        userService.enableUser(value);
        return "Rejestracja się powiodła. Możesz się zalogować";
    }
}
