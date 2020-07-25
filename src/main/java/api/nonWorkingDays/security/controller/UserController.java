package api.nonWorkingDays.security.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.security.service.UserService;

import java.security.Principal;
import java.util.Collection;
@RestController
//@Controller
public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

  //   for REST value returned
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

//    @GetMapping("/hello")
//    public String hello(Principal principal, Model model) {
//        model.addAttribute("name", principal.getName());
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        model.addAttribute("authorities", authorities);
//        model.addAttribute("details", details);
//        return "hello";
//    }

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
