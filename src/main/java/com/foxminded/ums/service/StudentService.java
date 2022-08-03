package com.foxminded.ums.service;

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

    public Student findStudent(Student student) {
        return studentRepository.findById(student.getId()).get();
    }

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.deleteById(student.getId());
    }

}