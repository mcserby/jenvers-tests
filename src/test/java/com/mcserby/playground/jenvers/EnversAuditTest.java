package com.mcserby.playground.jenvers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcserby.playground.jenvers.entity.Question;
import com.mcserby.playground.jenvers.entity.Questionnaire;
import com.mcserby.playground.jenvers.entity.User;
import com.mcserby.playground.jenvers.repository.QuestionRepository;
import com.mcserby.playground.jenvers.repository.QuestionnaireRepository;
import com.mcserby.playground.jenvers.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class EnversAuditTest {

    private static final Logger logger = LoggerFactory.getLogger(EnversAuditTest.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testEnversAudit() throws JsonProcessingException {
        // Create a User
        int index = 5;
        User user = new User();
        user.setUsername("testUser" + index);
        user.setEmail("test" + index + "@example.com");
        user = userRepository.save(user);
        logger.info("Created user: " + objectMapper.writeValueAsString(user));
        // Create a Question
        Question question = new Question();
        question.setText("Test Question " + index + "?");
        question.setType("multiselect");
        //question.setQuestionOrder(1L);
        question = questionRepository.save(question);
        logger.info("Created question: " + objectMapper.writeValueAsString(question));

        // Create a Questionnaire and link User and Question
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setTitle("Test Questionnaire " + index);
        questionnaire.setOwner(user);
        Set<Question> questions = new HashSet<>();
        questions.add(question);
        questionnaire.setQuestions(questions);

        questionnaire = questionnaireRepository.save(questionnaire);
        logger.info("Created questionnaire: " + objectMapper.writeValueAsString(questionnaire));

        List<Revision<Integer, Questionnaire>> questionnaireRevisions = questionnaireRepository.findRevisions(questionnaire.getId()).getContent();
        List<Revision<Integer, Question>> questionRevisions = questionRepository.findRevisions(question.getId()).getContent();
        List<Revision<Integer, User>> userRevisions = userRepository.findRevisions(user.getId()).getContent();

        assertFalse(questionnaireRevisions.isEmpty());
        assertFalse(questionRevisions.isEmpty());
        assertFalse(userRevisions.isEmpty());
        logger.info("Questionnaire revisions: " + objectMapper.writeValueAsString(questionnaireRevisions.stream().map(Revision::getRequiredRevisionNumber).toList()));
        logger.info("Question revisions: " + objectMapper.writeValueAsString(questionRevisions.stream().map(Revision::getRequiredRevisionNumber).toList()));
        logger.info("Users revisions: " + objectMapper.writeValueAsString(userRevisions.stream().map(Revision::getRequiredRevisionNumber).toList()));


        // Check if revision data is present
        Revision<Integer, Questionnaire> firstQuestionnaireRevision = questionnaireRevisions.getFirst();
        Questionnaire questionnaireWithFirstRevision = questionnaireRepository.findById(firstQuestionnaireRevision.getEntity().getId()).orElseThrow();
        assertEquals("Test Questionnaire " + index, questionnaireWithFirstRevision.getTitle());
        assertEquals(user.getId(), questionnaireWithFirstRevision.getOwner().getId());
        assertEquals(question.getId(), questionnaireWithFirstRevision.getQuestions().iterator().next().getId());

        Revision<Integer, Question> firstQuestionRevision = questionRevisions.getFirst();
        Question questionWithFirstRevision = questionRepository.findById(firstQuestionRevision.getEntity().getId()).orElseThrow();
        assertEquals("Test Question " + index + "?", questionWithFirstRevision.getText());

        Revision<Integer, User> firstUserRevision = userRevisions.getFirst();
        User userWithFirstRevision = userRepository.findById(firstUserRevision.getEntity().getId()).orElseThrow();
        assertEquals("testUser" + index, userWithFirstRevision.getUsername());
        assertEquals("test" + index + "@example.com", userWithFirstRevision.getEmail());
    }
}
