package com.foxminded.ums.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.ums.dto.TimeTableUnitDto;
import com.foxminded.ums.service.TimeTableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {TimeTableRestController.class})
class TimeTableRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TimeTableService timeTableService;

    @Test
    void getTimeTableForStudent_mustReturnStatus200AndTimeTableUnitDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7");

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDay = "2022-06-10";
        String endDay = "2022-06-11";
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        String uriPath = "/timetable/student/" + studentId.toString();
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson = "[" +
                "{" +
                    "\"id\":\"9001d675-3591-4971-a636-11aa64a7a25a\"," +
                    "\"location\":{" +
                        "\"id\":\"28cc17b7-6539-4ca4-9e7a-4386616b0166\"," +
                        "\"address\":\"69497 Considine Isle Apt. 813\\rHandfurt, Virginia, 95523-7832\\rUSA\"" +
                                "}," +
                    "\"lecture\":{" +
                        "\"id\":\"4b0c8869-989c-4131-8b93-36a224886b9a\"," +
                            "\"teacher\":{" +
                                "\"id\":\"6e1e9867-4670-4520-8b85-7c195e72bd6c\"," +
                                "\"name\":\"Donna\"," +
                                "\"surname\":\"Cohen\"" +
                                        "}" +
                            "}," +
                    "\"begin\":\"2022-06-10T09:20:00\"," +
                    "\"end\":\"2022-06-10T11:20:00\"" +
                "}" +
                "]";

        TimeTableUnitDto[] timeTableUnitDtosArray = objectMapper.readValue(expectedJson, TimeTableUnitDto[].class);
        timeTableUnitDtosArray[0].setGroups(null);
        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>(Arrays.asList(timeTableUnitDtosArray));

        when(timeTableService.findByPeriodForStudent(studentId,startDayDate,endDayDate)).thenReturn(timeTableUnitDtos);

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

        InOrder serviceCallsOrder = Mockito.inOrder(timeTableService);
        serviceCallsOrder.verify(timeTableService).findByPeriodForStudent(studentId, startDayDate, endDayDate);
        serviceCallsOrder.verifyNoMoreInteractions();
    }

    @Test
    void getTimeTableForTeacher_mustReturnStatus200AndTimeTableUnitDtosInJsonFormat_whenGetMethod() throws Exception {
        //given
        UUID studentId = UUID.fromString("6e1e9867-4670-4520-8b85-7c195e72bd6c");

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDay = "2022-06-10";
        String endDay = "2022-06-11";
        LocalDate startDayDate = LocalDate.parse(startDay, formatter);
        LocalDate endDayDate = LocalDate.parse(endDay, formatter);

        String uriPath = "/timetable/teacher/" + studentId.toString();
        HttpStatus expectedHttpStatus = HttpStatus.OK;
        String expectedJson = "[" +
                "{" +
                    "\"id\":\"9001d675-3591-4971-a636-11aa64a7a25a\"," +
                    "\"location\":{" +
                        "\"id\":\"28cc17b7-6539-4ca4-9e7a-4386616b0166\"," +
                        "\"address\":\"69497 Considine Isle Apt. 813\\rHandfurt, Virginia, 95523-7832\\rUSA\"" +
                            "}," +
                    "\"lecture\":{" +
                        "\"id\":\"4b0c8869-989c-4131-8b93-36a224886b9a\"" +
                            "}," +
                    "\"begin\":\"2022-06-10T09:20:00\"," +
                    "\"end\":\"2022-06-10T11:20:00\"" +
                "}" +
                "]";

        TimeTableUnitDto[] timeTableUnitDtosArray = objectMapper.readValue(expectedJson, TimeTableUnitDto[].class);
        timeTableUnitDtosArray[0].setGroups(null);
        List<TimeTableUnitDto> timeTableUnitDtos = new ArrayList<>(Arrays.asList(timeTableUnitDtosArray));

        when(timeTableService.findByPeriodForTeacher(studentId,startDayDate,endDayDate)).thenReturn(timeTableUnitDtos);

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

        InOrder serviceCallsOrder = Mockito.inOrder(timeTableService);
        serviceCallsOrder.verify(timeTableService).findByPeriodForTeacher(studentId, startDayDate, endDayDate);
        serviceCallsOrder.verifyNoMoreInteractions();
    }
}