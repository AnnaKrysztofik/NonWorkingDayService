package api.nonWorkingDays.security.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AppUser {

    @Id
    private String id;

    private String username;
    private String password;
    private String role;
    private boolean isEnabled;
}
