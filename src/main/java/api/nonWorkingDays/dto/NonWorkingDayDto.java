package api.nonWorkingDays.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class NonWorkingDayDto {
    private LocalDate date;
    private String description;
    private String countryCode;
    private String dayOfWeek;
}
