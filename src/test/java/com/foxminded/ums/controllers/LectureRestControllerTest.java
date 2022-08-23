package com.foxminded.ums.controllers;

import com.foxminded.ums.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {LectureRestController.class})
class LectureRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureRestController lectureRestController;

    @Test
    void findLecture_Must() {
        //given
        UUID expectStudentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");

        //when
        Student actualStudent = studentRepository.findById(expectStudentId).get();

        //then
        assertNotNull(actualStudent);
        assertEquals(expectStudentId, actualStudent.getId());

    }
}