package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.service.TeacherService;
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
@RequestMapping(value = "/teachers")
public class TeachersRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TeacherDto>> findTeachers(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<TeacherDto> teacherDtos = teacherService.findTeachersPageable(pageable);

        return ResponseEntity.ok().body(teacherDtos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TeacherDto> addTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        TeacherDto addedTeacher = teacherService.addTeacher(teacherDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedTeacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeacherDto> findTeacher(@Valid @PathVariable("id") @UUID String id) {
        java.util.UUID teacherId = java.util.UUID.fromString(id);

        TeacherDto teacherDto = teacherService.findTeacher(teacherId);

        return ResponseEntity.ok().body(teacherDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TeacherDto> updateTeacher(@Valid @RequestBody TeacherDto teacherDto,
                                                    @Valid @PathVariable("id") @UUID String id) {
        java.util.UUID teacherId = java.util.UUID.fromString(id);
        teacherDto.setId(teacherId);

        TeacherDto updatedTeacher = teacherService.updateTeacher(teacherDto);

        return ResponseEntity.ok().body(updatedTeacher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<TeacherDto> deleteTeacher(@Valid @PathVariable("id") @UUID String id) {
        java.util.UUID teacherId = java.util.UUID.fromString(id);
        teacherService.deleteTeacher(teacherId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
