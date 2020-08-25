package api.nonWorkingDays.service;

import api.nonWorkingDays.dto.NonWorkingDayDto;
import api.nonWorkingDays.mappers.Mapper;
import api.nonWorkingDays.mappers.MapperDto;
import api.nonWorkingDays.repo.NonWorkingDayRepo;
import api.nonWorkingDays.supply.SupplyDatabaseInterface;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    private NonWorkingDayRepo nonWorkingDayRepo;
    @Qualifier("supplyDatabaseHardcoded")
    private SupplyDatabaseInterface supplyDatabase;
    private Environment environment;

    @Autowired
    public DataService(NonWorkingDayRepo nonWorkingDayRepo, SupplyDatabaseInterface supplyDatabase, Environment environment) {
        this.nonWorkingDayRepo = nonWorkingDayRepo;
        this.supplyDatabase = supplyDatabase;
        this.environment = environment;
    }

    @PostConstruct
    public void postConstruct() {
        if (List.of(environment.getActiveProfiles()).contains("local"))
            supplyDatabase.supply();
    }

    public List<NonWorkingDayDto> findAllDays(){

        return nonWorkingDayRepo.findAll().stream().map(n->Mapper.map(n)).collect(Collectors.toList());
    }


    public List<NonWorkingDayDto> findAllDates(String countryCode){

        return nonWorkingDayRepo.findAllByCountryCode(countryCode).stream().map(m-> Mapper.map(m)).collect(Collectors.toList());
    }

    public void addNewDate(NonWorkingDayDto nonWorkingDayDto)
    {
        nonWorkingDayDto.setDayOfWeek(nonWorkingDayDto.getDate().getDayOfWeek().toString());

        nonWorkingDayRepo.save(MapperDto.map(nonWorkingDayDto));
    }


    public void deleteDate(NonWorkingDayDto nonWorkingDayDto) {

//      val search = nonWorkingDayRepo.findAll().stream().filter(n->(n.getDescription()).equals(nonWorkingDayDto.getDescription()))
//        .filter((m->m.getDate().equals(nonWorkingDayDto.getDate())))
//        .filter(k->k.getCountryCode().equals(nonWorkingDayDto.getCountryCode())).collect(Collectors.toList());

      val toDelete = nonWorkingDayRepo.findByCountryCodeAndDescriptionAndDate(nonWorkingDayDto.getCountryCode(),
              nonWorkingDayDto.getDescription(), nonWorkingDayDto.getDate());

        nonWorkingDayRepo.deleteAll(toDelete);
    }

    public List<NonWorkingDayDto> findInYear(String year) {

        LocalDate localDate1;
        LocalDate localDate2;

        try {
            localDate1 = LocalDate.of(Integer.parseInt(year), 1, 1);
            localDate2 = LocalDate.of(Integer.parseInt(year), 12, 31);
        } catch (NumberFormatException ex) {
            return Collections.emptyList();
        }

        return nonWorkingDayRepo.findByDateBetween(localDate1, localDate2).stream().map(m->Mapper.map(m)).collect(Collectors.toList());
    }

    public List<NonWorkingDayDto> findInPeriod(String dateFrom, String dateTo) {

        LocalDate localDate1 = LocalDate.parse(dateFrom);
        LocalDate localDate2 = LocalDate.parse(dateTo);

        return nonWorkingDayRepo.findByDateBetween(localDate1, localDate2).stream().map(m->Mapper.map(m)).collect(Collectors.toList());
    }


}
