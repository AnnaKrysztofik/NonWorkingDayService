package api.nonWorkingDays;

import api.nonWorkingDays.entity.NonWorkingDay;
import api.nonWorkingDays.repo.NonWorkingDayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    private NonWorkingDayRepo nonWorkingDayRepo;
    private SupplyDatabaseInterface supplyDatabase;

    @Autowired
    public DataService(NonWorkingDayRepo nonWorkingDayRepo, SupplyDatabase supplyDatabase) {
        this.nonWorkingDayRepo = nonWorkingDayRepo;
        this.supplyDatabase = supplyDatabase;
    }

    @PostConstruct
    public void postConstruct(){

        supplyDatabase.supply();
    }

    public List<NonWorkingDay> findAllDates(String countryCode){

        return new ArrayList<>(nonWorkingDayRepo.findAllByCountryCode(countryCode));
    }

    public void addNewDate(NonWorkingDay nonWorkingDay)
    {
        nonWorkingDay.setDayOfWeek(nonWorkingDay.getDate().getDayOfWeek().toString());
        nonWorkingDayRepo.save(nonWorkingDay);
    }


    public void deleteDate(NonWorkingDay nonWorkingDay) {

        nonWorkingDayRepo.delete(nonWorkingDay);
    }

    public List<NonWorkingDay> findInYear(String year) {

        LocalDate localDate1;
        LocalDate localDate2;

        try {
            localDate1 = LocalDate.of(Integer.parseInt(year), 1, 1);
            localDate2 = LocalDate.of(Integer.parseInt(year), 12, 31);
        } catch (NumberFormatException ex) {
            return Collections.emptyList();
        }

        return nonWorkingDayRepo.findByDateBetween(localDate1, localDate2);
    }

    public List<NonWorkingDay> findInPeriod(String dateFrom, String dateTo) {

        LocalDate localDate1 = LocalDate.parse(dateFrom);
        LocalDate localDate2 = LocalDate.parse(dateTo);

        return nonWorkingDayRepo.findByDateBetween(localDate1, localDate2);
    }
}
