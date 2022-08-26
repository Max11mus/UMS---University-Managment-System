package com.foxminded.ums.service;

import com.foxminded.ums.dto.TimeTableUnitDto;
import com.foxminded.ums.entities.TimeTableUnit;
import com.foxminded.ums.repository.TimeTableUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TimeTableService {
    @Autowired
    TimeTableUnitRepository timeTableUnitRepository;

    public List<TimeTableUnitDto> findByPeriodForStudent(UUID studentUuid, LocalDate startDay, LocalDate endDay) {
        LocalDateTime startDateTime = startDay.atStartOfDay();
        LocalDateTime endDateTime = endDay.atStartOfDay();

        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>();
        timeTableUnitRepository.findByBeginBetweenForStudent(studentUuid, startDateTime, endDateTime)
                .forEach(t -> timeTableUnitDtos.add(convertToDto(t)));

        for (TimeTableUnitDto t : timeTableUnitDtos) {
            t.setGroups(null);
            t.getLecture().setDescription(null);
            t.getLecture().setTopic(null);
            t.getLecture().setSubject(null);
            t.setGroups(null);
        }

        return timeTableUnitDtos;
    }

    public List<TimeTableUnitDto> findByPeriodForTeacher(UUID teacherUuid, LocalDate startDay,LocalDate endDay ) {
        LocalDateTime startDateTime = startDay.atStartOfDay();
        LocalDateTime endDateTime = endDay.atStartOfDay();

        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>();
        timeTableUnitRepository.findByBeginBetweenForTeacher(teacherUuid, startDateTime, endDateTime)
                .forEach(t -> timeTableUnitDtos.add(convertToDto(t)));

        for (TimeTableUnitDto t : timeTableUnitDtos) {
            t.setGroups(null);
            t.setLecture(null);
        }

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