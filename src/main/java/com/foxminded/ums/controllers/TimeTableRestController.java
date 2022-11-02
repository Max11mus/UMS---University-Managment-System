package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.TimeTableUnitDto;
import com.foxminded.ums.exeptions.TeacherIlegalUuidException;
import com.foxminded.ums.exeptions.TimeTableIlegalTeacherUuidException;
import com.foxminded.ums.exeptions.TimeTableUnitIlegalStudentUuidException;
import com.foxminded.ums.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

@RestController
@RequestMapping(value = "/timetable")
public class TimeTableRestController {

    @Autowired
    private TimeTableService timeTableService;

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, params = {"startDay", "endDay"})
    public ResponseEntity<List<TimeTableUnitDto>> getTimeTableForStudent(
            @PathVariable String id,
            @RequestParam(value = "startDay") String startDay,
            @RequestParam(value = "endDay") String endDay) {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = null;

        try {
            timeTableUnitDto = timeTableService.findByPeriodForStudent(UUID.fromString(id),
                    startDayDate, endDayDate);
        } catch (IllegalArgumentException e) {
            throw new TimeTableUnitIlegalStudentUuidException(id + " isn't correct Strudent UUID." +
                    " See RFC 4122 - 4.1. Format", e);
        }

        return ResponseEntity.ok().body(timeTableUnitDto);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, params = {"startDay", "endDay"})
    public ResponseEntity<List<TimeTableUnitDto>> getTimeTableForTeacher(
            @PathVariable String id,
            @RequestParam(value = "startDay") String startDay,
            @RequestParam(value = "endDay") String endDay) {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = null;
        try {
            timeTableUnitDto = timeTableService.findByPeriodForTeacher(UUID.fromString(id),
                    startDayDate, endDayDate);
        } catch (IllegalArgumentException e) {
            throw new TimeTableIlegalTeacherUuidException(id + " isn't correct Teacher UUID." +
                    " See RFC 4122 - 4.1. Format", e);
        }

        return ResponseEntity.ok().body(timeTableUnitDto);
    }

}
