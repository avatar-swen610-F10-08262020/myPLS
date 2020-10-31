package com.mypls.model;

import javax.persistence.*;

@Entity
@Table
public class Quiz_Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quiz_id;
    private Long question_number;
    private String question_description;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuiz_id(){return quiz_id;}

    public void setQuiz_id(Long quiz_id){this.quiz_id = quiz_id;}

    public Long getQuestion_number(){return question_number;}

    public void setQuestion_number(Long question_number){this.question_number = question_number;}

    public String getQuestion_description(){return question_description;}

    public void setQuestion_description(String question_description){this.question_description = question_description;}

    public Integer getStatus(){return status;}

    public void setStatus(Integer status){ this.status = status;}
}
