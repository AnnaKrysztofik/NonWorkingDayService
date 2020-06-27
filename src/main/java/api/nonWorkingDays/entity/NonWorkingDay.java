package api.nonWorkingDays.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class NonWorkingDay {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;
    private String countryCode;
    private String dayOfWeek;
}
