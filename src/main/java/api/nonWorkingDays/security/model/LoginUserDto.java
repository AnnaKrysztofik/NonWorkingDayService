package api.nonWorkingDays.security.model;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;
    private String password;
}
