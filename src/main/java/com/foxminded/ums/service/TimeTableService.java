package com.foxminded.ums.service;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.entities.Student;
import com.foxminded.ums.entities.Teacher;
import com.foxminded.ums.entities.TimeTableUnit;
import com.foxminded.ums.repository.TimeTableUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TimeTableService {
    @Autowired
    TimeTableUnitRepository timeTableUnitRepository;

    public List<TimeTableUnit> findByDayForStudent(StudentDto studentDto, LocalDate day) {
        return timeTableUnitRepository.findByDayForStudent(studentDto.convertToEntity().getId(),
                        day.getDayOfMonth(), day.getMonthValue(), day.getYear());
    }

    public List<TimeTableUnit> findByMonthForStudent(StudentDto studentDto, LocalDate month) {
        return timeTableUnitRepository.findByMonthForStudent(studentDto.convertToEntity().getId(),
                month.getYear(), month.getMonthValue());
    }

    public List<TimeTableUnit> findByDayForTeacher(TeacherDto teacherDto, LocalDate day) {
        return timeTableUnitRepository.findByDayForStudent(teacherDto.convertToEntity().getId(),
                day.getDayOfMonth(), day.getMonthValue(), day.getYear());
    }

    public List<TimeTableUnit> findByMonthForTeacher(TeacherDto teacherDto, LocalDate month) {
        return timeTableUnitRepository.findByMonthForStudent(teacherDto.convertToEntity().getId(),
                month.getYear(), month.getMonthValue());
    }

}