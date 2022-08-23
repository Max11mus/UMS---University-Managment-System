package com.foxminded.ums.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "/test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = "/insert_all_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clear_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ддTimeTableUnitRepositoryTest {
    @Autowired
    private TimeTableUnitRepository timeTableUnitRepository;

    @Test
    void findByBeginBetweenForStudent_MustFindTimeTableUnit_WhenPeriodOneDay() {
        //given
        UUID studentUuid = UUID.fromString("8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse("2022-06-05 00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse("2022-06-06 00:00", formatter);
        String[] expectedTimeTableUnitUuids = {"9c1a0a40-b398-4c83-a571-1aaeb18e5414",
                "639b9cbe-3654-40d6-ae7f-aaf651c85950"};
        List<UUID> listExpectedTimeTableUnitUuids = Arrays.asList(expectedTimeTableUnitUuids)
                .stream()
                .map(s -> UUID.fromString(s))
                .collect(Collectors.toList());
        Collections.sort(listExpectedTimeTableUnitUuids);

        //when
        List<UUID> actualTimeTableUnitUuids =
                timeTableUnitRepository.findByBeginBetweenForStudent(studentUuid, startDateTime, endDateTime)
                        .stream()
                        .map(u -> u.getId())
                        .collect(Collectors.toList());
        Collections.sort(actualTimeTableUnitUuids);

        //then
        assertEquals(listExpectedTimeTableUnitUuids, actualTimeTableUnitUuids);
    }

    @Test
    void findByBeginBetweenForStudent_MustFindTimeTableUnit_WhenPeriodOneMonth() {
        //given
        UUID studentUuid = UUID.fromString("8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        LocalDateTime startDateTime = LocalDateTime.parse("2022-08-01 00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse("2022-09-01 00:00", formatter);
        String[] expectedTimeTableUnitUuids = {"0cc96b2a-224e-402f-82d0-4c56e684c2e5",
                "33a71d82-8e97-4e5e-aca6-d9e94c85d33f", "43be8d95-5cb8-4c07-9763-df18b2a8c124"};
        List<UUID> listExpectedTimeTableUnitUuids = Arrays.asList(expectedTimeTableUnitUuids)
                .stream()
                .map(s -> UUID.fromString(s))
                .collect(Collectors.toList());
        Collections.sort(listExpectedTimeTableUnitUuids);

        //when
        List<UUID> actualTimeTableUnitUuids =
                timeTableUnitRepository.findByBeginBetweenForStudent(studentUuid, startDateTime, endDateTime)
                        .stream()
                        .map(u -> u.getId())
                        .collect(Collectors.toList());
        Collections.sort(actualTimeTableUnitUuids);

        //then
        assertEquals(listExpectedTimeTableUnitUuids, actualTimeTableUnitUuids);
    }

    @Test
    void findByBeginBetweenForTeacher_MustFindTimeTableUnit_WhenPeriodOneDay() {
        //given
        UUID teacherUuid = UUID.fromString("6e1e9867-4670-4520-8b85-7c195e72bd6c");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        LocalDateTime startDateTime = LocalDateTime.parse("2022-06-05 00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse("2022-06-06 00:00", formatter);
        String[] expectedTimeTableUnitUuids = {"9c1a0a40-b398-4c83-a571-1aaeb18e5414"};
        List<UUID> listExpectedTimeTableUnitUuids = Arrays.asList(expectedTimeTableUnitUuids)
                .stream()
                .map(s -> UUID.fromString(s))
                .collect(Collectors.toList());
        Collections.sort(listExpectedTimeTableUnitUuids);

        //when
        List<UUID> actualTimeTableUnitUuids =
                timeTableUnitRepository.findByBeginBetweenForTeacher(teacherUuid, startDateTime,endDateTime)
                        .stream()
                        .map(u -> u.getId())
                        .collect(Collectors.toList());
        Collections.sort(actualTimeTableUnitUuids);

        //then
        assertEquals(listExpectedTimeTableUnitUuids, actualTimeTableUnitUuids);
    }

    @Test
    void findByBeginBetweenForTeacher_MustFindTimeTableUnit_WhenPeriodOneMonth() {
        //given
        UUID teacherUuid = UUID.fromString("210dd67b-7810-4edf-98be-e9a2cffe6290");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        LocalDateTime startDateTime = LocalDateTime.parse("2022-08-01 00:00", formatter);
        LocalDateTime entDateTime = LocalDateTime.parse("2022-09-01 00:00", formatter);
        String[] expectedTimeTableUnitUuids = {"43be8d95-5cb8-4c07-9763-df18b2a8c124"};
        List<UUID> listExpectedTimeTableUnitUuids = Arrays.asList(expectedTimeTableUnitUuids)
                .stream()
                .map(s -> UUID.fromString(s))
                .collect(Collectors.toList());
        Collections.sort(listExpectedTimeTableUnitUuids);

        //when
        List<UUID> actualTimeTableUnitUuids =
                timeTableUnitRepository.findByBeginBetweenForTeacher(teacherUuid, startDateTime,entDateTime)
                        .stream()
                        .map(u -> u.getId())
                        .collect(Collectors.toList());
        Collections.sort(actualTimeTableUnitUuids);

        //then
        assertEquals(listExpectedTimeTableUnitUuids, actualTimeTableUnitUuids);
    }
}
