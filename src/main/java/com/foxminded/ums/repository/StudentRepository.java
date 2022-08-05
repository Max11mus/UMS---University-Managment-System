package com.foxminded.ums.repository;

import com.foxminded.ums.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID>,
        PagingAndSortingRepository<Student, UUID> {
        }