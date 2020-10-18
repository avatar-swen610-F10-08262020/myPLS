package com.mypls.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course_name;
    private String course_code;
    private String description;
    private Long class_size;
    private String start_date;
    private String end_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getClass_size() {
        return class_size;
    }

    public void setClass_size(Long class_size) {
        this.class_size = class_size;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

//    @Override
//    public String toString() {
//        return "Course{" + "course_name='" + course_name + '\'' + "course_code='" + course_code + '\'' + ", description='" + description + '\'' + ", class_size='" + class_size + '\''
//                + ", start_date='" + start_date + '\'' + ", end_date='" + end_date + '\'' + '}';
//    }
}

