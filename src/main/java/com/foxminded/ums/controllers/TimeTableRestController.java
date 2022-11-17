package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.TimeTableUnitDto;
import com.foxminded.ums.service.TimeTableService;
import com.foxminded.ums.validation.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@RestController
@Validated
@RequestMapping(value = "/timetable")
public class TimeTableRestController {

    @Autowired
    private TimeTableService timeTableService;

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, params = {"startDay", "endDay"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TimeTableUnitDto>> getTimeTableForStudent(
            @Valid @PathVariable("id") @UUID String id,
            @RequestParam(value = "startDay") String startDay,
            @RequestParam(value = "endDay") String endDay) {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = timeTableService.findByPeriodForStudent(java.util.UUID.fromString(id),
                    startDayDate, endDayDate);

        return ResponseEntity.ok().body(timeTableUnitDto);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, params = {"startDay", "endDay"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TimeTableUnitDto>> getTimeTableForTeacher(
            @Valid @PathVariable("id") @UUID String id,
            @RequestParam(value = "startDay") String startDay,
            @RequestParam(value = "endDay") String endDay) {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = null;

        timeTableUnitDto = timeTableService.findByPeriodForTeacher(java.util.UUID.fromString(id),
                startDayDate, endDayDate);

        return ResponseEntity.ok().body(timeTableUnitDto);
    }

}
