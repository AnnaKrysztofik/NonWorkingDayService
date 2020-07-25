package api.nonWorkingDays.security.model;

import lombok.Value;

@Value
public class LoginResponseDto {
    private boolean logged;
    private String msg;
}
