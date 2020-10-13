package com.mypls.ui;

import com.mypls.model.*;
import spark.ModelAndView;
import spark.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseController {
    CourseService cService = new CourseService();

    public ModelAndView home(Request req) {
        System.out.println("course date");
        Map<String, Object> map = new HashMap<>();
        List<Course> courses = cService.getAllCourses();
//        System.out.println(courses);
        return new ModelAndView(map , "course/home.ftl");
    }

    public ModelAndView singleview(Request req) {
        Map<String, Object> map = new HashMap<>();
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
