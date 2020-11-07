package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Quiz_OptionsService {
    public List<Quiz_Options> getQuizOptionByQuizId(Long quiz_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        Query q = session.createQuery("From Quiz as a,Quiz_Questions as b,Quiz_Options as c Where a.id = "+quiz_id.toString() +
//                " And b.quiz_id = a.id And c.question_id = b.id ");
        Query q = session.createQuery("From Quiz_Options Where question_id in (Select id From Quiz_Questions Where quiz_id = "+quiz_id.toString()+" And status = 1) And status=1");

        List<Quiz_Options> quiz_options = q.list();
        return quiz_options;
    }

    public Quiz_Options getQuizOptionById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        Query q = session.createQuery("From Quiz as a,Quiz_Questions as b,Quiz_Options as c Where a.id = "+quiz_id.toString() +
//                " And b.quiz_id = a.id And c.question_id = b.id ");
        Query q = session.createQuery("From Quiz_Options Where id = "+id.toString()+ " And status='1'");

        List<Quiz_Options> quiz_options = q.list();
        return quiz_options.get(0);
    }
    public Quiz_Options getQuizOptionByQuestionIdAndOptionNumber(Long question_id, Long option_number){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz_Options Where question_id = "+question_id.toString()+" And status=1 And option_number = "+option_number.toString()+ " And status='1'");

        List<Quiz_Options> quiz_options = q.list();
        return quiz_options.get(0);
    }
}
