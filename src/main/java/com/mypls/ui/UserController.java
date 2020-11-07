package com.mypls.ui;

import com.mypls.appl.Authenticate;
import com.mypls.appl.UserAuthentication;
import com.mypls.model.*;
import com.mypls.util.HibernateUtil;
import com.mypls.util.SessionUtil;
import org.hibernate.Session;
import spark.ModelAndView;
import spark.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends LoginController{
    CourseService cService = new CourseService();
    UserService userService = new UserService();
    Professor_CourseService professorCourseService = new Professor_CourseService();
    Course_DependencyService cdService = new Course_DependencyService();
    Course_FeedbackService cfService = new Course_FeedbackService();
    Session session = null;
    SessionUtil sessionUtil = new SessionUtil();

    public ModelAndView user_details(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
//            Course currCourse = cService.getIndividualCourse(ID);
//            Professor_Course professor_course = professorCourseService.getCourseProfessor(ID);
//            User userProfessor = userService.getUserbyId(professor_course.getUser_id());
//            List<Course_Feedback> feedbackList = cfService.getFeedbackByCourse(ID);
//
//            map.put("feedbackList",feedbackList);
//            map.put("professor",userProfessor);
//            map.put("course", currCourse);
            User userData = userService.getUserbyId(ID);
            List<Professor_Course> professor_course = professorCourseService.getCourseByProfessor(ID);
            map.put("courses", professor_course);
            map.put("user", userData);
            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "user/single.ftl");
        }
        catch (NullPointerException ex)
        {
            return login(req);
        }
    }

    public ModelAndView user_edit(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
            System.out.println(ID);
            User user_details = userService.getUserbyId(ID);


            map.put("user",user_details);

            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "user/edit.ftl");
        }
        catch (NullPointerException ex)
        {
            return login(req);
        }
    }

    public ModelAndView user_update(Request req) {
        Map<String, Object> map = new HashMap<>();
        Long ID = Long.parseLong(req.params(":id"));
        String first_name = req.queryParams("first_name");
        String last_name = req.queryParams("last_name");
        Integer user_type_id = Integer.parseInt(req.queryParams("userTypeID"));
        Integer status = Integer.parseInt(req.queryParams("status"));



        System.out.println(first_name + "\n" + last_name + "\n" + user_type_id + "\n" );

        User user = userService.getUserbyId(ID);
        System.out.println(user);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setUser_type_id(user_type_id);
        user.setStatus(status);

        session.update(user);
        session.getTransaction().commit();
        map.put("msg_type", "notification");
        map.put("msg", "Course is updated");
//        return new ModelAndView(map , "course/create.ftl");

        return user_edit(req);

    }

}
