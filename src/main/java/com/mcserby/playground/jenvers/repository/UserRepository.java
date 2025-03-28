package com.mcserby.playground.jenvers.repository;

import com.mcserby.playground.jenvers.entity.User;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface UserRepository extends CrudRepository<User, Long>, RevisionRepository<User, Long, Integer> {
}