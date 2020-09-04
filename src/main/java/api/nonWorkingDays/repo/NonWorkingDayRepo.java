package api.nonWorkingDays.repo;

import api.nonWorkingDays.entity.NonWorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NonWorkingDayRepo extends JpaRepository<NonWorkingDay, Long> {

    List<NonWorkingDay> findAllByCountryCode(String countryCode);
    List<NonWorkingDay> findByDateBetween(LocalDate date1, LocalDate date2);
    List<NonWorkingDay> findByCountryCodeAndDescriptionAndDate(String countryCode, String description, LocalDate date);
    List<NonWorkingDay> findAll();
}
