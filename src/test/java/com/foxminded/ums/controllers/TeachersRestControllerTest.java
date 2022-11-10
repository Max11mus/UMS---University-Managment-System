package com.foxminded.ums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.ums.dto.TeacherDto;
import com.foxminded.ums.service.TeacherService;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {TeachersRestController.class})
class TeachersRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeacherService teacherService;

    @SpyBean
    private ClockBean clockBean;

    @BeforeEach
    void setFixedClockForTests() {
        String fixedTestInstantTime = "2022-11-11T11:11:11Z";
        String fixedTestZone = "Etc/UTC";
        clockBean.setClock(Clock.fixed(Instant.parse(fixedTestInstantTime), ZoneId.of(fixedTestZone)));
    }

    @Test
    void findTeachers_mustReturnStatus200AndExistedTeacherDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        String uriPath = "/teachers";
        Pageable pageable = PageRequest.of(0,5);
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson ="[" +
                "{" +
                    "\"id\":\"210dd67b-7810-4edf-98be-e9a2cffe6290\"," +
                    "\"name\":\"Brian\"," +
                    "\"surname\":\"Stafford\"," +
                    "\"birthDate\":\"1993-01-03\"," +
                    "\"timeZone\":\"GMT-05:00\"," +
                    "\"login\":\"b.stafford\"," +
                    "\"email\":\"s.stafford@gmail.com\"," +
                    "\"avatarPath\":\"\"," +
                    "\"academicDegree\":\"Master of Science \"," +
                    "\"employmentDate\":\"2021-08-04\"" +
                "}" +
                "]";

        TeacherDto[] teacherDtoArray = objectMapper.readValue(expectedJson, TeacherDto[].class);
        List<TeacherDto> teacherDtos = new ArrayList<>(Arrays.asList(teacherDtoArray));

        when(teacherService.findTeachersPageable(pageable)).thenReturn(teacherDtos);

        //when
        ResultActions actualResult = mockMvc.perform(get(uriPath)
                .param("page", String.valueOf(0))
                .param("size", String.valueOf(5)));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);

        InOrder serviceCallsOrder = Mockito.inOrder(teacherService);
        serviceCallsOrder.verify(teacherService).findTeachersPageable(pageable);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void addTeacher_mustReturnStatus201AndAddedTeacherDtoInJsonFormat_whenPostMethod() throws Exception {
        //given
        UUID teacherId = UUID.fromString("210dd67b-7810-4edf-98be-e9a2cffe6290");
        String uriPath = "/teachers";

        HttpStatus expectedHttpStatus = HttpStatus.CREATED;
        String inputJson ="{" +
                "\"name\":\"Brian\"," +
                "\"surname\":\"Stafford\"," +
                "\"birthDate\":\"1993-01-03\"," +
                "\"timeZone\":\"GMT-05:00\"," +
                "\"login\":\"b.stafford\"," +
                "\"email\":\"s.stafford@gmail.com\"," +
                "\"hashedPassword\":\"\"," +
                "\"avatarPath\":\"\"," +
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";
        String expectedJson ="{" +
                "\"id\":\"210dd67b-7810-4edf-98be-e9a2cffe6290\"," +
                "\"name\":\"Brian\"," +
                "\"surname\":\"Stafford\"," +
                "\"birthDate\":\"1993-01-03\"," +
                "\"timeZone\":\"GMT-05:00\"," +
                "\"login\":\"b.stafford\"," +
                "\"email\":\"s.stafford@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";

        TeacherDto inputTeacherDto = objectMapper.readValue(inputJson, TeacherDto.class);
        TeacherDto outputTeacherDto = objectMapper.readValue(expectedJson, TeacherDto.class);

        when(teacherService.addTeacher(inputTeacherDto)).thenReturn(outputTeacherDto);

        //when
        ResultActions actualResult = mockMvc.perform(post(uriPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson));

        //then
        String actualJson = actualResult
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);

        InOrder serviceCallsOrder = Mockito.inOrder(teacherService);
        serviceCallsOrder.verify(teacherService).addTeacher(inputTeacherDto);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void findTeacher_mustReturnStatus200AndExistedTeacherDtoInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID teacherId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String uriPath = "/teachers/" + teacherId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson ="{" +
                "\"id\":\"210dd67b-7810-4edf-98be-e9a2cffe6290\"," +
                "\"name\":\"Brian\"," +
                "\"surname\":\"Stafford\"," +
                "\"birthDate\":\"1993-01-03\"," +
                "\"timeZone\":\"GMT-05:00\"," +
                "\"login\":\"b.stafford\"," +
                "\"email\":\"s.stafford@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";

        TeacherDto teacherDto = teacherDto = objectMapper.readValue(expectedJson, TeacherDto.class);

        when(teacherService.findTeacher(teacherId)).thenReturn(teacherDto);

        //when
        ResultActions actualResult = mockMvc.perform(get(uriPath));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);

        InOrder serviceCallsOrder = Mockito.inOrder(teacherService);
        serviceCallsOrder.verify(teacherService).findTeacher(teacherId);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void updateTeacher_mustReturnStatus200AndUpdatedTeacherDtoInJsonFormat_whenPutMethod() throws Exception {
        //given
        UUID teacherId = UUID.fromString("210dd67b-7810-4edf-98be-e9a2cffe6290");
        String uriPath = "/teachers/" + teacherId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String inputJson ="{" +
                "\"id\":\"210dd67b-7810-4edf-98be-e9a2cffe6290\"," +
                "\"name\":\"Brian\"," +
                "\"surname\":\"Stafford\"," +
                "\"birthDate\":\"1993-01-03\"," +
                "\"timeZone\":\"GMT-05:00\"," +
                "\"login\":\"b.stafford\"," +
                "\"email\":\"s.stafford@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";
        String expectedJson ="{" +
                "\"id\":\"210dd67b-7810-4edf-98be-e9a2cffe6290\"," +
                "\"name\":\"Brian\"," +
                "\"surname\":\"Stafford\"," +
                "\"birthDate\":\"1993-01-03\"," +
                "\"timeZone\":\"GMT-05:00\"," +
                "\"login\":\"b.stafford\"," +
                "\"email\":\"s.stafford@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";

        TeacherDto inputTeacherDto = objectMapper.readValue(inputJson, TeacherDto.class);
        TeacherDto outputTeacherDto = objectMapper.readValue(expectedJson, TeacherDto.class);

        when(teacherService.updateTeacher(inputTeacherDto)).thenReturn(outputTeacherDto);

        //when
        ResultActions actualResult = mockMvc.perform(put(uriPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);

        InOrder serviceCallsOrder = Mockito.inOrder(teacherService);
        serviceCallsOrder.verify(teacherService).updateTeacher(inputTeacherDto);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void deleteTeacher_mustReturnStatus204_whenDeleteMethod() throws Exception {
        //given
        UUID teacherId = UUID.fromString("f210dd67b-7810-4edf-98be-e9a2cffe6290");
        String uriPath = "/teachers/" + teacherId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.NO_CONTENT;

        //when
        ResultActions actualResult = mockMvc.perform(delete(uriPath));

        //then
        actualResult
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        InOrder serviceCallsOrder = Mockito.inOrder(teacherService);
        serviceCallsOrder.verify(teacherService).deleteTeacher(teacherId);
        serviceCallsOrder.verifyNoMoreInteractions();
    }
}