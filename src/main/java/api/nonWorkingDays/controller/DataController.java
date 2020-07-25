package api.nonWorkingDays.controller;

import api.nonWorkingDays.dto.NonWorkingDayDto;
import api.nonWorkingDays.service.DataService;
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
    public List<NonWorkingDayDto> findAll(@RequestParam(value = "countryCode") String countryCode) {
        return dataService.findAllDates(countryCode);
    }

    @GetMapping("/between")
    public List<NonWorkingDayDto> findInPeriod(@RequestParam(value = "date1") String date1, @RequestParam(value = "date2") String date2) {
        return dataService.findInPeriod(date1, date2);
    }

    @GetMapping("/year")
    public List<NonWorkingDayDto> findInYear(@RequestParam(value = "year") String year) {
        return dataService.findInYear(year);
    }

    @PostMapping("/add")
    public String addNewDate(@RequestBody NonWorkingDayDto nonWorkingDayDto) {
        dataService.addNewDate(nonWorkingDayDto);
        return "OK";
    }

    @PutMapping("/add")
    public String correctDate(@RequestBody NonWorkingDayDto nonWorkingDayDto) {
        dataService.addNewDate(nonWorkingDayDto);
        return "OK";
    }

    @DeleteMapping("/delete")
    public String deleteDate(@RequestBody NonWorkingDayDto nonWorkingDayDto) {
        dataService.deleteDate(nonWorkingDayDto);
        return "OK";
    }
}
