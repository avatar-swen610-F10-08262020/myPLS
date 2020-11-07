package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Learner_courseService {
    public Integer getRegisteredCount(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Learner_course Where course_id = "+course.getId().toString()+" And status = '1'");
        List<Learner_course> courseList = q.list();
        session.close();
        if (courseList.isEmpty()) {
            return 0;
        } else {
            return  courseList.size();
        }
    }

    public Boolean registrationAvailable(Course course) {
        Integer totalSeats = Integer.parseInt(course.getClass_size());
        Integer alreadyRegistered = getRegisteredCount(course);
        if (totalSeats > alreadyRegistered) {
            return true;
        } else {
            return false;
        }
    }
}
