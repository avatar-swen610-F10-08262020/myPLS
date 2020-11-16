package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Course_FeedbackService {
    public List<Course_Feedback> getFeedbackByCourse(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course_Feedback a, User b Where a.user_id = b.id And a.course_id = "+course_id.toString()+" And a.status = '1'");
//        Query q = session.createQuery("From Course_Feedback course_id = "+course_id.toString());

        List<Course_Feedback> resultList = q.list();
        return resultList;
    }

    public Double getRatingByCourse(Long course_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double q = (Double) session.createQuery("Select NULLIF(avg(rating),0) From Course_Feedback Where course_id = "+ course_id.toString() + " And status = 1").getSingleResult();
        if(q == null)
            return Double.valueOf(5);
        return q;
    }


}
