package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.service.TeacherService;
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
@RequestMapping(value = "/teachers")
public class TeachersRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> findTeachers(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        List<TeacherDto> teacherDtos = teacherService.findTeachersPageable(pageNumber,pageSize);
        teacherDtos.forEach(s -> s.setHashedPassword("pa$$word"));

        return ResponseEntity.ok().body(teacherDtos);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> addTeacher(@RequestBody TeacherDto teacherDto) {
        UUID teacherId = teacherService.addTeacher(teacherDto).getId();
        TeacherDto addedTeacher = teacherService.findTeacher(teacherId);

        return ResponseEntity.ok().body(addedTeacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> findTeacher(@PathVariable String id) {
        UUID teacherId = UUID.fromString(id);

        TeacherDto teacherDto = teacherService.findTeacher(teacherId);
        teacherDto.setHashedPassword("pa$$word");

        return ResponseEntity.ok().body(teacherDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable String id) {
        UUID teacherId = UUID.fromString(id);
        teacherDto.setId(teacherId);
        teacherService.updateTeacher(teacherDto);

        TeacherDto updatedTeacher = teacherService.findTeacher(teacherId);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTeacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> deleteTeacher(@PathVariable String id) {
        UUID teacherId = UUID.fromString(id);
        teacherService.deleteTeacher(teacherId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
