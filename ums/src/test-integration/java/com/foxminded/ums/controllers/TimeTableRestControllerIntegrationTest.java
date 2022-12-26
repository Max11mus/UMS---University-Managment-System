package com.foxminded.ums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.ums.validation.ClockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/integration-test.properties")
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = "/clear_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TimeTableRestControllerIntegrationTest extends BaseIT{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private ClockBean clockBean;

    @BeforeEach
    void setFixedClockForTests() {
        String fixedTestInstantTime = "2022-11-11T11:11:11Z";
        String fixedTestZone = "Etc/UTC";
        clockBean.setClock(Clock.fixed(Instant.parse(fixedTestInstantTime), ZoneId.of(fixedTestZone)));
    }

    @Sql(value = "/insert_all_data.sql")
    @Test
    void getTimeTableForStudent_mustReturnStatus200AndTimeTableUnitDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7");

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDay = "2022-06-05";
        String endDay = "2022-06-06";
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        String uriPath = "/timetable/student/" + studentId.toString();
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson =
                "[" +
                        "{" +
                            "\"id\":\"639b9cbe-3654-40d6-ae7f-aaf651c85950\"," +
                                "\"location\":{" +
                                    "\"id\":\"e8f01c7f-a1e5-4fa0-8bd4-fa9203f01823\"," +
                                    "\"address\":\"853 Upton Crossing Suite 560\\n" +
                                        "East Andreannemouth, Illinois, 36125-7843\\nUSA\"}," +
                            "\"lecture\":{" +
                                "\"id\":\"b1f7401e-d295-4dbc-9be9-7aed9e45354a\"," +
                                    "\"teacher\":{" +
                                        "\"id\":\"d87a90ba-1237-419a-b199-19dc389b4bbf\"," +
                                        "\"name\":\"Richard \"," +
                                        "\"surname\":\"Ortiz\"}" +
                                    "}," +
                            "\"begin\":\"2022-06-05T09:20:00\"," +
                            "\"end\":\"2022-06-05T11:20:00\"" +
                        "}," +
                        "{" +
                            "\"id\":\"9c1a0a40-b398-4c83-a571-1aaeb18e5414\"," +
                                "\"location\":{" +
                                    "\"id\":\"28cc17b7-6539-4ca4-9e7a-4386616b0166\"," +
                                    "\"address\":\"69497 Considine Isle Apt. 813\\n" +
                                        "Handfurt, Virginia, 95523-7832\\nUSA\"}," +
                            "\"lecture\":{" +
                                "\"id\":\"1876ecb7-3388-4a97-9821-e7c4f8c5a2ff\"," +
                                "\"teacher\":{" +
                                    "\"id\":\"6e1e9867-4670-4520-8b85-7c195e72bd6c\"," +
                                    "\"name\":\"Donna\"," +
                                    "\"surname\":\"Cohen\"" +
                            "}" +
                        "}," +
                            "\"begin\":\"2022-06-05T09:20:00\"," +
                            "\"end\":\"2022-06-05T11:20:00\"" +
                        "}" +
                "]";


        //when
        ResultActions actualResult = mockMvc.perform(get(uriPath)
                .param("startDay", startDay)
                .param("endDay", endDay));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }

    @Sql(value = "/insert_all_data.sql")
    @Test
    void getTimeTableForTeacher_mustReturnStatus200AndTimeTableUnitDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("6e1e9867-4670-4520-8b85-7c195e72bd6c");

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDay = "2022-08-01";
        String endDay = "2022-09-01";
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        String uriPath = "/timetable/teacher/" + studentId.toString();
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson =
                "[" +
                        "{" +
                            "\"id\":\"0cc96b2a-224e-402f-82d0-4c56e684c2e5\"," +
                            "\"location\":{" +
                                "\"id\":\"28cc17b7-6539-4ca4-9e7a-4386616b0166\"," +
                                "\"address\":\"69497 Considine Isle Apt. 813\\n" +
                                    "Handfurt, Virginia, 95523-7832\\nUSA\"}," +
                            "\"lecture\":{" +
                                "\"id\":\"b733b94c-5c59-4c3d-a04f-c308afc8e237\"}," +
                            "\"begin\":\"2022-08-03T09:20:00\"," +
                            "\"end\":\"2022-08-03T11:20:00\"" +
                        "}" +
                "]";

        //when
        ResultActions actualResult = mockMvc.perform(get(uriPath)
                .param("startDay", startDay)
                .param("endDay", endDay));

        //then
        String actualJson = actualResult
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(expectedJson, actualJson);
    }
}