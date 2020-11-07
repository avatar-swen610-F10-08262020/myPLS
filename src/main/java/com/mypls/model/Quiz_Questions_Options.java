package com.mypls.model;

import java.util.List;

public class Quiz_Questions_Options implements Comparable<Quiz_Questions_Options>{
    private Quiz_Questions quiz_questions;
    private List<Quiz_Options> quiz_optionslist;
    private Integer result;
    public Quiz_Questions_Options(Quiz_Questions quiz_questions, List<Quiz_Options> quiz_optionslist){
        super();
        this.quiz_questions = quiz_questions;
        this.quiz_optionslist = quiz_optionslist;
    }
    @Override
    public int compareTo(Quiz_Questions_Options o) {
        return this.quiz_questions.getId().compareTo(o.quiz_questions.getId());
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getResult(){
        return result;
    }

    public Quiz_Questions getQuiz_questions(){
        return quiz_questions;
    }

    public List<Quiz_Options> getQuiz_optionslist(){
        return quiz_optionslist;
    }



}
