package com.mypls.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table
public class Learner_course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long course_id;
    private Long user_id;
    @ColumnDefault("1")
    private Integer status;

    @ColumnDefault("0")
    private Integer completed;

    @ManyToOne
    @JoinColumn(name="course_id", referencedColumnName="id", insertable = false, updatable = false)
    private Course course;

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
