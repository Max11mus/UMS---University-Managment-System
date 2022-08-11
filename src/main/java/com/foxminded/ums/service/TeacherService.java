package com.foxminded.ums.service;

import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.entities.Teacher;
import com.foxminded.ums.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public Teacher findTeacher(Teacher teacher) {
        UUID id = teacher.getId();
        return teacherRepository.findById(id).get();
    }

    public List<TeacherDto> findTeachers() {
        List<TeacherDto> teachersDto = new ArrayList<>();
        teacherRepository.findAll().forEach(t -> teachersDto.add(convertToDto(t)));
        return teachersDto;
    }

    @Transactional
    public Teacher addTeacher(TeacherDto teacherDto) {
        Teacher entity = convertToEntity(teacherDto);
        return teacherRepository.save(entity);
    }

    @Transactional
    public Teacher updateTeacher(TeacherDto teacherDto) {
        Teacher entity = convertToEntity(teacherDto);
        return teacherRepository.save(entity);
    }

    @Transactional
    public void deleteTeacher(TeacherDto teacherDto) {
        UUID id = teacherDto.getId();
        teacherRepository.deleteById(id);
    }

    public Teacher convertToEntity(TeacherDto teacherDto){
        Teacher entity = new Teacher();

        entity.setId(teacherDto.getId());
        entity.setName(teacherDto.getName());
        entity.setSurname(teacherDto.getSurname());
        entity.setBirthDate(teacherDto.getBirthDate());
        entity.setTimeZone(teacherDto.getTimeZone());
        entity.setLogin(teacherDto.getLogin());
        entity.setEmail(teacherDto.getEmail());
        entity.setAvatarPath(teacherDto.getAvatarPath());
        entity.setHashedPassword(teacherDto.getHashedPassword());
        entity.setAcademicDegree(teacherDto.getAcademicDegree());
        entity.setEmploymentDate(teacherDto.getEmploymentDate());

        return  entity;
    }

    public TeacherDto convertToDto(Teacher teacher){
        TeacherDto dto = new TeacherDto();

        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setSurname(teacher.getSurname());
        dto.setBirthDate(teacher.getBirthDate());
        dto.setTimeZone(teacher.getTimeZone());
        dto.setLogin(teacher.getLogin());
        dto.setEmail(teacher.getEmail());
        dto.setAvatarPath(teacher.getAvatarPath());
        dto.setHashedPassword(teacher.getHashedPassword());
        dto.setAcademicDegree(teacher.getAcademicDegree());
        dto.setEmploymentDate(teacher.getEmploymentDate());

        return  dto;
    }
}