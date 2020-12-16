package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Professor_CourseService {
    public Professor_Course getCourseProfessor(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Professor_Course Where course_id = "+course_id.toString()+" And status = '1' ");
        List<Professor_Course> courseList = q.list();

        session.close();
        for(Professor_Course pc: courseList){
            return pc;
        }
        return null;
    }

    public List<Professor_Course> getCourseByProfessor(Long user_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Professor_Course a, Course b Where a.course_id = b.id And a.user_id = "+user_id.toString());
        List<Professor_Course> courseList = q.list();

        session.close();
       return courseList;
    }


}
