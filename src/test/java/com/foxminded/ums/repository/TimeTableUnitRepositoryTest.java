package com.foxminded.ums.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.url=jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=sa",
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.flyway.enabled=false"})
class TimeTableUnitRepositoryTest {
    @Autowired
    private TimeTableUnitRepository timeTableUnitRepository;

    @Test
    @Sql({ "drop_schema.sql", "create_schema.sql" })
    @Sql("insert_data.sql")
    void findByDayForStudent() {

    }


}
