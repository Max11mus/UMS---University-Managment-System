package com.foxminded.ums.repository;

import com.foxminded.ums.entities.Student;
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
@Sql(value = "/clear_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Sql(value = "/insert_only_students.sql")
    void findById_MustFindExistedStudent() {
        //given
        UUID expectStudentId = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");

        //when
        Student actualStudent = studentRepository.findById(expectStudentId).get();

        //then
        assertNotNull(actualStudent);
        assertEquals(expectStudentId, actualStudent.getId());
    }

    @Test
    @Sql(value = "/insert_only_students.sql")
    void findAll_MustFindAllStudents() {
        //given
        String[] expectedStudentUuids = {"8cfe9e2c-7c4c-4b97-a590-bcc125eba4b7", "c3e47148-adcf-4ee3-81f6-6b79b83a41ca",
                "f57e0ffe-6118-44a8-b39d-b2da86b65aff"};
        List<UUID> listExpectedStudentUuids = Arrays.asList(expectedStudentUuids)
                .stream()
                .map(s -> UUID.fromString(s))
                .collect(Collectors.toList());
        Collections.sort(listExpectedStudentUuids);

        //when
        List<UUID> actualStudentUuids = new ArrayList<>();
        Iterable<Student> all = studentRepository.findAll();
        all.forEach(s -> actualStudentUuids.add(s.getId()));
        Collections.sort(actualStudentUuids);

        //then
        assertEquals(listExpectedStudentUuids, actualStudentUuids);
    }

    @Test
    void save_MustCreateNewStudent() {
        //given
        Student expectedStudent = new Student();

        //when
        studentRepository.save(expectedStudent);
        Student actualStudent = studentRepository.findById(expectedStudent.getId()).get();

        //then
        assertNotNull(actualStudent);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    @Sql(value = "/insert_only_students.sql")
    void save_MustUpdateExistedStudent() {
        //given
        UUID expectedStudentUuid = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        String surName = "surName_Test";
        Student expectedStudent = studentRepository.findById(expectedStudentUuid).get();
        expectedStudent.setSurname(surName);

        //when
        studentRepository.save(expectedStudent);
        Student actualStudent = studentRepository.findById(expectedStudent.getId()).get();

        //then
        assertNotNull(actualStudent);
        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    @Sql(value = "/insert_only_students.sql")
    void deleteById_MustDeleteExistedStudent() {
        //given
        UUID expectedStudentUuid = UUID.fromString("f57e0ffe-6118-44a8-b39d-b2da86b65aff");
        Student expectedStudentBeforeDelete = studentRepository.findById(expectedStudentUuid).get();
        Student expectedStudentAfterDelete = null;

        //when
        studentRepository.deleteById(expectedStudentUuid);
        Student actualStudent = studentRepository.findById(expectedStudentUuid).orElse(null);

        //then
        assertNotNull(expectedStudentBeforeDelete);
        assertEquals(expectedStudentUuid, expectedStudentBeforeDelete.getId());

        assertNull(expectedStudentAfterDelete);
        assertEquals(expectedStudentAfterDelete, actualStudent);
    }

}