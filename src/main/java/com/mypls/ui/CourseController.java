package com.mypls.ui;

import com.mypls.model.*;
import com.mypls.util.HibernateUtil;
import com.mypls.util.SessionUtil;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseController {
    CourseService cService = new CourseService();
    Session session = null;
    SessionUtil sessionUtil = new SessionUtil();
    public ModelAndView home(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUserTypeID());
            map.put("Username", user.getFirst_name());
            List<Course> courses = cService.getAllCourses();
            try {
                map.put("courses", courses);
                System.out.println(map);
            } catch (NullPointerException ex) {
                System.out.println(ex.toString());
            }

            map.put("msg_type", "none");
            map.put("message", "none");
            return new ModelAndView(map , "course/home.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }

    }

    public ModelAndView create(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUserTypeID());
            map.put("Username", user.getFirst_name());
            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "course/create.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }
    }

    public ModelAndView registerClass(Request req) {
        Map<String, Object> map = new HashMap<>();
        String courseName = req.queryParams("course_name");
        String description = req.queryParams("description");
        String course_code = req.queryParams("course_code");
        Long class_size = Long.valueOf(req.queryParams("class_size"));
        String start_date = req.queryParams("start_date");
        String end_date = req.queryParams("end_date");

        System.out.println(courseName + description + course_code + class_size + start_date + end_date + courseName.isEmpty() + course_code.isEmpty());

        System.out.println(cService.alreadyAvailable(courseName, course_code));
        if (cService.alreadyAvailable(courseName, course_code)) {
            map.put("msg_type", "error");
            map.put("msg", "Course has already been created");
            System.out.println("Not Available");
            return new ModelAndView(map , "course/create.ftl");
        } else {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Course newCourse = new Course();
            newCourse.setCourse_name(courseName);
            newCourse.setClass_size(class_size);
            newCourse.setDescription(description);
            newCourse.setCourse_code(course_code);
            newCourse.setStart_date(start_date);
            newCourse.setEnd_date(end_date);
            session.save(newCourse);
            session.flush(); // I forgot this from the previous post
            session.getTransaction().commit();
            map.put("msg_type", "notification");
            map.put("msg", "Course is registered");
            return new ModelAndView(map , "course/create.ftl");
        }
    }

    public ModelAndView singleview(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUserTypeID());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
            Course currCourse = cService.getIndividualCourse(ID);

            map.put("course", currCourse);
            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "course/single.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }
    }

    public ModelAndView edit(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUserTypeID());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
            System.out.println(ID);
            Course course = cService.getIndividualCourse(ID);

            map.put("course", course);
            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "course/edit.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }
    }

    public ModelAndView updateClass(Request req) {
        Map<String, Object> map = new HashMap<>();
        Long courseId = Long.valueOf(req.queryParams("course_id"));
        String courseName = req.queryParams("course_name");
        String description = req.queryParams("description");
        String course_code = req.queryParams("course_code");
        Long class_size = Long.valueOf(req.queryParams("class_size"));
        String start_date = req.queryParams("start_date");
        String end_date = req.queryParams("end_date");

        System.out.println(courseId + "\n" + courseName + "\n" + description + "\n" + course_code + "\n" + class_size + "\n" + start_date + "\n" + end_date + "\n" + courseName.isEmpty() + "\n" + course_code.isEmpty());

        Course currCourse = cService.getIndividualCourse(courseId);
        System.out.println(currCourse);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currCourse.setCourse_name(courseName);
        currCourse.setClass_size(class_size);
        currCourse.setDescription(description);
        currCourse.setCourse_code(course_code);
        currCourse.setStart_date(start_date);
        currCourse.setEnd_date(end_date);
        session.update(currCourse);
        session.flush(); // I forgot this from the previous post
        session.getTransaction().commit();
        map.put("msg_type", "notification");
        map.put("msg", "Course is updated");
//        return new ModelAndView(map , "course/create.ftl");

        map.put("course", currCourse);
        return new ModelAndView(map , "course/edit.ftl");
    }

    public ModelAndView delete(Request req) {
        Long ID = Long.parseLong(req.params(":id"));
        if (!cService.deleteIndividualCourse(ID)) {
            Map<String, Object> map = new HashMap<>();
            List<Course> courses = cService.getAllCourses();
            map.put("courses", courses);
            System.out.println(map);
            map.put("msg_type", "delerror");
            map.put("msg", "Couldn't delete the course");
            return new ModelAndView(map , "course/home.ftl");
        } else {
            Map<String, Object> map = new HashMap<>();
            List<Course> courses = cService.getAllCourses();
            map.put("courses", courses);
            System.out.println(map);
            map.put("msg_type", "delmsg");
            map.put("msg", "Successfully deleted a course");
            return new ModelAndView(map, "course/home.ftl");
        }
    }
}
