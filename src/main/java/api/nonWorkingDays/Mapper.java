package api.nonWorkingDays;

import api.nonWorkingDays.entity.NonWorkingDay;
import api.nonWorkingDays.dto.NonWorkingDayDto;

public class Mapper {

    public static NonWorkingDayDto map(NonWorkingDay nonWorkingDay){
        NonWorkingDayDto nonWorkingDayDto = new NonWorkingDayDto();
        nonWorkingDayDto.setCountryCode(nonWorkingDay.getCountryCode());
        nonWorkingDayDto.setDate(nonWorkingDay.getDate());
        nonWorkingDayDto.setDescription(nonWorkingDay.getDescription());
        nonWorkingDayDto.setDayOfWeek(nonWorkingDay.getDate().getDayOfWeek().toString());
        return nonWorkingDayDto;
    }
}
