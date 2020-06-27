package api.nonWorkingDays;

import api.nonWorkingDays.entity.NonWorkingDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {
    private DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/all")
    public List<NonWorkingDay> findAll(@RequestParam(value = "countryCode") String countryCode) {
        return dataService.findAllDates(countryCode);
    }

    @GetMapping("/between")
    public List<NonWorkingDay> findInPeriod(@RequestParam(value = "date1") String date1, @RequestParam(value = "date2") String date2) {
        return dataService.findInPeriod(date1, date2);
    }

    @GetMapping("/year")
    public List<NonWorkingDay> findInYear(@RequestParam(value = "year") String year) {
        return dataService.findInYear(year);
    }

    @PostMapping("/add")
    public String addNewDate(@RequestBody NonWorkingDay nonWorkingDay) {
        dataService.addNewDate(nonWorkingDay);
        return "OK";
    }

    @PutMapping("/add")
    public String correctDate(@RequestBody NonWorkingDay nonWorkingDay) {
        dataService.addNewDate(nonWorkingDay);
        return "OK";
    }

    @DeleteMapping("/delete")
    public String deleteDate(@RequestBody NonWorkingDay nonWorkingDay) {
        dataService.deleteDate(nonWorkingDay);
        return "OK";
    }
}
