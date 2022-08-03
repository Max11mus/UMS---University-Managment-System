package com.foxminded.ums.service;


import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.entities.Student;
import com.foxminded.ums.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student findStudent(StudentDto studentDto) {
        return studentRepository.findById(studentDto.getId()).get();
    }

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(StudentDto studentDto) {
        return studentRepository.save(studentDto.convertToEntity());
    }

    public Student updateStudent(StudentDto studentDto) {
        return studentRepository.save(studentDto.convertToEntity());
    }

    public void deleteStudent(StudentDto studentDto) {
        studentRepository.deleteById(studentDto.getId());
    }

}