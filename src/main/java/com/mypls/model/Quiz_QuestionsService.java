package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Quiz_QuestionsService {
    public Quiz_Questions getQuizQuestionsByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz_Questions Where id = "+id.toString()+" And status=1");
        List<Quiz_Questions> quizList = q.list();

        session.close();
        return quizList.get(0);
    }

    public List<Quiz_Questions> getQuizQuestionsByQuizID(Long quiz_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz_Questions Where quiz_id = "+quiz_id.toString()+" And status=1");
        List<Quiz_Questions> questionList = q.list();

        session.close();
        return questionList;
    }
}
