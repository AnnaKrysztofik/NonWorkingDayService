package api.nonWorkingDays.security.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="app_user")
@Getter
public class AppUser {

    @Id
    private String id;

    private String username;
    private String password;
    private String role;
    private boolean isEnabled;

//    public boolean getEnabled() {
//        return isEnabled;
//    }
}
