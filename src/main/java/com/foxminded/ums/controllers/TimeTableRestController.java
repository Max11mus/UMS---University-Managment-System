package com.foxminded.ums.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.dto.TimeTableUnitDto;
import com.foxminded.ums.json.TimeTableUnitDtoSerializerForStudent;
import com.foxminded.ums.json.TimeTableUnitDtoSerializerForTeacher;
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

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, params = "day")
    @ResponseBody
    public ResponseEntity<Object> getDailyTimeTableForStudent(@PathVariable String id,
                                                              @RequestParam(value = "day") String day) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dayDate = LocalDate.parse(day, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = timeTableService.findByDayForStudent(UUID.fromString(id), dayDate);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new TimeTableUnitDtoSerializerForStudent(timeTableUnitDto.getClass()));
        mapper.registerModule(module);

        String serializedJson = null;
        try {
            serializedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(timeTableUnitDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(serializedJson);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET,params = "month")
    @ResponseBody
    public ResponseEntity<Object> getMonthlyTimeTableForStudent(@PathVariable String id,
                                              @RequestParam(value = "month") String month) {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate monthDate = LocalDate.parse(month, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = timeTableService.findByMonthForStudent(UUID.fromString(id), monthDate);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new TimeTableUnitDtoSerializerForStudent(timeTableUnitDto.getClass()));
        mapper.registerModule(module);

        String serializedJson = null;
        try {
            serializedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(timeTableUnitDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(serializedJson);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET, params = "day")
    @ResponseBody
    public ResponseEntity<Object> getDailyTimeTableForTeacher(@PathVariable String id,
                                                              @RequestParam(value = "day") String day) {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dayDate = LocalDate.parse(day, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = timeTableService.findByDayForTeacher(UUID.fromString(id), dayDate);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new TimeTableUnitDtoSerializerForTeacher(timeTableUnitDto.getClass()));
        mapper.registerModule(module);

        String serializedJson = null;
        try {
            serializedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(timeTableUnitDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(serializedJson);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET,params = "month")
    @ResponseBody
    public ResponseEntity<Object> getMonthyTimeTableForTeacher(@PathVariable String id,
                                              @RequestParam(value = "month") String month) {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate monthDate = LocalDate.parse(month, formatter);

        List<TimeTableUnitDto> timeTableUnitDto = timeTableService.findByMonthForTeacher(UUID.fromString(id), monthDate);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new TimeTableUnitDtoSerializerForStudent(timeTableUnitDto.getClass()));
        mapper.registerModule(module);

        String serializedJson = null;
        try {
            serializedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(timeTableUnitDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(serializedJson);
    }

}
