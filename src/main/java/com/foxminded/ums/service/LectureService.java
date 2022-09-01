package com.foxminded.ums.service;

import com.foxminded.ums.dto.LectureDto;
import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.entities.Lecture;
import com.foxminded.ums.entities.Student;
import com.foxminded.ums.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LectureService {
    @Autowired
    LectureRepository lectureRepository;

    public LectureDto findLecture(UUID id){
        LectureDto lectureDto = convertToDto(lectureRepository.findById(id).get());
        lectureDto.getTeacher().setHashedPassword(null);
        return lectureDto;
    }

    private LectureDto convertToDto(Lecture lecture) {
        LectureDto dto = new LectureDto();

        dto.setId(lecture.getId());
        dto.setDescription(lecture.getDescription());
        dto.setSubject(lecture.getSubject());
        dto.setTeacher(lecture.getTeacher());
        dto.setTopic(lecture.getTopic());

        return  dto;
    }

}
