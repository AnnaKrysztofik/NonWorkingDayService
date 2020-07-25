package api.nonWorkingDays.security.model;

import lombok.Value;

@Value
public class RegisterUserDto {

    private String email;
    private String password;
}
