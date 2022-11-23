package com.foxminded.ums.service;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.entities.Student;
import com.foxminded.ums.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentDto findStudent(UUID studentId) {
        StudentDto studentsDto = convertToDto(studentRepository.findById(studentId).get());
        studentsDto.setHashedPassword(null);
        return studentsDto;
    }

    public List<StudentDto> findStudents() {
        List<StudentDto> studentsDto = new ArrayList<>();
        studentRepository.findAll().forEach(s -> studentsDto.add(convertToDto(s)));
        studentsDto.forEach(s -> s.setHashedPassword(null));
        return studentsDto;
    }

    public List<StudentDto> findStudentsPageable(Pageable pageable) {
        List<StudentDto> studentsDto = new ArrayList<>();
        studentRepository.findAll(pageable).forEach(s -> studentsDto.add(convertToDto(s)));
        studentsDto.forEach(s -> s.setHashedPassword(null));
        return studentsDto;
    }

    @Transactional
    public StudentDto addStudent(StudentDto studentDto) {
        Student entity = convertToEntity(studentDto);
        entity.setId(null);
        if (entity.getHashedPassword() == null) {
            entity.setHashedPassword("");
        }
        return convertToDto(studentRepository.save(entity));
    }

    @Transactional
    public StudentDto updateStudent(StudentDto studentDto) {
        findStudent(studentDto.getId());

        Student entity = convertToEntity(studentDto);
        studentRepository.save(entity);

        StudentDto dto = convertToDto(studentRepository.findById(studentDto.getId()).get());
        dto.setHashedPassword(null);

        return dto;
    }

    @Transactional
    public void deleteStudent(UUID studentUuid) {
        studentRepository.deleteById(studentUuid);
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