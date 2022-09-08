package com.foxminded.ums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.dto.TeacherDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/integration-test.properties")
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = "/clear_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TeachersRestControllerIntegrationTest extends BaseIT{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(value = "/insert_only_teachers.sql")
    void findTeachers_mustReturnStatus200AndExistedTeacherDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        String uriPath = "/teachers";
        Pageable pageable = PageRequest.of(0,5);
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson =
                "[" +
                        "{" +
                            "\"id\":\"6e1e9867-4670-4520-8b85-7c195e72bd6c\"," +
                            "\"name\":\"Donna\"," +
                            "\"surname\":\"Cohen\"," +
                            "\"birthDate\":\"2003-05-07\"," +
                            "\"timeZone\":\"GMT+01:00\"," +
                            "\"login\":\"d.cohen\",\"email\":\"d.cohen@gmail.com\"," +
                            "\"avatarPath\":\"\"," +
                            "\"academicDegree\":\"Master of Science \"," +
                            "\"employmentDate\":\"2021-08-04\"" +
                        "}," +
                        "{" +
                            "\"id\":\"210dd67b-7810-4edf-98be-e9a2cffe6290\"," +
                            "\"name\":\"Brian\",\"surname\":\"Stafford\"," +
                            "\"birthDate\":\"1993-01-03\"," +
                            "\"timeZone\":\"GMT-05:00\"," +
                            "\"login\":\"b.stafford\"," +
                            "\"email\":\"s.stafford@gmail.com\"," +
                            "\"avatarPath\":\"\"," +
                            "\"academicDegree\":\"Master of Science \"," +
                            "\"employmentDate\":\"2021-08-04\"" +
                        "}," +
                        "{" +
                            "\"id\":\"d87a90ba-1237-419a-b199-19dc389b4bbf\"," +
                            "\"name\":\"Richard \"," +
                            "\"surname\":\"Ortiz\"," +
                            "\"birthDate\":\"1997-04-18\"," +
                            "\"timeZone\":\"GMT-06:00\"," +
                            "\"login\":\"R_Ortiz\"," +
                            "\"email\":\"R_Ortiz@gmail.com\"," +
                            "\"avatarPath\":\"\"," +
                            "\"academicDegree\":\"Master of Science \"," +
                            "\"employmentDate\":\"2021-08-04\"" +
                        "}" +
                        "]";

        TeacherDto[] teacherDtoArray = objectMapper.readValue(expectedJson, TeacherDto[].class);
        List<TeacherDto> teacherDtos = new ArrayList<>(Arrays.asList(teacherDtoArray));

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
    }

    @Test
    @Sql(value = "/insert_only_teachers.sql")
    void addTeacher_mustReturnStatus201AndAddedTeacherDtoInJsonFormat_whenPostMethod() throws Exception {
        //given
        UUID teacherId = UUID.fromString("210dd67b-7810-4edf-98be-e9a2cffe6290");
        String uriPath = "/teachers";

        HttpStatus expectedHttpStatus = HttpStatus.CREATED;
        String inputJson ="{" +
                "\"name\":\"Brian\"," +
                "\"surname\":\"May\"," +
                "\"birthDate\":\"1993-01-03\"," +
                "\"timeZone\":\"GMT-05:00\"," +
                "\"login\":\"b.may\"," +
                "\"email\":\"b.may@gmail.com\"," +
                "\"hashedPassword\":\" \"," +
                "\"avatarPath\":\"\"," +
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";
        String expectedJson ="{" +
                "\"name\":\"Brian\"," +
                "\"surname\":\"May\"," +
                "\"birthDate\":\"1993-01-03\"," +
                "\"timeZone\":\"GMT-05:00\"," +
                "\"login\":\"b.may\"," +
                "\"email\":\"b.may@gmail.com\"," +
                "\"hashedPassword\":\" \"," +
                "\"avatarPath\":\"\"," +
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";

        TeacherDto inputTeacherDto = objectMapper.readValue(inputJson, TeacherDto.class);
        TeacherDto expectedDto = objectMapper.readValue(expectedJson, TeacherDto.class);

        //when
        ResultActions actualResult = mockMvc.perform(post(uriPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson));

        //then
        String actualJson = actualResult
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        TeacherDto actualDto =  objectMapper.readValue(actualJson, TeacherDto.class);
        expectedDto.setId(actualDto.getId());

        assertEquals(expectedDto, actualDto);
    }

    @Test
    @Sql(value = "/insert_only_teachers.sql")
    void findTeacher_mustReturnStatus200AndExistedTeacherDtoInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID teacherId = UUID.fromString("6e1e9867-4670-4520-8b85-7c195e72bd6c");
        String uriPath = "/teachers/" + teacherId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson =
                "{" +
                        "\"id\":\"6e1e9867-4670-4520-8b85-7c195e72bd6c\","
                        + "\"name\":\"Donna\","
                        + "\"surname\":\"Cohen\","
                        + "\"birthDate\":\"2003-05-07\","
                        + "\"timeZone\":\"GMT+01:00\","
                        + "\"login\":\"d.cohen\",\"email\":\"d.cohen@gmail.com\","
                        + "\"avatarPath\":\"\","
                        + "\"academicDegree\":\"Master of Science \","
                        + "\"employmentDate\":\"2021-08-04\""
                + "}";

        TeacherDto teacherDto = teacherDto = objectMapper.readValue(expectedJson, TeacherDto.class);

        //when
        ResultActions actualResult = mockMvc.perform(get(uriPath));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }

    @Test
    @Sql(value = "/insert_only_teachers.sql")
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
                "\"academicDegree\":\"Master of Science \"," +
                "\"employmentDate\":\"2021-08-04\"" +
                "}";

        TeacherDto inputTeacherDto = objectMapper.readValue(inputJson, TeacherDto.class);
        TeacherDto outputTeacherDto = objectMapper.readValue(expectedJson, TeacherDto.class);

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
    }

    @Test
    @Sql(value = "/insert_only_teachers.sql")
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
    }
}
