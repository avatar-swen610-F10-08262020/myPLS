package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Learner_courseService {
    CourseService courseService = new CourseService();
    Course_DependencyService cdService = new Course_DependencyService();
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

    public Boolean checkEnrollCriteria(Long user_id, List<Course> dependentCourses) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Learner_course Where user_id = "+user_id.toString()+" And status = '1'");
        List<Learner_course> courseList = q.list();
        session.close();
        if (courseList.size() < dependentCourses.size()) {
            return false;
        } else {
            Integer counter = 0;
            Integer matched = 0;
            for (Learner_course lc : courseList) {
                counter += 1;
                for (Course css: dependentCourses) {
                    if (css.getId() == lc.getCourse_id()) {
                        matched += 1;
                    }
                }
            }

            if (matched < counter) {
                return false;
            } else {
                return true;
            }
        }


    }

    public Boolean enroll(User user, Course course) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Learner_course learner_course = new Learner_course();
            learner_course.setCourse_id(course.getId());
            learner_course.setUser_id(user.getId());
            learner_course.setStatus(1);
            learner_course.setCompleted(0);

            session.save(learner_course);
            System.out.println("Learner Course ID:" + learner_course.getId());
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Course> getRegisteredCourses(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Learner_course Where user_id = "+user.getId().toString()+" And status = '1'");
        List<Learner_course> courseList = q.list();
        ArrayList<Course> courses = new ArrayList<Course>();
        for (Learner_course l_c: courseList) {
            courses.add(courseService.getIndividualCourse(l_c.getCourse_id()));
        }
        return courses;
    }

}
