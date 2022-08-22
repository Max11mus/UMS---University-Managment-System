package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/students")
public class StudentsRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> findStudents(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<StudentDto> studentDtos = studentService.findStudentsPageable(pageNumber,pageSize);
        studentDtos.forEach(s -> s.setHashedPassword("pa$$word"));

        return ResponseEntity.ok().body(studentDtos);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> addStudent(@RequestBody StudentDto studentDto) {
        UUID studentId = studentService.addStudent(studentDto).getId();
        StudentDto addedStudent = studentService.findStudent(studentId);

        return ResponseEntity.ok().body(addedStudent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> findStudent(@PathVariable String id) {
        UUID studentId = UUID.fromString(id);

        StudentDto studentDto = studentService.findStudent(studentId);
        studentDto.setHashedPassword("pa$$word");

        return ResponseEntity.ok().body(studentDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updateStudent(@RequestBody StudentDto studentDto, @PathVariable String id) {
        UUID studentId = UUID.fromString(id);
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);

        StudentDto updatedStudent = studentService.findStudent(studentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedStudent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteStudent(@PathVariable String id) {
        UUID studentId = UUID.fromString(id);
        studentService.deleteStudent(studentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
