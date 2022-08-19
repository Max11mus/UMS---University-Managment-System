package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class studentsRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> findStudents(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        if (pageNumber<0 || pageSize <1)
            return ResponseEntity.badRequest().body("Must be pageNumber > 0 and pageSize < 1");

        List<StudentDto> studentDtos = studentService.findStudentsPageable(pageNumber,pageSize);
        studentDtos.forEach(s -> s.setHashedPassword("pa$$word"));

        return ResponseEntity.ok().body(studentDtos);
    }



}
