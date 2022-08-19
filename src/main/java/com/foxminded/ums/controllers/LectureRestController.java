package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.LectureDto;
import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.service.LectureService;
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
@RequestMapping(value = "/lectures")
public class LectureRestController {

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> findStudent(@PathVariable String id) {
        UUID lectureId = UUID.fromString(id);

        LectureDto lectureDto = lectureService.findLecture(lectureId);

        return ResponseEntity.ok().body(lectureDto);
    }

}
