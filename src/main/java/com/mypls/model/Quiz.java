package com.mypls.model;

import javax.persistence.*;

@Entity
@Table
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long course_id;
    private String quiz_description;
    private Integer quiz_length;
    private String start_date;
    private String end_date;
    private Integer status;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getQuiz_description(){return quiz_description;}

    public void setQuiz_description(String quiz_description){this.quiz_description = quiz_description;}

    public Integer getQuiz_length(){return quiz_length;}

    public void setQuiz_length(Integer quiz_length){this.quiz_length = quiz_length;}

    public  String getStart_date(){return start_date;}

    public void setStart_date(String start_date){this.start_date = start_date;}

    public String getEnd_date(){return end_date;}

    public void setEnd_date(String end_date){this.end_date = end_date;}

    public Integer getStatus(){return status;}

    public void setStatus(Integer status){this.status = status;}
}
