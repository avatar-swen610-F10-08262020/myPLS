package com.mypls.ui;

import spark.ModelAndView;
import spark.Request;

import java.util.ArrayList;
import java.util.List;

public class QuizController extends CourseController{
    public ModelAndView quiz_add( Request req){
        System.out.println(req.queryParams());
        String quiz_description = req.queryParams("quiz_description");
        String quiz_length = req.queryParams("quiz_length");
        String start_date = req.queryParams("start_date");
        String end_date = req.queryParams("end_date");
        Integer count=1;
        List<String> questions = new ArrayList<String>();
        List<String> correct_ans = new ArrayList<String>();

        List<List<String>> options = new ArrayList<List<String>>();
        while(req.queryParams("question"+count.toString())!=null){
            questions.add(req.queryParams("question"+count.toString()));
            correct_ans.add(req.queryParams("correct_ans_"+count.toString()));
            Integer optionCount =1;
            List<String> option = new ArrayList<String>();
            while (optionCount<6)
           {
               option.add(req.queryParams("options"+count.toString()+optionCount.toString()));
               optionCount++;
           }
            options.add(option);
           count++;
        }

        System.out.println(quiz_description);
        System.out.println(quiz_length);
        System.out.println(start_date);
        System.out.println(end_date);
        System.out.println(questions);
        System.out.println(options);

        return singleview(req);

    }
}
