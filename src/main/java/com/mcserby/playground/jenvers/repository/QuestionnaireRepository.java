package com.mcserby.playground.jenvers.repository;

import com.mcserby.playground.jenvers.entity.Questionnaire;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@JaversSpringDataAuditable
@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long>, RevisionRepository<Questionnaire, Long, Integer> {
}