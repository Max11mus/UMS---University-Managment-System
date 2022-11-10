package com.foxminded.ums.controllers;

import com.foxminded.ums.dto.LectureDto;
import com.foxminded.ums.service.LectureService;
import com.foxminded.ums.validation.ValidUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(value = "/lectures")
public class LectureRestController {

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<LectureDto> findLecture(@Valid @PathVariable("id") @ValidUUID String id ) {
        UUID lectureId = UUID.fromString(id);

        LectureDto lectureDto = lectureService.findLecture(lectureId);

        return ResponseEntity.ok().body(lectureDto);
    }

}
