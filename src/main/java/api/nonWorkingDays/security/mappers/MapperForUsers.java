package api.nonWorkingDays.security.mappers;

import api.nonWorkingDays.dto.NonWorkingDayDto;
import api.nonWorkingDays.entity.NonWorkingDay;
import api.nonWorkingDays.security.model.LoginCredentials;
import api.nonWorkingDays.security.model.LoginUserDto;

public class MapperForUsers {

    public static LoginUserDto map(LoginCredentials loginCredentials) {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setEmail(loginCredentials.getUsername());
        loginUserDto.setPassword(loginCredentials.getPassword());
        return loginUserDto;
    }
}
