package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.*;
import java.util.List;

public class CourseService {

    public List<Course> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course ");
        List<Course> resultList = q.list();
        System.out.println(resultList);
        return null;
//        return resultList;
    }

//    public Course getIndividualCourse(int id) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Query q = session.createQuery("From Course ");
//
//        List<Course> resultList = q.list();
//        for (Course courseData : resultList) {
//            if(courseData.getId().equals(id))
//                return courseData;
//        }
//        return null;
//    }
}
