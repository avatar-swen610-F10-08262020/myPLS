package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Learner_courseService {
    CourseService courseService = new CourseService();
    Course_DependencyService cdService = new Course_DependencyService();
    public Integer getRegisteredCount(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Learner_course Where course_id = "+course.getId().toString()+" And status = '1'");
        List<Learner_course> courseList = q.list();
        session.close();
        Calendar today = Calendar.getInstance();
        Calendar stoday = Calendar.getInstance();
        ArrayList<Learner_course> regLearnerCourse = new ArrayList<Learner_course>();

        for(Learner_course l_c: courseList) {
            String regDate = l_c.getCreated_date();
            String[] split1 = regDate.split(" ");
            String[] split2 = split1[0].split("-");
            stoday.set(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]) - 1, Integer.parseInt(split2[2]));
            Float diff = (float)(today.getTimeInMillis() - stoday.getTimeInMillis())/(24 * 60 * 60 * 1000);
            if (diff < 0)
                diff *= -1;
            // If the date when someone wants to register is within 46 days of someone else's registration
            if (diff < 46)
                regLearnerCourse.add(l_c);
        }
        if (regLearnerCourse.isEmpty()) {
            return 0;
        } else {
            return regLearnerCourse.size();
        }
    }

    public List<Learner_course> getLearnerByCourse(Long course_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Learner_course Where course_id = "+course_id.toString()+" And status = '1'");
        List<Learner_course> learner_courseList = q.list();
        return learner_courseList;
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
        Query q = session.createQuery("From Learner_course Where user_id = "+user_id.toString()+" And status = '1' And completed = '1'");
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

    public Learner_course getLearnerCourseByCourseIdAndLearnerId(Long course_id, Long learner_id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Learner_course q = (Learner_course) session.createQuery("From Learner_course Where user_id = " + learner_id.toString() + " And course_id = " + course_id.toString() + " And status = '1'").getSingleResult();
            return q;
        }
        catch (NoResultException ex){
            return null;
        }
    }

}
