package api.nonWorkingDays.service;

import api.nonWorkingDays.dto.NonWorkingDayDto;
import api.nonWorkingDays.entity.NonWorkingDay;
import api.nonWorkingDays.repo.NonWorkingDayRepo;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class DataServiceTest {

    @Autowired
    private DataService dataService;

//    @MockBean
//    private NonWorkingDayRepo nonWorkingDayRepo;


    @Test
    void findAllDates() {
        // Arrange
        val nonWorkingDayDto1 = new NonWorkingDayDto();
        nonWorkingDayDto1.setDate(LocalDate.of(2019, 2, 1));
        nonWorkingDayDto1.setDescription("Nowy Rok");
        nonWorkingDayDto1.setCountryCode("PL");

        val nonWorkingDayDto2 = new NonWorkingDayDto();
        nonWorkingDayDto2.setDate(LocalDate.of(2020, 2, 1));
        nonWorkingDayDto2.setDescription("Nowy Rok");
        nonWorkingDayDto2.setCountryCode("UK");

        dataService.addNewDate(nonWorkingDayDto1);
        dataService.addNewDate(nonWorkingDayDto2);
        // Act
        List<NonWorkingDayDto> foundAll = dataService.findAllDays();

        // Assert
        assertEquals(List.of(nonWorkingDayDto1, nonWorkingDayDto2), foundAll);
        dataService.deleteDate(nonWorkingDayDto1);
        dataService.deleteDate(nonWorkingDayDto2);

    }


    @Test
    void addNewDate() {
        // Arrange
        val nonWorkingDayDto1 = new NonWorkingDayDto();
        nonWorkingDayDto1.setDate(LocalDate.of(2019, 2, 1));
        nonWorkingDayDto1.setDescription("Nowy Rok");
        nonWorkingDayDto1.setCountryCode("PL");

        val nonWorkingDayDto2 = new NonWorkingDayDto();
        nonWorkingDayDto2.setDate(LocalDate.of(2020, 2, 1));
        nonWorkingDayDto2.setDescription("Nowy Rok");
        nonWorkingDayDto2.setCountryCode("UK");


        // Act
        dataService.addNewDate(nonWorkingDayDto1);
        dataService.addNewDate(nonWorkingDayDto2);

        // Assert
        assertEquals(List.of(nonWorkingDayDto1, nonWorkingDayDto2),dataService.findAllDays());
        dataService.deleteDate(nonWorkingDayDto1);
        dataService.deleteDate(nonWorkingDayDto2);

    }

    @Test
    void deleteDate_test() {

        // Arrange
        val nonWorkingDayDto1 = new NonWorkingDayDto();
        nonWorkingDayDto1.setDate(LocalDate.of(2020, 2, 1));
        nonWorkingDayDto1.setDescription("Nowy Rok");
        nonWorkingDayDto1.setCountryCode("PL");

        dataService.addNewDate(nonWorkingDayDto1);

        val beforeDelete = dataService.findAllDates("PL").stream()
                .filter(c->c.getDate().toString().equals(LocalDate.of(2020, 2, 1).toString()))
                .collect(Collectors.toList());

        assertEquals(1, beforeDelete.size());

        // Act
        dataService.deleteDate(nonWorkingDayDto1);

        // Assert
        val afterDelete = dataService.findAllDates("PL").stream()
                .filter(c->c.getDate().toString().equals(LocalDate.of(2020, 2, 1).toString()))
                .collect(Collectors.toList());

        assertEquals(0, afterDelete.size());
    }

    @Test
    void deleteDateTwoItems_test() {

        // Arrange
        val nonWorkingDayDto1 = new NonWorkingDayDto();
        nonWorkingDayDto1.setDate(LocalDate.of(2020, 2, 1));
        nonWorkingDayDto1.setDescription("Nowy Rok");
        nonWorkingDayDto1.setCountryCode("PL");

        val nonWorkingDayDto2 = new NonWorkingDayDto();
        nonWorkingDayDto2.setDate(LocalDate.of(2020, 2, 1));
        nonWorkingDayDto2.setDescription("Nowy Rok");
        nonWorkingDayDto2.setCountryCode("UK");

        dataService.addNewDate(nonWorkingDayDto1);
        dataService.addNewDate(nonWorkingDayDto2);

        val beforeDelete = dataService.findAllDays().stream()
                .filter(c->c.getDate().toString().equals(LocalDate.of(2020, 2, 1).toString()))
                .collect(Collectors.toList());
        assertEquals(2, beforeDelete.size());

        // Act
        dataService.deleteDate(nonWorkingDayDto1);

        // Assert
        val afterDelete = dataService.findAllDays().stream()
                .filter(c->c.getDate().toString().equals(LocalDate.of(2020, 2, 1).toString()))
                .collect(Collectors.toList());

        assertEquals(1, afterDelete.size());

        dataService.deleteDate(nonWorkingDayDto2);
    }

    @Test
    void findInYear() {
        // Arrange
        val nonWorkingDayDto1 = new NonWorkingDayDto();
        nonWorkingDayDto1.setDate(LocalDate.of(2019, 2, 1));
        nonWorkingDayDto1.setDescription("Nowy Rok");
        nonWorkingDayDto1.setCountryCode("PL");

        val nonWorkingDayDto2 = new NonWorkingDayDto();
        nonWorkingDayDto2.setDate(LocalDate.of(2020, 2, 1));
        nonWorkingDayDto2.setDescription("Nowy Rok");
        nonWorkingDayDto2.setCountryCode("UK");

        dataService.addNewDate(nonWorkingDayDto1);
        dataService.addNewDate(nonWorkingDayDto2);
        // Act
        List<NonWorkingDayDto> foundYear = dataService.findInYear("2020");

        // Assert
        assertEquals(List.of(nonWorkingDayDto2), foundYear);

        dataService.deleteDate(nonWorkingDayDto1);
        dataService.deleteDate(nonWorkingDayDto2);

    }

    @Test
    void findInPeriod() {
        // Arrange
        val nonWorkingDayDto1 = new NonWorkingDayDto();
        nonWorkingDayDto1.setDate(LocalDate.of(2019, 2, 1));
        nonWorkingDayDto1.setDescription("Nowy Rok");
        nonWorkingDayDto1.setCountryCode("PL");

        val nonWorkingDayDto2 = new NonWorkingDayDto();
        nonWorkingDayDto2.setDate(LocalDate.of(2020, 2, 1));
        nonWorkingDayDto2.setDescription("Nowy Rok");
        nonWorkingDayDto2.setCountryCode("UK");

        dataService.addNewDate(nonWorkingDayDto1);
        dataService.addNewDate(nonWorkingDayDto2);
        // Act
        List<NonWorkingDayDto> foundInPeriod = dataService.findInPeriod("2019-01-01", "2019-12-15");

        // Assert
        assertEquals(List.of(nonWorkingDayDto1), foundInPeriod );

        dataService.deleteDate(nonWorkingDayDto1);
        dataService.deleteDate(nonWorkingDayDto2);
    }

}