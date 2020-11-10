package com.mypls.model;

import java.util.List;

public class Lesson_Week implements Comparable<Lesson_Week>{
    private Long week_number;
    private List<Lesson> content_lesson;
    public Lesson_Week(Long week_number, List<Lesson> content_lesson){
        super();
        this.week_number = week_number;
        this.content_lesson = content_lesson;
    }
    @Override
    public int compareTo(Lesson_Week o) {
        return this.week_number.compareTo(o.week_number);
    }


    public Long getWeek_number(){
        return week_number;
    }

    public List<Lesson> getContent_lesson(){
        return content_lesson;
    }

}
