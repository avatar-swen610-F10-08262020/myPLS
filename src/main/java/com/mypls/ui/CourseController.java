package com.mypls.ui;

import com.mypls.model.*;
import com.mypls.util.HibernateUtil;
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

    public ModelAndView home(Request req) {
        Map<String, Object> map = new HashMap<>();
        List<Course> courses = cService.getAllCourses();
        map.put("courses", courses);
        System.out.println(map);
        map.put("msg_type", "none");
        map.put("message", "none");
        return new ModelAndView(map , "course/home.ftl");
    }

    public ModelAndView create(Request req) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg_type", "none");
        map.put("msg", "none");
        return new ModelAndView(map , "course/create.ftl");
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
        Long ID = Long.parseLong(req.params(":id"));
        Course currCourse = cService.getIndividualCourse(ID);
        Map<String, Object> map = new HashMap<>();
        map.put("course", currCourse);
        return new ModelAndView(map , "course/single.ftl");
    }

    public ModelAndView update(Request req) {
        Map<String, Object> map = new HashMap<>();
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
