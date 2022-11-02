package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.exeptions.TeacherIlegalUuidException;
import com.foxminded.ums.exeptions.TeacherNotFoundException;
import com.foxminded.ums.service.TeacherService;
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
@RequestMapping(value = "/teachers")
public class TeachersRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> findTeachers(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<TeacherDto> teacherDtos = teacherService.findTeachersPageable(pageable);

        return ResponseEntity.ok().body (teacherDtos);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacherDto) {
        TeacherDto addedTeacher = teacherService.addTeacher(teacherDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedTeacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TeacherDto> findTeacher(@PathVariable String id) {
        UUID teacherId = null;
        try {
            teacherId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new TeacherIlegalUuidException(id + " isn't correct Teacher UUID. See RFC 4122 - 4.1. Format", e);
        }

        TeacherDto teacherDto = null;
        try {
            teacherDto = teacherService.findTeacher(teacherId);
        } catch (NoSuchElementException e) {
            throw new TeacherNotFoundException("Teacher with ID: " + id + " not found", e);
        }

        return ResponseEntity.ok().body(teacherDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable String id) {
        UUID teacherId = UUID.fromString(id);
        teacherDto.setId(teacherId);

        TeacherDto updatedTeacher = teacherService.updateTeacher(teacherDto);

        return ResponseEntity.ok().body(updatedTeacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TeacherDto> deleteTeacher(@PathVariable String id) {
        UUID teacherId = UUID.fromString(id);
        teacherService.deleteTeacher(teacherId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
