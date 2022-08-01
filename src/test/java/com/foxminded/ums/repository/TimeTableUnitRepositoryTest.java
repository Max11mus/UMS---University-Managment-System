package com.foxminded.ums.repository;

import com.foxminded.ums.entities.TimeTableUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.url=jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa",
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.flyway.enabled=false",
        "spring.jpa.show-sql=true",
        "spring.jpa.show-sql=true"
})
class TimeTableUnitRepositoryTest {
    @Autowired
    private TimeTableUnitRepository timeTableUnitRepository;

    @Test
    @Sql("/create_schema.sql")
    @Sql("/insert_data.sql")
    void findByDayForStudent_ResultMustBeAsExpected() {
        //given
        UUID studentUuid = UUID.fromString("8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("2022-06-05 00:00", formatter);
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
                timeTableUnitRepository.findByDayForStudent(studentUuid, localDateTime)
                        .stream()
                        .map(u -> u.getId())
                        .collect(Collectors.toList());
        Collections.sort(actualTimeTableUnitUuids);

        //then
        assertEquals(listExpectedTimeTableUnitUuids, actualTimeTableUnitUuids);

    }


}
