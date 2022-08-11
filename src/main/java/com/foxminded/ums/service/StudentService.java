package com.foxminded.ums.service;


import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.entities.Student;
import com.foxminded.ums.entities.Teacher;
import com.foxminded.ums.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentDto findStudent(Student student) {
        UUID id = student.getId();
        return convertToDto(studentRepository.findById(id).get());
    }

    public List<StudentDto> findStudents() {
        List<StudentDto> studentsDto = new ArrayList<>();
        studentRepository.findAll().forEach(s -> studentsDto.add(convertToDto(s)));
        return studentsDto;
    }

    @Transactional
    public Student addStudent(StudentDto studentDto) {
        Student entity = convertToEntity(studentDto);
        return studentRepository.save(entity);
    }

    @Transactional
    public Student updateStudent(StudentDto studentDto) {
        Student entity = convertToEntity(studentDto);
        return studentRepository.save(entity);
    }

    @Transactional
    public void deleteStudent(StudentDto studentDto) {
        UUID id = studentDto.getId();
        studentRepository.deleteById(id);
    }

    public Student convertToEntity(StudentDto studentDto){
        Student entity = new Student();

        entity.setId(studentDto.getId());
        entity.setName(studentDto.getName());
        entity.setSurname(studentDto.getSurname());
        entity.setBirthDate(studentDto.getBirthDate());
        entity.setTimeZone(studentDto.getTimeZone());
        entity.setLogin(studentDto.getLogin());
        entity.setEmail(studentDto.getEmail());
        entity.setAvatarPath(studentDto.getAvatarPath());
        entity.setHashedPassword(studentDto.getHashedPassword());
        entity.setDropoutDate(studentDto.getDropoutDate());
        entity.setEnrollDate(studentDto.getEnrollDate());

        return  entity;
    }

    public StudentDto convertToDto(Student student){
        StudentDto dto = new StudentDto();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        dto.setBirthDate(student.getBirthDate());
        dto.setTimeZone(student.getTimeZone());
        dto.setLogin(student.getLogin());
        dto.setEmail(student.getEmail());
        dto.setAvatarPath(student.getAvatarPath());
        dto.setHashedPassword(student.getHashedPassword());
        dto.setDropoutDate(student.getDropoutDate());
        dto.setEnrollDate(student.getEnrollDate());

        return  dto;
    }
}