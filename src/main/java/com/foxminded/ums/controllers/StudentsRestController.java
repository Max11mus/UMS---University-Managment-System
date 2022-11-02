package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.exeptions.LectureIlegalUuidException;
import com.foxminded.ums.exeptions.LectureNotFoundException;
import com.foxminded.ums.exeptions.StudentIlegalUuidException;
import com.foxminded.ums.exeptions.StudentNotFoundException;
import com.foxminded.ums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/students")
public class StudentsRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> findStudents(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<StudentDto> studentDtos = studentService.findStudentsPageable(pageable);

        return ResponseEntity.ok().body(studentDtos);
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
        StudentDto addedStudent = studentService.addStudent(studentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDto> findStudent(@PathVariable String id) {
        UUID studentId = null;
        try {
            studentId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new LectureIlegalUuidException(id + " isn't correct Student UUID. See RFC 4122 - 4.1. Format", e);
        }

        StudentDto studentDto = null;
        try {
            studentDto = studentService.findStudent(studentId);
        } catch (NoSuchElementException e) {
            throw new StudentNotFoundException("Student with ID: " + id + " not found", e);
        }

        return ResponseEntity.ok().body(studentDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto, @PathVariable String id) {
        UUID studentId = UUID.fromString(id);
        studentDto.setId(studentId);
        StudentDto updatedStudent = studentService.updateStudent(studentDto);

        return ResponseEntity.ok().body(updatedStudent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable String id) {
        UUID studentId = UUID.fromString(id);
        studentService.deleteStudent(studentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
