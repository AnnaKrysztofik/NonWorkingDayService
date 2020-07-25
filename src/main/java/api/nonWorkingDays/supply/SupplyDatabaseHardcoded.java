package api.nonWorkingDays.supply;

import api.nonWorkingDays.entity.NonWorkingDay;
import api.nonWorkingDays.repo.NonWorkingDayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Primary
@Component
@Qualifier("supplyDatabaseHardcoded")
public class SupplyDatabaseHardcoded implements SupplyDatabaseInterface {

    @Autowired
    private NonWorkingDayRepo nonWorkingDayRepo;


    public SupplyDatabaseHardcoded(NonWorkingDayRepo nonWorkingDayRepo) {
        this.nonWorkingDayRepo = nonWorkingDayRepo;
    }

    public SupplyDatabaseHardcoded() {
    }

    @Override
    public void supply() {

        for (int i = 0; i < 10; i++) {
            NonWorkingDay example1 = new NonWorkingDay();
            example1.setDate(LocalDate.of(2020 + i, 1, 01));
            example1.setDescription("Nowy_Rok");
            example1.setCountryCode("PL");
            nonWorkingDayRepo.save(example1);

            NonWorkingDay example3 = new NonWorkingDay();
            example3.setDate(LocalDate.of(2020 + i, 8, 15));
            example3.setDescription("Wniebowziecie_Najswietszej_Maryi_Panny");
            example3.setCountryCode("PL");
            nonWorkingDayRepo.save(example3);

            NonWorkingDay example4 = new NonWorkingDay();
            example4.setDate(LocalDate.of(2020 + i, 5, 1));
            example4.setDescription("Swieto_Pracy");
            example4.setCountryCode("PL");
            nonWorkingDayRepo.save(example4);

            NonWorkingDay example5 = new NonWorkingDay();
            example5.setDate(LocalDate.of(2020 + i, 5, 3));
            example5.setDescription("Swieto Konstytucji 3 Maja");
            example5.setCountryCode("PL");
            nonWorkingDayRepo.save(example5);

            NonWorkingDay example6 = new NonWorkingDay();
            example6.setDate(LocalDate.of(2020 + i, 11, 1));
            example6.setDescription("Wszystkich Świętych");
            example6.setCountryCode("PL");
            nonWorkingDayRepo.save(example6);

            NonWorkingDay example7 = new NonWorkingDay();
            example7.setDate(LocalDate.of(2020 + i, 11, 11));
            example7.setDescription("Święto Niepodległości");
            example7.setCountryCode("PL");
            nonWorkingDayRepo.save(example7);

            NonWorkingDay example8 = new NonWorkingDay();
            example8.setDate(LocalDate.of(2020 + i, 12, 25));
            example8.setDescription("Boże Narodzenie");
            example8.setCountryCode("PL");
            nonWorkingDayRepo.save(example8);

            NonWorkingDay example9 = new NonWorkingDay();
            example9.setDate(LocalDate.of(2020 + i, 12, 26));
            example9.setDescription("Boże Narodzenie. Dzień Drugi");
            example9.setCountryCode("PL");
            nonWorkingDayRepo.save(example9);
        }
        NonWorkingDay example2 = new NonWorkingDay();
        example2.setDate(LocalDate.of(2020, 6, 11));
        example2.setDescription("Boze_Cialo");
        example2.setCountryCode("PL");
        nonWorkingDayRepo.save(example2);

        NonWorkingDay example10 = new NonWorkingDay();
        example10.setDate(LocalDate.of(2020, 4, 13));
        example10.setDescription("Poniedziałek Wielkanocny");
        example10.setCountryCode("PL");
        nonWorkingDayRepo.save(example10);
    }
}

