package api.nonWorkingDays.security.controller;

import api.nonWorkingDays.security.jwt.JwtUtil;
import api.nonWorkingDays.security.model.LoginRequest;
import api.nonWorkingDays.security.model.LoginResponse;
import api.nonWorkingDays.security.model.MyUserDetails;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        val responseHeaders = new HttpHeaders();
        val authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        final MyUserDetails userDetails = (MyUserDetails) userDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        val loginResponse = new LoginResponse(jwt);

        return new ResponseEntity<>(loginResponse, responseHeaders, HttpStatus.OK);
    }
}
