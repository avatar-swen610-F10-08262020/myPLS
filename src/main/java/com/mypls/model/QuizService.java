package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class QuizService {
    public List<Quiz> getQuizByCourseID(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz Where course_id = "+course_id.toString()+" ");
        List<Quiz> quizList = q.list();

        session.close();
        return quizList;
    }
}
