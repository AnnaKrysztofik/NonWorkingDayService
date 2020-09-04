package api.nonWorkingDays.supply;

import api.nonWorkingDays.entity.NonWorkingDay;
import api.nonWorkingDays.security.model.RegisterUserDto;
import api.nonWorkingDays.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SupplyDatabaseFromFileTest {

    @Autowired
    private SupplyDatabaseFromFile supplyDatabaseFromFile;

    @Test
    void supplyFromFile() {
    }

    @Test
    void buildNonWorkingDayFromString() {
        // Arrange
        NonWorkingDay nonWorkingDay = new NonWorkingDay();
        nonWorkingDay.setDescription("Boże Narodzenie");
        nonWorkingDay.setCountryCode("PL");
        nonWorkingDay.setDate(LocalDate.of(2020,12,25));


        // Act
        NonWorkingDay fromString = supplyDatabaseFromFile.buildNonWorkingDayFromString("2020-12-25,Boże Narodzenie");



        // Assert
        assertEquals(nonWorkingDay, fromString);
    }
}