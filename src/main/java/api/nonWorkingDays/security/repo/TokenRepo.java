package api.nonWorkingDays.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import api.nonWorkingDays.security.model.RegisterToken;

@Repository
public interface TokenRepo extends JpaRepository<RegisterToken, Long> {

    RegisterToken findByValue(String value);
}
