package com.foxminded.ums.service;

import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.entities.Teacher;
import com.foxminded.ums.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public TeacherDto findTeacher(UUID teacherUuid) {
        return convertToDto(teacherRepository.findById(teacherUuid).get());
    }

    public List<TeacherDto> findTeachers() {
        List<TeacherDto> teachersDto = new ArrayList<>();
        teacherRepository.findAll().forEach(t -> teachersDto.add(convertToDto(t)));
        return teachersDto;
    }

    public List<TeacherDto> findTeachersPageable(int pageNumber, int pageSize) {
        List<TeacherDto> teachersDto = new ArrayList<>();
        teacherRepository.findAll(PageRequest.of(pageNumber, pageSize)).forEach(s -> teachersDto.add(convertToDto(s)));
        return teachersDto;

    }
    
    @Transactional
    public TeacherDto addTeacher(TeacherDto teacherDto) {
        Teacher entity = convertToEntity(teacherDto);
        return convertToDto(teacherRepository.save(entity));
    }

    @Transactional
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        Teacher entity = convertToEntity(teacherDto);
        return convertToDto(teacherRepository.save(entity));
    }

    @Transactional
    public void deleteTeacher(UUID teacherUuid) {
        teacherRepository.deleteById(teacherUuid);
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