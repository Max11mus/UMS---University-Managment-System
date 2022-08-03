package com.foxminded.ums.service;

import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.entities.Teacher;
import com.foxminded.ums.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Teacher findTeacher(Teacher teacher) {
        return teacherRepository.findById(teacher.getId()).get();
    }

    public List<Teacher> findTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    public Teacher addTeacher(TeacherDto teacherDto) {
        return teacherRepository.save(teacherDto.convertToEntity());
    }

    public Teacher updateTeacher(TeacherDto teacherDto) {
        return teacherRepository.save(teacherDto.convertToEntity());
    }

    public void deleteTeacher(TeacherDto teacherDto) {
        teacherRepository.deleteById(teacherDto.convertToEntity().getId());
    }

}