package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.*;

public class CourseService {

    public List<Course> getAllCourses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course Where status = '1'");
        List<Course> courseList = q.list();

        session.close();
        return courseList;
    }

    public List<Course> getAllOfferedCourses(ArrayList<Course> preRegisteredCourse) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Calendar today = Calendar.getInstance();
        Calendar stoday = Calendar.getInstance();
//        System.out.println(today.get);
        Query q = session.createQuery("From Course Where status = '1'");
        List<Course> courseList = q.list();
        ArrayList<Course> offeredList = new ArrayList<Course>();
        for (Course courseData : courseList) {
            if (courseData.getStart_date().isEmpty()) {
                continue;
            } else {
                String stDate = courseData.getStart_date();
                String[] splitDate = stDate.split("-");
                stoday.set(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]) - 1, Integer.parseInt(splitDate[2]));
                Float diff = (float)(stoday.getTimeInMillis() - today.getTimeInMillis())/(24 * 60 * 60 * 1000);
                if ((diff > 0 && diff <= 30) || (diff < 0 && -diff <=15)) {
//                    System.out.println(courseData);
                    offeredList.add(courseData);
                }

            }
        }

        ArrayList<Course> finalOfferedList = new ArrayList<Course>();
        for (Course courseData : offeredList) {
            Integer flag = 0;
            for (Course pre_course: preRegisteredCourse) {
                if (courseData.getId() == pre_course.getId()) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                finalOfferedList.add(courseData);
            }
        }
        session.close();
//        System.out.println(offeredList);



        return finalOfferedList;
    }


    public Course getIndividualCourse(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From Course ");

        List<Course> resultList = q.list();
        for (Course courseData : resultList) {
            if(courseData.getId().equals(id)) {
//                System.out.print(courseData);
                return courseData;
            }
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
            currCourse.setStatus(0);
            session.update(currCourse);
            session.flush();
            session.getTransaction().commit();
            return true;
        }
    }

}
