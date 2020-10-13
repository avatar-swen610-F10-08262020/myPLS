package com.mypls.ui;

import com.mypls.model.*;
import spark.ModelAndView;
import spark.Request;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseController {
    CourseService cService = new CourseService();

    public ModelAndView home(Request req) {
        Map<String, Object> map = new HashMap<>();
        List<Course> courses = cService.getAllCourses();
        map.put("courses", courses);
        System.out.println(map);
        return new ModelAndView(map , "course/home.ftl");
    }

    public ModelAndView create(Request req) {
        Map<String, Object> map = new HashMap<>();
        List<Course> courses = cService.getAllCourses();
        map.put("courses", courses);
        System.out.println(map);
        return new ModelAndView(map , "course/home.ftl");
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
        Map<String, Object> map = new HashMap<>();
        return new ModelAndView(map , "course/home.ftl");
    }
}
