package com.mcserby.playground.jenvers.entity;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String type;

//    private Long questionOrder;

    @ManyToMany(mappedBy = "questions", fetch = FetchType.EAGER)
    private Set<Questionnaire> questionnaires = new HashSet<>();

    // Constructors, getters, setters
    public Question() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Long getQuestionOrder() {
//        return questionOrder;
//    }
//
//    public void setQuestionOrder(Long questionOrder) {
//        this.questionOrder = questionOrder;
//    }

    public Set<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Set<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

}