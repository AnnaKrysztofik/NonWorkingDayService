package api.nonWorkingDays.security.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import api.nonWorkingDays.security.entity.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);
}
