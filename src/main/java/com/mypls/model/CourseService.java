package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CourseService {

    public List<Course> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course");
        List<Course> resultList = null;
        try{
            resultList = q.list();
            System.out.println(resultList);
//            for (Course course : resultList) {
//                System.out.println(course.getCourse_name());
//            }

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        session.close();
        return resultList;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course ");
        List<Course> resultList = q.list();
        for (Course courseData : resultList) {
            if(courseData.getId().equals(id))
                session.delete(courseData);
                session.close();
                return true;
        }
        return false;
    }

}
