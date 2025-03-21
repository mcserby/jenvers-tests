package com.mcserby.playground.jenvers.service;

import com.mcserby.playground.jenvers.entity.Question;
import com.mcserby.playground.jenvers.entity.Questionnaire;
import com.mcserby.playground.jenvers.entity.User;
import com.mcserby.playground.jenvers.repository.QuestionRepository;
import com.mcserby.playground.jenvers.repository.QuestionnaireRepository;
import com.mcserby.playground.jenvers.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public QuestionnaireService(QuestionnaireRepository questionnaireRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.questionnaireRepository = questionnaireRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire addEditor(Long questionnaireId, Long editorId) {
        Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).orElseThrow(() -> new RuntimeException("Questionnaire not found"));
        User editor = userRepository.findById(editorId).orElseThrow(() -> new RuntimeException("Editor not found"));
        if (questionnaire != null && editor != null) {
            questionnaire.getEditors().add(editor);
            return questionnaireRepository.save(questionnaire);
        }
        return questionnaire;
    }

    public Questionnaire addQuestions(Long questionnaireId, Set<Long> questionIds) {
        Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).orElseThrow(() -> new RuntimeException("Questionnaire not found"));
        Set<Question> questions = questionIds.stream().map(questionRepository::findById).filter(java.util.Optional::isPresent).map(java.util.Optional::get).collect(Collectors.toSet());
        questionnaire.getQuestions().addAll(questions);
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire getQuestionnaire(Long id) {
        return questionnaireRepository.findById(id).orElseThrow(() -> new RuntimeException("Questionnaire not found"));
    }
}