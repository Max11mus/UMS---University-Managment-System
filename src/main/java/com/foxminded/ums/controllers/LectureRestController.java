package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.LectureDto;
import com.foxminded.ums.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/lectures")
public class LectureRestController {

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LectureDto> findLecture(@PathVariable String id) {
        UUID lectureId = UUID.fromString(id);

        LectureDto lectureDto = lectureService.findLecture(lectureId);

        return ResponseEntity.ok().body(lectureDto);
    }

}
