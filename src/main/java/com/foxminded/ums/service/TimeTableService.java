package com.foxminded.ums.service;

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

    public List<TimeTableUnit> findByDayForStudent(Student student, LocalDate day) {
        return timeTableUnitRepository.findByDayForStudent(student.getId(),
                        day.getDayOfMonth(), day.getMonthValue(), day.getYear());
    }

    public List<TimeTableUnit> findByMonthForStudent(Student student, LocalDate month) {
        return timeTableUnitRepository.findByMonthForStudent(student.getId(),
                month.getYear(), month.getMonthValue());
    }

    public List<TimeTableUnit> findByDayForTeacher(Teacher teacher, LocalDate day) {
        return timeTableUnitRepository.findByDayForStudent(teacher.getId(),
                day.getDayOfMonth(), day.getMonthValue(), day.getYear());
    }

    public List<TimeTableUnit> findByMonthForTeacher(Teacher teacher, LocalDate month) {
        return timeTableUnitRepository.findByMonthForStudent(teacher.getId(),
                month.getYear(), month.getMonthValue());
    }

}