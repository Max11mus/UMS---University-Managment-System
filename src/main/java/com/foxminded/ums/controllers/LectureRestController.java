package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.LectureDto;
import com.foxminded.ums.exeptions.LectureIlegalUuidException;
import com.foxminded.ums.exeptions.LectureNotFoundException;
import com.foxminded.ums.service.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/lectures")
public class LectureRestController {

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LectureDto> findLecture(@PathVariable String id) {
        UUID lectureId = null;
        try {
            lectureId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new LectureIlegalUuidException(id + " isn't correct Lecture UUID. See RFC 4122 - 4.1. Format", e);
        }

        LectureDto lectureDto = null;
        try {
            lectureDto = lectureService.findLecture(lectureId);
        } catch (NoSuchElementException e) {
            throw new LectureNotFoundException("Lecture with ID: " + id + " not found", e);
        }

            return ResponseEntity.ok().body(lectureDto);
    }

}
