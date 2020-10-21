package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Course_FeedbackService {
    public List<Course_Feedback> getFeedbackByCourse(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course_Feedback a, User b Where a.user_id = b.id And a.course_id = "+course_id.toString()+" ");
//        Query q = session.createQuery("From Course_Feedback course_id = "+course_id.toString());

        List<Course_Feedback> resultList = q.list();
        return resultList;
    }
}
