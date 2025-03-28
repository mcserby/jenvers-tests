package com.mcserby.playground.jenvers.repository;


import com.mcserby.playground.jenvers.entity.Question;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface QuestionRepository extends CrudRepository<Question, Long>, RevisionRepository<Question, Long, Integer> {
}
