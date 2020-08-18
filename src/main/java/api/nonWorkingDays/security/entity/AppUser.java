package api.nonWorkingDays.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="app_user")
public class AppUser {

    @Id
    private String id;

    private String username;
    private String password;
    private String role;
    private boolean isEnabled;
}
