package com.mypls.model;

import javax.persistence.*;

@Entity
@Table
public class Quiz_Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long question_id;
    private Long option_number;
    private String description;
    private Integer option_status;
    private Integer status;
    @ManyToOne
    @JoinColumn(name="question_id", referencedColumnName="id", insertable = false, updatable = false)
    private Quiz_Questions quiz_questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestion_id(){return question_id;}

    public void setQuestion_id(Long question_id){this.question_id = question_id;}

    public Long getOption_number(){return  option_number;}

    public void setOption_number(Long option_number){this.option_number = option_number;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public Integer getOption_status(){return option_status;}

    public void setOption_status(Integer option_status){this.option_status = option_status;}

    public Integer getStatus(){return status;}

    public void setStatus(Integer status){this.status = status;}

    public Quiz_Questions getQuiz_questions(){ return quiz_questions;}

    public void setQuiz_questions(Quiz_Questions quiz_questions){ this.quiz_questions = quiz_questions;}


}
