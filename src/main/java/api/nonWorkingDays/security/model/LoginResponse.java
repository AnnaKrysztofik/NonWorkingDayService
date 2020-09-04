package api.nonWorkingDays.security.model;

import lombok.Data;

@Data
public class LoginResponse {

    private final String jwt;

    public LoginResponse(String jwt)
    {
        this.jwt = jwt;
    }
}
