package com.mcserby.playground.jenvers.controller;

import com.mcserby.playground.jenvers.entity.Question;
import com.mcserby.playground.jenvers.entity.Questionnaire;
import com.mcserby.playground.jenvers.entity.User;
import com.mcserby.playground.jenvers.service.QuestionService;
import com.mcserby.playground.jenvers.service.QuestionnaireService;
import com.mcserby.playground.jenvers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @PostMapping("/questionnaires")
    public Questionnaire createQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return questionnaireService.saveQuestionnaire(questionnaire);
    }

    @PutMapping("/questionnaires/{questionnaireId}/editors/{editorId}")
    public Questionnaire addEditor(@PathVariable Long questionnaireId, @PathVariable Long editorId) {
        return questionnaireService.addEditor(questionnaireId, editorId);
    }

    @PutMapping("/questionnaires/{questionnaireId}/questions")
    public Questionnaire addQuestions(@PathVariable Long questionnaireId, @RequestBody Set<Long> questionIds) {
        return questionnaireService.addQuestions(questionnaireId, questionIds);
    }

    @GetMapping("/questionnaires/{id}")
    public Questionnaire getQuestionnaire(@PathVariable Long id) {
        return questionnaireService.getQuestionnaire(id);
    }

    @PostMapping("/questions")
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping("/questions/{id}")
    public Question getQuestion(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
