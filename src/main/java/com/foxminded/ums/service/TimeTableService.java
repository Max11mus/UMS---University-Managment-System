package com.foxminded.ums.service;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.dto.TimeTableUnitDto;
import com.foxminded.ums.entities.TimeTableUnit;
import com.foxminded.ums.repository.TimeTableUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableService {
    @Autowired
    TimeTableUnitRepository timeTableUnitRepository;

    public List<TimeTableUnitDto> findByDayForStudent(StudentDto studentDto, LocalDate dayLocalDate) {
        LocalDateTime startDateTime = dayLocalDate.atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1);

        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>();
        timeTableUnitRepository.findByBeginBetweenForStudent(studentDto.getId(), startDateTime, endDateTime)
                .forEach(t -> timeTableUnitDtos.add(convertToDto(t)));
        return timeTableUnitDtos;
    }

    public List<TimeTableUnitDto> findByMonthForStudent(StudentDto studentDto, LocalDate monthLocalDate) {
        LocalDateTime startDateTime = monthLocalDate.withDayOfMonth(1).atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusMonths(1);

        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>();
        timeTableUnitRepository.findByBeginBetweenForStudent(studentDto.getId(), startDateTime, endDateTime)
                .forEach(t -> timeTableUnitDtos.add(convertToDto(t)));
        return timeTableUnitDtos;
    }

    public List<TimeTableUnitDto> findByDayForTeacher(TeacherDto teacherDto, LocalDate dayLocalDate) {
        LocalDateTime startDateTime = dayLocalDate.atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1);

        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>();
        timeTableUnitRepository.findByBeginBetweenForStudent(teacherDto.getId(), startDateTime, endDateTime)
                .forEach(t -> timeTableUnitDtos.add(convertToDto(t)));
        return timeTableUnitDtos;
    }

    public List<TimeTableUnitDto> findByMonthForTeacher(TeacherDto teacherDto, LocalDate monthLocalDate) {
        LocalDateTime startDateTime = monthLocalDate.withDayOfMonth(1).atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusMonths(1);

        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>();
        timeTableUnitRepository.findByBeginBetweenForTeacher(teacherDto.getId(), startDateTime, endDateTime)
                .forEach(t -> timeTableUnitDtos.add(convertToDto(t)));
        return timeTableUnitDtos;
    }

    public TimeTableUnitDto convertToDto(TimeTableUnit timeTableUnit ){
        TimeTableUnitDto dto = new TimeTableUnitDto();

        dto.setId(timeTableUnit.getId());
        dto.setBegin(timeTableUnit.getBegin());
        dto.setEnd(timeTableUnit.getEnd());
        dto.setGroups(timeTableUnit.getGroups());
        dto.setLecture(timeTableUnit.getLecture());
        dto.setLocation(timeTableUnit.getLocation());

        return  dto;
    }

}