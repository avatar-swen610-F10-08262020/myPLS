package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class Course_DependencyService {
    public List<Course_Dependency> getCourseDependency(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course_Dependency Where dependent_id = "+course_id.toString()+" And status = '1'");
        List<Course_Dependency> courseList = q.list();

        session.close();
        return courseList;
    }

    public Course_Dependency getIndividualCourseDependency(Long parent_id, Long dependent_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course_Dependency Where dependent_id = "+dependent_id.toString()+" And parent_id = "+parent_id.toString());
        List<Course_Dependency> courseList = q.list();
        for(Course_Dependency course_dependency:courseList){
            return course_dependency;
        }
        return null;
    }


}
