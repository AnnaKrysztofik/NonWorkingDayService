package api.nonWorkingDays.security.model;

import lombok.Value;

@Value
public class RegisterUserDto {

    String email;
    String password;
}
