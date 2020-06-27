package api.nonWorkingDays;

import api.nonWorkingDays.entity.NonWorkingDay;
import api.nonWorkingDays.repo.NonWorkingDayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SupplyDatabase implements SupplyDatabaseInterface {

    @Autowired
    private NonWorkingDayRepo nonWorkingDayRepo;


    public SupplyDatabase(NonWorkingDayRepo nonWorkingDayRepo) {
        this.nonWorkingDayRepo = nonWorkingDayRepo;
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
        }
        NonWorkingDay example2 = new NonWorkingDay();
        example2.setDate(LocalDate.of(2020, 6, 11));
        example2.setDescription("Boze_Cialo");
        example2.setCountryCode("PL");
        nonWorkingDayRepo.save(example2);


    }
}

