package com.mypls.ui;

import com.mypls.model.*;
import com.mypls.util.HibernateUtil;
import com.mypls.util.SessionUtil;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.*;

public class EnrollController {
    CourseService cService = new CourseService();
    UserService userService = new UserService();
    Course_DependencyService cdService = new Course_DependencyService();
    Professor_CourseService professorCourseService = new Professor_CourseService();
    Course_FeedbackService cfService = new Course_FeedbackService();
    Learner_courseService learner_courseService = new Learner_courseService();
    SessionUtil sessionUtil = new SessionUtil();
    public ModelAndView home(Request req) {
//        User user = userService.getUserbyId((long) 1);
        User user = sessionUtil.getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        Calendar today = Calendar.getInstance();
        if (today.get(Calendar.MONTH) <=5) {
            map.put("semester", "Spring");
        } else {
            map.put("semester", "Fall");
        }
        ArrayList<Course> preRegisteredCourses = learner_courseService.getRegisteredCourses(user);
        List<Course> allCourses = cService.getAllOfferedCourses(preRegisteredCourses);
//        System.out.println(allCourses);
        try {
            map.put("courses", allCourses);
//            System.out.println(map);
        } catch (NullPointerException ex) {
            System.out.println(ex.toString());
        }
        map.put("msg_type", "none");
        map.put("message", "none");
        return new ModelAndView(map, "enroll/home.ftl");
    }

    public ModelAndView show(Request req) {
        Map<String, Object> map = new HashMap<>();
        Long ID = Long.parseLong(req.params(":id"));
//        User user = userService.getUserbyId((long) 1);
        User user = sessionUtil.getAuthenticatedUser(req);
        Course currCourse = cService.getIndividualCourse(ID);
        Professor_Course professor_course = professorCourseService.getCourseProfessor(ID);
        User userProfessor = userService.getUserbyId(professor_course.getUser_id());
        List<Course_Feedback> feedbackList = cfService.getFeedbackByCourse(ID);
        List<Course> dependentCourses = cdService.getDependentCourses(ID);
        System.out.println(dependentCourses.size());
        map.put("eligible", "yes");
        if (learner_courseService.checkEnrollCriteria(user.getId(), dependentCourses) == false) {
            map.put("eligible", "no");
        }
        map.put("dependent_course", dependentCourses);
        map.put("dependent_size", dependentCourses.size());
        map.put("course", currCourse);
        map.put("professor",userProfessor);
        map.put("feedbackList",feedbackList);
        map.put("msg_type", "none");
        map.put("msg", "none");
        if (learner_courseService.registrationAvailable(currCourse)) {
            map.put("regis_available", "yes");
        } else {
            map.put("regis_available", "no");
        }
        System.out.println(map);
        return new ModelAndView(map , "enroll/single.ftl");
    }

    public ModelAndView enroll(Request req) {
        Map<String, Object> map = new HashMap<>();
        Long ID = Long.parseLong(req.params(":id"));
//        User user = userService.getUserbyId((long) 1);
        User user = sessionUtil.getAuthenticatedUser(req);
        Course course = cService.getIndividualCourse(ID);
        List<Course> dependentCourses = cdService.getDependentCourses(course.getId());
        if (dependentCourses.size() == 0) {
            Boolean enrolled = learner_courseService.enroll(user, course);
            if (enrolled) {
                map.put("msg_type", "notification");
                map.put("msg", "Enrolled to the course");
            } else {
                map.put("msg_type", "error");
                map.put("msg", "Couldn't enroll. Database error!");
            }
            return home(req);
        } else {
            if (learner_courseService.checkEnrollCriteria(user.getId(), dependentCourses)) {
                Boolean enrolled = learner_courseService.enroll(user, course);
                if (enrolled) {
                    map.put("msg_type", "notification");
                    map.put("msg", "Enrolled to the course");
                } else {
                    map.put("msg_type", "error");
                    map.put("msg", "Couldn't enroll. Database error!");
                }
            } else {
                map.put("msg_type", "error");
                map.put("msg", "Couldn't enroll. Prerequisite not complete!");
            }
            return home(req);
        }
    }
}
