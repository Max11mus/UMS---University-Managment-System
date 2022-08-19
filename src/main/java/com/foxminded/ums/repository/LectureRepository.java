package com.foxminded.ums.repository;

import com.foxminded.ums.entities.Lecture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LectureRepository extends CrudRepository<Lecture, UUID> {
}
