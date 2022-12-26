package com.foxminded.ums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.ums.dto.LectureDto;
import com.foxminded.ums.service.LectureService;
import com.foxminded.ums.validation.ClockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {LectureRestController.class})
class LectureRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LectureService lectureService;

    @SpyBean
    private ClockBean clockBean;

    @BeforeEach
    void setFixedClockForTests() {
        String fixedTestInstantTime = "2022-11-11T11:11:11Z";
        String fixedTestZone = "Etc/UTC";
        clockBean.setClock(Clock.fixed(Instant.parse(fixedTestInstantTime), ZoneId.of(fixedTestZone)));
    }

    @Test
    void findLecture_mustReturnStatus200AndExistedLectureDtoInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID lectureId = UUID.fromString("22bfa2c0-9022-49b6-ac34-c46cffe9677e");
        String uriPath = "/lectures/" + lectureId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson ="{" +
                "\"id\":\"22bfa2c0-9022-49b6-ac34-c46cffe9677e\"," +
                "\"topic\":\"PostgreSQL\"," +
                "\"description\":\"PostgreSQL is a powerful, open source object-relational database system that uses " +
                    "and extends the SQL language combined with many features that safely store and scale the most " +
                    "complicated data workloads. The origins of PostgreSQL date back to 1986 as part of the POSTGRES " +
                    "project at the University of California at Berkeley and has more than 30 years of active development " +
                    "on the core platform.\"," +
                "\"teacher\":" +
                    "{\"id\":\"6e1e9867-4670-4520-8b85-7c195e72bd6c\"," +
                    "\"name\":\"Donna\"," +
                    "\"surname\":\"Cohen\"," +
                    "\"birthDate\":\"2003-05-07\"" +
                    ",\"timeZone\":\"GMT+01:00\"," +
                    "\"login\":\"d.cohen\"," +
                    "\"email\":\"d.cohen@gmail.com\"," +
                    "\"avatarPath\":\"\"," +
                    "\"hashedPassword\":\"7f0485c93e4328b69b0b5f03a2b37cb73ef9838e9f3fdeb74c20474b1ea75e45\"," +
                    "\"academicDegree\":\"Master of Science \"," +
                    "\"employmentDate\":\"2021-08-04\"}," +
                "\"subject\":{\"id\":\"2b41e5c2-76ce-46e1-895f-c5a6e588de64\"," +
                    "\"name\":\"SQL (Structured Query Language)\"," +
                    "\"description\":\"SQL (Structured Query Language) is a domain-specific programming language " +
                        "designed for managing data held in a relational database management system (RDBMS).\"}" +
                "}";

        LectureDto lectureDto = lectureDto = objectMapper.readValue(expectedJson, LectureDto.class);

        when(lectureService.findLecture(lectureId)).thenReturn(lectureDto);

        //when
        ResultActions actualResult = mockMvc.perform(get(uriPath));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();


        assertEquals(expectedJson, actualJson);

        InOrder serviceCallsOrder = Mockito.inOrder(lectureService);
        serviceCallsOrder.verify(lectureService).findLecture(lectureId);
        serviceCallsOrder.verifyNoMoreInteractions();
    }
}