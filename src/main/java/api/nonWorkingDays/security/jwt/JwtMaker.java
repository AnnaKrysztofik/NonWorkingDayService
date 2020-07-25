package api.nonWorkingDays.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import api.nonWorkingDays.security.model.LoginResponseDto;
import api.nonWorkingDays.security.model.LoginUserDto;
import api.nonWorkingDays.security.service.UserService;

import java.util.Date;

//@RestController
public class JwtMaker {

    private UserService userService;


    public JwtMaker(UserService userService) {
        this.userService = userService;
    }

    //@PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginUserDto user){

        if(!userService.canUserLogin(user))
           return new LoginResponseDto(false, "Logowanie nie powiodło się");

        long currentTimeMillis = System.currentTimeMillis();
       String code = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles","user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 200000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
        System.out.println(code);

        return new LoginResponseDto(true, code);
    }
}
