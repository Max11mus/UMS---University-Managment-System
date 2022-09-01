package com.foxminded.ums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.ums.dto.StudentDto;
import com.foxminded.ums.service.LectureService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/integration-test.properties")
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = "/clear_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class StudentsRestControllerIntegrationTest extends BaseIT{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(value = "/insert_only_students.sql")
    void findStudents_mustReturnStatus200AndExistedStudentDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        String uriPath = "/students";
        Pageable pageable = PageRequest.of(0,5);
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson ="[" +
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
                "}," +
                "{" +
                    "\"id\":\"8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7\"," +
                    "\"name\":\"Frank\"," +
                    "\"surname\":\"Parker\"," +
                    "\"birthDate\":\"1985-07-15\"," +
                    "\"timeZone\":\"UTC\"," +
                    "\"login\":\"f_parker\"," +
                    "\"email\":\"f.parker@gmail.com\"," +
                    "\"avatarPath\":\"\"," +
                    "\"dropoutDate\":\"2022-07-07\"," +
                    "\"enrollDate\":\"2022-01-05\"" +
                "}," +
                "{" +
                    "\"id\":\"c3e47148-adcf-4ee3-81f6-6b79b83a41ca\"," +
                    "\"name\":\"Kathy\"," +
                    "\"surname\":\"Mizer\"," +
                    "\"birthDate\":\"1977-08-06\"," +
                    "\"timeZone\":\"GMT+09:00\"," +
                    "\"login\":\"Kathy$Mizer\"," +
                    "\"email\":\"Kathy$Mizer@gmail.com\"," +
                    "\"avatarPath\":\"\"," +
                    "\"dropoutDate\":\"2022-07-07\"," +
                    "\"enrollDate\":\"2022-01-05\"" +
                "}" +
                "]";

        StudentDto[] studentDtoArray = objectMapper.readValue(expectedJson, StudentDto[].class);
        List<StudentDto> studentDtos = new ArrayList<>(Arrays.asList(studentDtoArray));

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
    @Sql(value = "/insert_only_students.sql")
    void addStudent_mustReturnStatus201AndAddedStudentDtoInJsonFormat_whenPostMethod() throws Exception {
        //given
        String uriPath = "/students";

        HttpStatus expectedHttpStatus = HttpStatus.CREATED;
        String inputJson ="{" +
                "\"name\":\"Mike\"," +
                "\"surname\":\"Patton\"," +
                "\"birthDate\":\"1974-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mike_Patton\"," +
                "\"email\":\"Mike_Patton@gmail.com\"," +
                "\"hashedPassword\":\" \"," +
                "\"avatarPath\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";
        String expectedJson ="{" +
                "\"name\":\"Mike\"," +
                "\"surname\":\"Patton\"," +
                "\"birthDate\":\"1974-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mike_Patton\"," +
                "\"email\":\"Mike_Patton@gmail.com\"," +
                "\"hashedPassword\":\" \"," +
                "\"avatarPath\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";

        StudentDto inputStudentDto = objectMapper.readValue(inputJson, StudentDto.class);
        StudentDto expectedDto = objectMapper.readValue(expectedJson, StudentDto.class);

        //when
        ResultActions actualResult = mockMvc.perform(post(uriPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson));

        //then
        String actualJson = actualResult
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        StudentDto actualDto =  objectMapper.readValue(actualJson, StudentDto.class);
        expectedDto.setId(actualDto.getId());

        assertEquals(expectedDto, actualDto);
    }

    @Test
    @Sql(value = "/insert_only_students.sql")
    void findStudent_mustReturnStatus200AndExistedStudentDtoInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String uriPath = "/students/" + studentId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson ="{" +
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
    @Sql(value = "/insert_only_students.sql")
    void updateStudent_mustReturnStatus200AndUpdatedStudentDtoInJsonFormat_whenPutMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String uriPath = "/students/" + studentId.toString();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String inputJson ="{" +
                "\"id\":\"f57e0ffe-6118-44a8-b39d-b2da86b65aff\"," +
                "\"name\":\"Mary\"," +
                "\"surname\":\"Carpenter\"," +
                "\"birthDate\":\"1973-01-11\"," +
                "\"timeZone\":\"GMT-08:00\"," +
                "\"login\":\"Mary_Carpenter\"," +
                "\"email\":\"Mary_Carpenter@gmail.com\"," +
                "\"hashedPassword\":\" \"," +
                "\"avatarPath\":\"\"," +
                "\"dropoutDate\":\"2022-07-07\"," +
                "\"enrollDate\":\"2022-01-05\"" +
                "}";
        String expectedJson ="{" +
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

        StudentDto inputStudentDto = objectMapper.readValue(inputJson, StudentDto.class);
        StudentDto outputStudentDto = objectMapper.readValue(expectedJson, StudentDto.class);

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
    @Sql(value = "/insert_only_students.sql")
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
    }
}