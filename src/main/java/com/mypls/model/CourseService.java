package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CourseService {

    public List<Course> getAllCourses() {
        System.out.println("course collecting");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course ");
        List<Course> resultList = null;
        try{
            resultList = q.list();
            for (Course course : resultList) {
                System.out.println(course.getCourse_name());
            }

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        session.close();
        return resultList;
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
