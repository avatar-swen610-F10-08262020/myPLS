package com.mypls.model;

import javax.persistence.*;

@Entity
@Table
public class Course_Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long week_number;
    private String week_name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWeek_number() {
        return week_number;
    }

    public void setWeek_number(Long week_number) {
        this.week_number = week_number;
    }

    public String getWeek_name() {
        return week_name;
    }

    public void setWeek_name(String week_name) {
        this.week_name = week_name;
    }

}
