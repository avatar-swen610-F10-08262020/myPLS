package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.HibernateException;
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

    public Integer feedbackGiven(User user, Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course_Feedback a Where a.user_id = "+user.getId().toString()+" And a.course_id = "+course_id.toString()+" And a.status = '1'");
//        Query q = session.createQuery("From Course_Feedback course_id = "+course_id.toString());

        List<Course_Feedback> resultList = q.list();
//        System.out.println("Size:" + resultList.size());
        if (resultList.size() > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public Boolean addFeedback(User user, Long courseId, String feedback_text, Integer rating) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Course_Feedback course_feedback = new Course_Feedback();
            course_feedback.setUser(user);
            course_feedback.setCourse_id(courseId);
            course_feedback.setUser_id(user.getId());
            course_feedback.setFeedback(feedback_text);
            course_feedback.setRating(rating);
            course_feedback.setStatus(1);

            session.save(course_feedback);
            System.out.println("Feedback ID:" + course_feedback.getId());
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

}
