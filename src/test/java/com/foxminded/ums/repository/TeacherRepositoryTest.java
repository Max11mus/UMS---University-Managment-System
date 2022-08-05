package com.foxminded.ums.repository;

import com.foxminded.ums.entities.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@TestPropertySource(locations = "/test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = "/delete_then_create_schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/insert_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TeacherRepositoryTest {
    @Autowired
    TeacherRepository teacherRepository;

    @Test
    void findById_ResultMustBeAsExpected() {
        //given
        UUID expectTeacherId = UUID.fromString("210dd67b-7810-4edf-98be-e9a2cffe6290");

        //when
        Teacher actualTeacher = teacherRepository.findById(expectTeacherId).get();

        //then
        assertNotNull(actualTeacher);
        assertEquals(expectTeacherId, actualTeacher.getId());
    }

    @Test
    void findAll_ResultMustBeAsExpected() {
        //given
        String[] expectedTeacherUuids = {"d87a90ba-1237-419a-b199-19dc389b4bbf", "210dd67b-7810-4edf-98be-e9a2cffe6290",
                "6e1e9867-4670-4520-8b85-7c195e72bd6c"};
        List<UUID> listExpectedTeacherUuids = Arrays.asList(expectedTeacherUuids)
                .stream()
                .map(s -> UUID.fromString(s))
                .collect(Collectors.toList());
        Collections.sort(listExpectedTeacherUuids);

        //when
        List<UUID> actualTeacherUuids = new ArrayList<>();
        teacherRepository.findAll()
                .forEach(t -> actualTeacherUuids.add(t.getId()));
        Collections.sort(actualTeacherUuids);

        //then
        assertEquals(listExpectedTeacherUuids, actualTeacherUuids);
    }

    @Test
    void saveNew_ResultMustBeAsExpected() {
        //given
        Teacher expectedTeacher = new Teacher();

        //when
        teacherRepository.save(expectedTeacher);
        Teacher actualTeacher = teacherRepository.findById(expectedTeacher.getId()).get();

                //then
        assertNotNull(actualTeacher);
        assertEquals(expectedTeacher, actualTeacher);
    }

    @Test
    void saveUpdate_ResultMustBeAsExpected() {
        //given
        UUID expectedTeacherUuid = UUID.fromString("6e1e9867-4670-4520-8b85-7c195e72bd6c");
        String expectedAcademicDegree = "AcademicDegree_Test";
        Teacher expectedTeacher = teacherRepository.findById(expectedTeacherUuid).get();
        expectedTeacher.setAcademicDegree(expectedAcademicDegree);

        //when
        teacherRepository.save(expectedTeacher);
        Teacher actualTeacher = teacherRepository.findById(expectedTeacher.getId()).get();

        //then
        assertNotNull(actualTeacher);
        assertEquals(expectedTeacher, actualTeacher);
    }

    @Test
    void deleteById_ResultMustBeAsExpected() {
        //given
        UUID expectedTeacherUuid = UUID.fromString("6e1e9867-4670-4520-8b85-7c195e72bd6c");
        Teacher expectedTeacherBeforeDelete = teacherRepository.findById(expectedTeacherUuid).get();
        Teacher expectedTeacherAfterDelete = null;

        //when
        teacherRepository.deleteById(expectedTeacherUuid);
        Teacher actualTeacher = teacherRepository.findById(expectedTeacherUuid).orElse(null);

        //then
        assertNotNull(expectedTeacherBeforeDelete);
        assertEquals(expectedTeacherUuid, expectedTeacherBeforeDelete.getId());

        assertNull(expectedTeacherAfterDelete);
        assertEquals(expectedTeacherAfterDelete, actualTeacher);
    }

}