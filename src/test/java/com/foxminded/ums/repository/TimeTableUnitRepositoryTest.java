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
@Sql(value = "/delete_then_create_schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TimeTableUnitRepositoryTest {
    @Autowired
    private TimeTableUnitRepository timeTableUnitRepository;

    @Test
    void findByBeginBetweenForStudentPeriodOneDay_ResultMustBeAsExpected() {
        //given
        UUID studentUuid = UUID.fromString("8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse("2022-06-05 00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse("2022-06-06 00:00", formatter);
        System.out.println(startDateTime);
        String[] expectedTimeTableUnitUuids = {"e8647e6b-e68f-40e3-9e25-b370a38bddfe",
                "19698d40-230e-4509-a94b-c4673f1269e5", "5d4981d1-c0b2-47c8-8c90-0a4f0e418976",
                "f69b6ca6-96c3-4705-9527-6f8a63268331", "339dc084-219f-454e-82dd-7aee895aae2f",
                "639b9cbe-3654-40d6-ae7f-aaf651c85950", "9c1a0a40-b398-4c83-a571-1aaeb18e5414"};
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
    void findByBeginBetweenForStudentPeriodOneMonth_ResultMustBeAsExpected() {
        //given
        UUID studentUuid = UUID.fromString("8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        LocalDateTime startDateTime = LocalDateTime.parse("2022-06-01 00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse("2022-07-01 00:00", formatter);
        String[] expectedTimeTableUnitUuids = {"4ebaed1f-6234-4429-8d2d-89046c8ad1ae",
                "8a5eda93-a8ad-454b-add1-60a29495c0ff", "0c6aa607-83d0-4c31-9903-d9ac1a3434e0",
                "e8647e6b-e68f-40e3-9e25-b370a38bddfe", "19698d40-230e-4509-a94b-c4673f1269e5",
                "5d4981d1-c0b2-47c8-8c90-0a4f0e418976", "f69b6ca6-96c3-4705-9527-6f8a63268331",
                "339dc084-219f-454e-82dd-7aee895aae2f", "639b9cbe-3654-40d6-ae7f-aaf651c85950",
                "9c1a0a40-b398-4c83-a571-1aaeb18e5414", "65193c54-b3e2-4483-8f4f-b2d89f5f6397",
                "a6e7e3de-cb9b-459e-af1f-500a619000a3", "448e3dd5-704d-4635-9fd5-5bd5d5d2d6ef",
                "e83c26d3-8402-4990-881b-a17737eaebe6", "303e342c-9d05-4f8e-b118-916dbba7f78e",
                "826b603b-e40a-4126-8e92-c77e73150d51", "ed23e77b-f22a-4705-98f8-ad879982b3f3",
                "b88a472c-b167-4227-9ef1-1b6f364ecfaa", "9001d675-3591-4971-a636-11aa64a7a25a",
                "7cdf9f9b-08ff-42f4-8af6-127c8d802544", "15b604fb-402f-45ab-b76a-b3506e90a1ce",
                "aafd1787-9bfb-4f43-90d8-a988686c400a", "2135a1f8-0d8a-465c-9645-47698454a9b0",
                "39bcb368-78d3-41dc-844a-e51f4515a5ea", "a2091806-e61d-48ce-be12-0ec5e7bf1254",
                "19cc54ac-8faf-4e46-a52b-6f1306017c5b", "05f44661-ec6f-4c79-bfff-af6e0074645b",
                "87290678-8937-453d-80d6-373cbe568144", "8435c167-30b8-4f99-8fe5-4fafdbd68c6a",
                "6e1f4990-9878-419e-964b-98e9b1b4c827", "db139ff9-76e7-440f-b655-6f89d28ecb1d",
                "8a2e3c2a-c7e8-4daf-a34d-777ad41c8d8f", "523e7da9-24b0-49f3-8a32-b65e0810420a",
                "b5d9b3bb-99de-4643-bfd3-6b90b0170416"};
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
    void findByBeginBetweenForTeacherPeriodOneDay_ResultMustBeAsExpected() {
        //given
        UUID teacherUuid = UUID.fromString("210dd67b-7810-4edf-98be-e9a2cffe6290");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        LocalDateTime startDateTime = LocalDateTime.parse("2022-06-05 00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse("2022-06-06 00:00", formatter);
        String[] expectedTimeTableUnitUuids = {"339dc084-219f-454e-82dd-7aee895aae2f",
                "e8647e6b-e68f-40e3-9e25-b370a38bddfe"};
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
    void findByBeginBetweenForTeacherPeriodOneMonth_ResultMustBeAsExpected() {
        //given
        UUID teacherUuid = UUID.fromString("210dd67b-7810-4edf-98be-e9a2cffe6290");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        LocalDateTime startDateTime = LocalDateTime.parse("2022-06-01 00:00", formatter);
        LocalDateTime entDateTime = LocalDateTime.parse("2022-07-01 00:00", formatter);
        String[] expectedTimeTableUnitUuids = {"826b603b-e40a-4126-8e92-c77e73150d51",
                "8435c167-30b8-4f99-8fe5-4fafdbd68c6a", "8a5eda93-a8ad-454b-add1-60a29495c0ff",
                "aafd1787-9bfb-4f43-90d8-a988686c400a", "b5d9b3bb-99de-4643-bfd3-6b90b0170416",
                "db139ff9-76e7-440f-b655-6f89d28ecb1d", "e8647e6b-e68f-40e3-9e25-b370a38bddfe",
                "05f44661-ec6f-4c79-bfff-af6e0074645b", "15b604fb-402f-45ab-b76a-b3506e90a1ce",
                "2135a1f8-0d8a-465c-9645-47698454a9b0", "339dc084-219f-454e-82dd-7aee895aae2f",
                "39bcb368-78d3-41dc-844a-e51f4515a5ea", "4ebaed1f-6234-4429-8d2d-89046c8ad1ae",
                "523e7da9-24b0-49f3-8a32-b65e0810420a", "7cdf9f9b-08ff-42f4-8af6-127c8d802544"};
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
