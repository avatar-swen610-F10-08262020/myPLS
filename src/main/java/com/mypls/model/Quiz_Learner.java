package com.mypls.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table
public class Quiz_Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quiz_id;
    private Long learner_id;
    private String percentage;
    private Integer score;
    @ColumnDefault("1")
    private Integer status;
    private Integer total_questions;
    @ManyToOne
    @JoinColumn(name="quiz_id", referencedColumnName="id", insertable = false, updatable = false)
    private Quiz quiz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Long getLearner_id() {
        return learner_id;
    }

    public void setLearner_id(Long learner_id) {
        this.learner_id = learner_id;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotal_questions() {
        return total_questions;
    }

    public void setTotal_questions(Integer total_questions) {
        this.total_questions = total_questions;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
