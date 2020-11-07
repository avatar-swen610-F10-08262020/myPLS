package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Course_DependencyService {
    CourseService cService = new CourseService();
    public List<Course_Dependency> getCourseDependency(Long course_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course_Dependency Where dependent_id = "+course_id.toString()+" And status = '1'");
        List<Course_Dependency> courseList = q.list();
        session.close();
        return courseList;
    }

    public Course_Dependency getIndividualCourseDependency(Long parent_id, Long dependent_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course_Dependency Where dependent_id = "+dependent_id.toString()+" And status = '1' And parent_id = "+parent_id.toString());
        List<Course_Dependency> courseList = q.list();

        for(Course_Dependency course_dependency:courseList){
            return course_dependency;
        }
        return null;
    }

    public List<Course> getDependentCourses(Long course_id) {
        List<Course_Dependency> course_dependency = getCourseDependency(course_id);
        ArrayList<Course> offeredList = new ArrayList<Course>();
        if (!course_dependency.isEmpty()) {
            for (Course_Dependency cd:course_dependency){
                offeredList.add(cService.getIndividualCourse(cd.getParent_id()));
            }

        }
        return offeredList;
    }


}
