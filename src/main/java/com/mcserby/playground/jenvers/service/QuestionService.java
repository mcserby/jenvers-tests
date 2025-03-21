package com.mcserby.playground.jenvers.service;

import com.mcserby.playground.jenvers.entity.Question;
import com.mcserby.playground.jenvers.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getQuestion(Long id){
        return questionRepository.findById(id).orElse(null);
    }
}
