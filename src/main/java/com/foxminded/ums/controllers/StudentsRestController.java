package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.service.StudentService;
import com.foxminded.ums.validation.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/students")
public class StudentsRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentDto>> findStudents(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<StudentDto> studentDtos = studentService.findStudentsPageable(pageable);

        return ResponseEntity.ok().body(studentDtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto addedStudent = studentService.addStudent(studentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentDto> findStudent(@Valid @PathVariable("id") @UUID String id) {
        java.util.UUID studentId = java.util.UUID.fromString(id);

        StudentDto studentDto = studentService.findStudent(studentId);

        return ResponseEntity.ok().body(studentDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto,
                                                    @Valid @PathVariable("id") @UUID String id) {
        java.util.UUID studentId = java.util.UUID.fromString(id);
        studentDto.setId(studentId);
        StudentDto updatedStudent = studentService.updateStudent(studentDto);

        return ResponseEntity.ok().body(updatedStudent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<StudentDto> deleteStudent(@Valid @PathVariable("id") @UUID String id) {
        java.util.UUID studentId = java.util.UUID.fromString(id);
        studentService.deleteStudent(studentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
