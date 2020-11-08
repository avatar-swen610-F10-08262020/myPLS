package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

public class QuizService {
    public List<Quiz> getQuizByCourseID(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz Where course_id = "+course_id.toString()+" ");
        List<Quiz> quizList = q.list();

        session.close();
        return quizList;
    }

    public Quiz getQuizByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz Where id = "+id.toString()+" ");
        List<Quiz> quizList = q.list();

        session.close();
        return quizList.get(0);
    }

    public ArrayList<Quiz> getAttemptableQuizByCourseID(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz Where course_id = "+course_id.toString()+" ");
        List<Quiz> quizList = q.list();

        session.close();

        Calendar today = Calendar.getInstance();
        Calendar stoday = Calendar.getInstance();
//        System.out.println(today.get);

        ArrayList<Quiz> offeredQuiz = new ArrayList<Quiz>();
        for (Quiz quizData : quizList) {
            // If start date is not specified consider this as an invalid quiz
            if (quizData.getStart_date().isEmpty()) {
//                System.out.println("Empty start date");
                continue;
            } else {
//                System.out.println("Nonempty start date");
                String stDate = quizData.getStart_date();
                String endDate = quizData.getEnd_date();
                String[] splitStDate = stDate.split("-");
                String[] splitEndDate = endDate.split("-");
                stoday.set(Integer.parseInt(splitStDate[0]), Integer.parseInt(splitStDate[1]) - 1, Integer.parseInt(splitStDate[2]));
                Float diff = (float)(today.getTimeInMillis() - stoday.getTimeInMillis())/(24 * 60 * 60 * 1000);
                // If quiz date hasn't started yet don't show the quiz
                if (diff < 0) {
//                    System.out.println("Quiz not started");
                    continue;
                } else {
//                    System.out.println("Quiz started");
                    // If end date is not specified it is an open ended quiz so show it
                    if (quizData.getEnd_date().isEmpty()) {
//                        System.out.println("End date empty");
                        offeredQuiz.add(quizData);
                    } else {
//                        System.out.println("End date not empty");
                        stoday.set(Integer.parseInt(splitEndDate[0]), Integer.parseInt(splitEndDate[1]) - 1, Integer.parseInt(splitEndDate[2]));
                        diff = (float)(stoday.getTimeInMillis() - today.getTimeInMillis())/(24 * 60 * 60 * 1000);
                        // If quiz date has passed don't show it
                        if (diff < 0) {
//                            System.out.println("End date gone");
                            continue;
                        } else {
//                            System.out.println("End date not gone");
                            offeredQuiz.add(quizData);
                        }
                    }
                }
            }

        }
        return offeredQuiz;
    }

    public ArrayList<Quiz> getPastQuizByCourseID(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz Where course_id = "+course_id.toString()+" ");
        List<Quiz> quizList = q.list();

        session.close();

        Calendar today = Calendar.getInstance();
        Calendar stoday = Calendar.getInstance();
//        System.out.println(today.get);

        ArrayList<Quiz> pastQuiz = new ArrayList<Quiz>();
        for (Quiz quizData : quizList) {
            // If start date is not specified consider this as an invalid quiz
            if (quizData.getStart_date().isEmpty()) {
//                System.out.println("Empty start date");
                continue;
            } else {
//                System.out.println("Nonempty start date");
                Float diff = (float)(today.getTimeInMillis() - stoday.getTimeInMillis())/(24 * 60 * 60 * 1000);
                if (quizData.getEnd_date().isEmpty()) {
                    //Check if already attempted three times
                    continue;
                } else {
                    String endDate = quizData.getEnd_date();
                    String[] splitEndDate = endDate.split("-");
                    stoday.set(Integer.parseInt(splitEndDate[0]), Integer.parseInt(splitEndDate[1]) - 1, Integer.parseInt(splitEndDate[2]));
                    diff = (float)(stoday.getTimeInMillis() - today.getTimeInMillis())/(24 * 60 * 60 * 1000);
                    // If quiz date has passed don't show it
                    if (diff > 0) {
//                            System.out.println("End date gone");
                        continue;
                    } else {
//                        System.out.println("End date not gone");
//                        System.out.println(quizData);
                        pastQuiz.add(quizData);
                    }
                }
            }

        }
        System.out.println(pastQuiz);
        return pastQuiz;
    }

    public List<Quiz_Questions_Options> getQA(Long quiz_id) {
        Quiz_OptionsService quiz_optionsService = new Quiz_OptionsService();
        List<Quiz_Options> quiz_options_list = quiz_optionsService.getQuizOptionByQuizId(quiz_id);
//            List<Quiz_Questions> quiz_questions = new ArrayList<>();
        Set<Quiz_Questions> quiz_questions = new HashSet<>();
        List<Quiz_Questions_Options> quiz_questions_options_list = new ArrayList<>();
        for(Quiz_Options quiz_options:quiz_options_list){
            quiz_questions.add(quiz_options.getQuiz_questions());
        }
        for(Quiz_Questions quizQuestion:quiz_questions){
            List<Quiz_Options> quiz_optionsList = new ArrayList<>();
            Integer correct_ans=0;
            for(Quiz_Options quiz_options:quiz_options_list){
                if(!quizQuestion.getId().equals(quiz_options.getQuiz_questions().getId()))
                    continue;
                quiz_optionsList.add(quiz_options);
                if(quiz_options.getOption_status().equals(1))
                    correct_ans = Math.toIntExact(quiz_options.getOption_number());
            }
            Quiz_Questions_Options quiz_questions_options = new Quiz_Questions_Options(quizQuestion,quiz_optionsList);
            quiz_questions_options.setResult(correct_ans);
            quiz_questions_options_list.add(quiz_questions_options);
        }
        Collections.sort(quiz_questions_options_list);
        return quiz_questions_options_list;
    }

}
