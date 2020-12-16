package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class QuizLearnerService {

    public List<Quiz_Learner> getQuizLearner(Long userid, Long quizid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Quiz_Learner Where learner_id = "+userid.toString()+" And quiz_id = "+quizid.toString());
        List<Quiz_Learner> quiz_learner = q.list();
        session.close();
        return quiz_learner;
    }

    public Boolean alreadyAttempted(Long userid, Long quizid) {
        List<Quiz_Learner> quiz_learner = this.getQuizLearner(userid, quizid);
        if (quiz_learner.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
