package com.foxminded.ums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.service.StudentService;
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
@WebMvcTest(controllers = {StudentsRestController.class})
class StudentsRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @SpyBean
    private ClockBean clockBean;

    @BeforeEach
    void setFixedClockForTests() {
        String fixedTestInstantTime = "2022-11-11T11:11:11Z";
        String fixedTestZone = "Etc/UTC";
        clockBean.setClock(Clock.fixed(Instant.parse(fixedTestInstantTime), ZoneId.of(fixedTestZone)));
    }

    @Test
    void findStudents_mustReturnStatus200AndExistedStudentDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        String uriPath = "/students";
        Pageable pageable = PageRequest.of(0, 5);
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson = "[" +
                "{" +
                "\"id\":\"f57e0ffe-6118-44a8-b39d-b2da86b65aff\"," +
                "\"name\":\"Mary\"," +
                "\"surname\":\"Carpenter\"," +
                "\"birthDate\":\"1973-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mary_Carpenter\"," +
                "\"email\":\"Mary_Carpenter@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}" +
                "]";

        StudentDto[] studentDtoArray = objectMapper.readValue(expectedJson, StudentDto[].class);
        List<StudentDto> studentDtos = new ArrayList<>(Arrays.asList(studentDtoArray));

        when(studentService.findStudentsPageable(pageable)).thenReturn(studentDtos);

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

        InOrder serviceCallsOrder = Mockito.inOrder(studentService);
        serviceCallsOrder.verify(studentService).findStudentsPageable(pageable);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void addStudent_mustReturnStatus201AndAddedStudentDtoInJsonFormat_whenPostMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String uriPath = "/students";

        HttpStatus expectedHttpStatus = HttpStatus.CREATED;
        String inputJson = "{" +
                "\"name\":\"Mary\"," +
                "\"surname\":\"Carpenter\"," +
                "\"birthDate\":\"1973-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mary_Carpenter\"," +
                "\"email\":\"Mary_Carpenter@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";
        String expectedJson = "{" +
                "\"id\":\"f57e0ffe-6118-44a8-b39d-b2da86b65aff\"," +
                "\"name\":\"Mary\"," +
                "\"surname\":\"Carpenter\"," +
                "\"birthDate\":\"1973-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mary_Carpenter\"," +
                "\"email\":\"Mary_Carpenter@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";

        StudentDto inputStudentDto = objectMapper.readValue(inputJson, StudentDto.class);
        StudentDto outputStudentDto = objectMapper.readValue(expectedJson, StudentDto.class);

        when(studentService.addStudent(inputStudentDto)).thenReturn(outputStudentDto);

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

        InOrder serviceCallsOrder = Mockito.inOrder(studentService);
        serviceCallsOrder.verify(studentService).addStudent(inputStudentDto);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void findStudent_mustReturnStatus200AndExistedStudentDtoInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String uriPath = "/students/" + studentId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson = "{" +
                "\"id\":\"f57e0ffe-6118-44a8-b39d-b2da86b65aff\"," +
                "\"name\":\"Mary\"," +
                "\"surname\":\"Carpenter\"," +
                "\"birthDate\":\"1973-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mary_Carpenter\"," +
                "\"email\":\"Mary_Carpenter@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";

        StudentDto studentDto = studentDto = objectMapper.readValue(expectedJson, StudentDto.class);

        when(studentService.findStudent(studentId)).thenReturn(studentDto);

        //when
        ResultActions actualResult = mockMvc.perform(get(uriPath));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);

        InOrder serviceCallsOrder = Mockito.inOrder(studentService);
        serviceCallsOrder.verify(studentService).findStudent(studentId);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void updateStudent_mustReturnStatus200AndUpdatedStudentDtoInJsonFormat_whenPutMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String uriPath = "/students/" + studentId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String inputJson = "{" +
                "\"id\":\"f57e0ffe-6118-44a8-b39d-b2da86b65aff\"," +
                "\"name\":\"Mary\"," +
                "\"surname\":\"Carpenter\"," +
                "\"birthDate\":\"1973-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mary_Carpenter\"," +
                "\"email\":\"Mary_Carpenter@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";
        String expectedJson = "{" +
                "\"id\":\"f57e0ffe-6118-44a8-b39d-b2da86b65aff\"," +
                "\"name\":\"Mary\"," +
                "\"surname\":\"Carpenter\"," +
                "\"birthDate\":\"1973-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mary_Carpenter\"," +
                "\"email\":\"Mary_Carpenter@gmail.com\"," +
                "\"avatarPath\":\"\"," +
                "\"hashedPassword\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";

        StudentDto inputStudentDto = objectMapper.readValue(inputJson, StudentDto.class);
        StudentDto outputStudentDto = objectMapper.readValue(expectedJson, StudentDto.class);

        when(studentService.updateStudent(inputStudentDto)).thenReturn(outputStudentDto);

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

        InOrder serviceCallsOrder = Mockito.inOrder(studentService);
        serviceCallsOrder.verify(studentService).updateStudent(inputStudentDto);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void deleteStudent_mustReturnStatus204_whenDeleteMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String uriPath = "/students/" + studentId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.NO_CONTENT;

        //when
        ResultActions actualResult = mockMvc.perform(delete(uriPath));

        //then
        actualResult
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        InOrder serviceCallsOrder = Mockito.inOrder(studentService);
        serviceCallsOrder.verify(studentService).deleteStudent(studentId);
        serviceCallsOrder.verifyNoMoreInteractions();
    }
}