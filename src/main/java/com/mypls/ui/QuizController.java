package com.mypls.ui;

import com.mypls.model.*;
import com.mypls.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class QuizController extends CourseController{
    Quiz_OptionsService quiz_optionsService = new Quiz_OptionsService();
    QuizService quizService = new QuizService();
    Quiz_QuestionsService quiz_questionsService = new Quiz_QuestionsService();
    Session session = null;

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

    public ModelAndView quiz_details( Request req){
        Map<String, Object> map = new HashMap<>();
        try {
//            User user = userService.getUserbyId((long) 1);
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long quiz_id = Long.parseLong(req.params(":id"));
            List<Quiz_Questions_Options> quiz_questions_options_list = quizService.getQA(quiz_id);

            map.put("QuizQuestionOptions", quiz_questions_options_list);
            map.put("quizId", quiz_id);



            return new ModelAndView(map, "quiz/quiz_details.ftl");
        }
         catch (NullPointerException ex)
        {
            return login(req);
        }
    }

    public ModelAndView quiz_attempt ( Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
//            User user = userService.getUserbyId((long) 1);
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long quiz_id = Long.parseLong(req.params(":id"));
            List<Quiz_Questions_Options> quiz_questions_options_list = quizService.getQA(quiz_id);

            map.put("QuizQuestionOptions", quiz_questions_options_list);
            map.put("quizId", quiz_id);



            return new ModelAndView(map, "quiz/quiz_attempt.ftl");
        }
        catch (NullPointerException ex)
        {
            return login(req);
        }
    }

    public ModelAndView quiz_submit(Request req, Response res){
        Map<String, Object> map = new HashMap<>();
        try {
//            User user = userService.getUserbyId((long) 1);
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long quiz_id = Long.parseLong(req.params(":id"));
            Long course_id = quizService.getQuizByID(quiz_id).getCourse_id();
            List<Quiz_Questions_Options> quiz_questions_options_list = quizService.getQA(quiz_id);
            double total_score = 0.0;
            double percentage = 0.0;
            for (Quiz_Questions_Options qqo: quiz_questions_options_list) {
                Integer correct_answer = -1;
                String submitted_answer = req.queryParams("q_"+qqo.getQuiz_questions().getQuestion_number());
                System.out.println("Submitted " + submitted_answer);
                List<Quiz_Options> options = qqo.getQuiz_optionslist();
                for (Quiz_Options qo: options) {
                    if (qo.getOption_status() == 1) {
                        correct_answer = Math.toIntExact(qo.getOption_number());
                        System.out.println(qo.getOption_status() + "c" + correct_answer);
                    }
                }
                if (correct_answer.toString().equalsIgnoreCase(submitted_answer)) {
                    System.out.println("correct" + total_score);
                    total_score += 1;
                }
            }
            percentage = total_score / quiz_questions_options_list.size() * 100;
            System.out.println(total_score + "---" + percentage);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Quiz_Learner quiz_learner = new Quiz_Learner();
            quiz_learner.setLearner_id(user.getId());
            quiz_learner.setQuiz_id(quiz_id);
            quiz_learner.setPercentage(String.valueOf(percentage));
            quiz_learner.setScore((int) total_score);
            quiz_learner.setStatus(1);
            quiz_learner.setTotal_questions(quiz_questions_options_list.size());
            session.save(quiz_learner);
//            session.getTransaction().commit();
            session.close();



            if (percentage < 50) {
                map.put("msg_type", "error");
                map.put("msg", "Sorry, you failed the quiz. You managed to get " + String.valueOf(percentage) +" (" +String.valueOf(total_score) + "/" + String.valueOf(quiz_questions_options_list.size()) + ")");
            } else {
                map.put("msg_type", "success");
                map.put("msg", "Congratulations, you passed the quiz. You managed to get " + String.valueOf(percentage) +" (" +String.valueOf(total_score) + "/" + String.valueOf(quiz_questions_options_list.size()) + ".0)");
            }

            map.put("courseid", course_id);
            return new ModelAndView(map , "quiz/quiz_result.ftl");
//            res.redirect( "/course/"+course_id.toString());
        }
        catch (NullPointerException ex)
        {
            return login(req);
        }
//        return null;
    }

    public ModelAndView quiz_edit( Request req){
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long quiz_id = Long.parseLong(req.params(":id"));
            Quiz quiz = quizService.getQuizByID(quiz_id);
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
            map.put("QuizQuestionOptions", quiz_questions_options_list);
            map.put("quiz", quiz);
            map.put("question_length", quiz_questions.size());


            return new ModelAndView(map, "quiz/edit.ftl");
        }
        catch (NullPointerException ex)
        {
            return login(req);
        }
    }

    public ModelAndView quiz_update( Request req){
        Long quiz_id = Long.parseLong(req.params(":id"));
        Quiz currQuiz = quizService.getQuizByID(quiz_id);
        System.out.println(currQuiz);
        String quiz_description = req.queryParams("quiz_description");
        String quiz_length = req.queryParams("quiz_length");
        String start_date = req.queryParams("start_date");
        String end_date = req.queryParams("end_date");
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currQuiz.setQuiz_description(quiz_description);
        currQuiz.setQuiz_length(Integer.parseInt(quiz_length));
        currQuiz.setStart_date(start_date);
        currQuiz.setEnd_date(end_date);
        session.update(currQuiz);
        session.getTransaction().commit();

        return quiz_edit(req);
    }

    public ModelAndView quiz_question_update( Request req){
        Long question_id = Long.parseLong(req.params(":question_id"));
        Quiz_Questions currQuestion = quiz_questionsService.getQuizQuestionsByID(question_id);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currQuestion.setQuestion_description(req.queryParams("question"+question_id.toString()));
        session.update(currQuestion);
        session.getTransaction().commit();

        return quiz_edit(req);
    }

    public ModelAndView quiz_option_update( Request req){
//        String option11 = req.queryParams("options11");
//        System.out.println(option11);
        Long question_id = Long.parseLong(req.params(":question_id"));

        List<String> correct_ans = new ArrayList<String>();


        correct_ans.add(req.queryParams("correct_ans_"+question_id.toString()));
        Integer optionCount =1;
        List<String> option = new ArrayList<String>();
        while (optionCount<6)
        {
            option.add(req.queryParams("options"+question_id.toString()+optionCount.toString()));
            optionCount++;
        }

        Long option_count = Long.valueOf(0);
        for(String optionValue:option){
            Quiz_Options quiz_options = quiz_optionsService.getQuizOptionByQuestionIdAndOptionNumber(question_id,option_count+1);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            quiz_options.setDescription(optionValue);
            if(option_count.equals(Long.parseLong(req.queryParams("correct_ans_"+question_id.toString()))-1))
                quiz_options.setOption_status(1);
            else quiz_options.setOption_status(0);

            session.update(quiz_options);

            session.getTransaction().commit();
            option_count++;
        }

        return quiz_edit(req);
    }

    public ModelAndView quiz_question_delete(Request req){
        Long question_id = Long.parseLong(req.params(":question_id"));
        Quiz_Questions currQuestion = quiz_questionsService.getQuizQuestionsByID(question_id);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currQuestion.setStatus(0);
        session.update(currQuestion);
        session.getTransaction().commit();


        return quiz_edit(req);
    }

    public ModelAndView quiz_question_add(Request req){
        Long quiz_id = Long.parseLong(req.params(":id"));

        String correct_ans = req.queryParams("correct_ans");
        String question = req.queryParams("question");
        Integer optionCount =1;
        List<String> option = new ArrayList<String>();
        while (optionCount<6)
        {
            option.add(req.queryParams("options"+optionCount.toString()));
            optionCount++;
        }
        List<Quiz_Questions> quizQuestions = quiz_questionsService.getQuizQuestionsByQuizID(quiz_id);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Quiz_Questions new_quiz_questions = new Quiz_Questions();
        new_quiz_questions.setQuiz_id(quiz_id);
        new_quiz_questions.setQuestion_description(question);
        new_quiz_questions.setQuestion_number(Long.valueOf(quizQuestions.size()+1));
        new_quiz_questions.setStatus(1);

        session.save(new_quiz_questions);
        System.out.println("Course ID:" + new_quiz_questions.getId());
        Long question_id = new_quiz_questions.getId();

        session.getTransaction().commit();

        Long option_count = Long.valueOf(0);
        for(String optionValue:option){
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Quiz_Options new_quiz_options = new Quiz_Options();
            new_quiz_options.setQuestion_id(question_id);
            new_quiz_options.setOption_number(option_count+1);
            new_quiz_options.setDescription(optionValue);
            new_quiz_options.setStatus(1);
            if(option_count == Long.parseLong(String.valueOf(correct_ans)))
                new_quiz_options.setOption_status(1);
            else new_quiz_options.setOption_status(0);

            session.save(new_quiz_options);
            System.out.println("Option ID:" + new_quiz_options.getId());
            Long option_id = new_quiz_options.getId();

            session.getTransaction().commit();
            option_count++;
        }



        return quiz_edit(req);
    }

    public ModelAndView quiz_performance(Request req){
        Map<String, Object> map = new HashMap<>();
        try {

//            User user = userService.getUserbyId((long) 1);
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
            Long Learner_ID = Long.parseLong(req.params(":learner_id"));
            Learner_course currCourse = learner_courseService.getLearnerCourseByCourseIdAndLearnerId(ID,Learner_ID);

            List<Quiz> pastQuiz = new ArrayList<Quiz>();
            pastQuiz = quizService.getQuizByCourseID(ID);

            ArrayList<Quiz_Learner> attemptedQuiz = new ArrayList<Quiz_Learner>();
            ArrayList<Quiz> notAttemptedQuiz = new ArrayList<Quiz>();

            for (int i = 0; i < pastQuiz.size(); i++) {
                System.out.println(String.valueOf(i) + "-" + quizLearnerService.alreadyAttempted(Learner_ID, pastQuiz.get(i).getId()));
                if (quizLearnerService.alreadyAttempted(Learner_ID, pastQuiz.get(i).getId())) {
                    List<Quiz_Learner> quiz_learners = quizLearnerService.getQuizLearner(Learner_ID, pastQuiz.get(i).getId());
                    for (Quiz_Learner ql_i: quiz_learners) {
                        attemptedQuiz.add(ql_i);
                    }
                } else {
                    notAttemptedQuiz.add(pastQuiz.get(i));
                }
            }
            map.put("courseId", ID);
            map.put("learnerID", Learner_ID);
            map.put("learnerCourse",currCourse);
            map.put("notAttemptedQuiz", notAttemptedQuiz);
            map.put("attemptedQuiz", attemptedQuiz);
            return new ModelAndView(map, "quiz/quiz_performance.ftl");
//            return singleview(req);
        }
        catch (NullPointerException ex)
        {
            return login(req);
        }

    }

    public ModelAndView pass_course(Request req) {
        Long ID = Long.parseLong(req.params(":id"));
        Long Learner_ID = Long.parseLong(req.params(":learner_id"));
        Learner_course currCourse = learner_courseService.getLearnerCourseByCourseIdAndLearnerId(ID,Learner_ID);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currCourse.setCompleted(2);
        session.update(currCourse);
        session.getTransaction().commit();
        return quiz_performance(req);
    }

    public ModelAndView fail_course(Request req) {
        Long ID = Long.parseLong(req.params(":id"));
        Long Learner_ID = Long.parseLong(req.params(":learner_id"));
        Learner_course currCourse = learner_courseService.getLearnerCourseByCourseIdAndLearnerId(ID,Learner_ID);
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currCourse.setCompleted(1);
        session.update(currCourse);
        session.getTransaction().commit();
        return quiz_performance(req);
    }

}
