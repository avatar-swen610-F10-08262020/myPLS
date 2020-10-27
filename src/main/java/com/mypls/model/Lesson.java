package com.mypls.model;

import javax.persistence.*;

@Entity
@Table
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long course_id;
    private Long week_id;
    private String lesson_content_path;
    private String content_type;
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

    public void setCourse_id(Long course_name) {
        this.course_id = course_id;
    }

    public Long getWeek_id() {
        return week_id;
    }

    public void setWeek_id(Long week_id) {
        this.week_id = week_id;
    }

    public String getLesson_content_path() {
        return lesson_content_path;
    }

    public void setLesson_content_path(String lesson_content_path) {
        this.lesson_content_path = lesson_content_path;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
