package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

public class CourseService {

    public List<Course> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course Where status = '1'");
        List<Course> courseList = q.list();

        session.close();
        return courseList;
    }

    public Course getIndividualCourse(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course ");

        List<Course> resultList = q.list();
        for (Course courseData : resultList) {
            if(courseData.getId().equals(id))
                return courseData;
        }
        return null;
    }

    public boolean alreadyAvailable(String courseName, String course_code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course ");

        List<Course> resultList = q.list();
        for (Course courseData : resultList) {
            if(courseData.getCourse_name().equals(courseName) || courseData.getCourse_code().equals(course_code))
                return true;
        }
        return false;
    }

    public boolean deleteIndividualCourse(Long id) {
        Course currCourse = getIndividualCourse(id);
        if (currCourse == null) {
            return false;
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            currCourse.setStatus(false);
            session.update(currCourse);
            session.flush();
            session.getTransaction().commit();
            return true;
        }
    }

}
