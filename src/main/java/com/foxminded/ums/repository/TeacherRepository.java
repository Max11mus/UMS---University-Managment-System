package com.foxminded.ums.repository;

import com.foxminded.ums.entities.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, UUID>,
        PagingAndSortingRepository<Teacher, UUID> {
}