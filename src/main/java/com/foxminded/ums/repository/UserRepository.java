package com.foxminded.ums.repository;

import com.foxminded.ums.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>,
        PagingAndSortingRepository<User, UUID> {
    User findByLogin(String login);
}