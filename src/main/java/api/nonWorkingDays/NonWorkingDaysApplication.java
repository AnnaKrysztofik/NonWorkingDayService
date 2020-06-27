package api.nonWorkingDays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.sql.SQLOutput;
import java.time.LocalDate;

@SpringBootApplication
public class NonWorkingDaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(NonWorkingDaysApplication.class, args);
	}



}
