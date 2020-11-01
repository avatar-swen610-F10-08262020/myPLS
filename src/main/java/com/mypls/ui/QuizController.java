package com.mypls.ui;

import com.mypls.model.*;
import com.mypls.util.HibernateUtil;
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

//        System.out.println(quiz_description);
//        System.out.println(quiz_length);
//        System.out.println(start_date);
//        System.out.println(end_date);
//        System.out.println(questions);
//        System.out.println(options);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Quiz newQuiz = new Quiz();
            newQuiz.setCourse_id(Long.parseLong(req.params(":id")));
            newQuiz.setQuiz_description(quiz_description);
            newQuiz.setQuiz_length(Integer.parseInt(quiz_length));
            newQuiz.setStart_date(start_date);
            newQuiz.setEnd_date(end_date);
            newQuiz.setStatus(1);

            session.save(newQuiz);
            System.out.println("Course ID:" + newQuiz.getId());
            Long quiz_id = newQuiz.getId();

            session.getTransaction().commit();
            Long cnt = Long.valueOf(0);
            for(String question:questions) {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Quiz_Questions new_quiz_questions = new Quiz_Questions();
                new_quiz_questions.setQuiz_id(quiz_id);
                new_quiz_questions.setQuestion_description(question);
                new_quiz_questions.setQuestion_number(cnt+1);
                new_quiz_questions.setStatus(1);

                session.save(new_quiz_questions);
                System.out.println("Course ID:" + new_quiz_questions.getId());
                Long question_id = new_quiz_questions.getId();

                session.getTransaction().commit();
                String correct = correct_ans.get(Math.toIntExact(cnt));
                Long option_count = Long.valueOf(0);
                for(String option:options.get(Math.toIntExact(cnt))){

                    session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    Quiz_Options new_quiz_options = new Quiz_Options();
                    new_quiz_options.setQuestion_id(question_id);
                    new_quiz_options.setOption_number(option_count+1);
                    new_quiz_options.setDescription(option);
                    new_quiz_options.setStatus(1);
                    if(option_count == Long.parseLong(String.valueOf(correct_ans.get(Math.toIntExact(cnt)))))
                        new_quiz_options.setOption_status(1);
                    else new_quiz_options.setOption_status(0);

                    session.save(new_quiz_options);
                    System.out.println("Option ID:" + new_quiz_options.getId());
                    Long option_id = new_quiz_options.getId();

                    session.getTransaction().commit();
                    option_count++;
                }
                cnt++;
            }
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }

        return singleview(req);

    }
}
