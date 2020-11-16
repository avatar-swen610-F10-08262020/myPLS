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
public class FeedbackController extends LoginController{
    CourseService cService = new CourseService();
    UserService userService = new UserService();
    Course_FeedbackService course_feedbackService = new Course_FeedbackService();

    public ModelAndView feedbackview(Request req, Response res) {
        Map<String, Object> map = new HashMap<>();
        try {
//            User user = userService.getUserbyId((long) 1);
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());

            Long ID = Long.parseLong(req.params(":id"));
            String feedback_text = req.queryParams("feedback_text");
            Integer rating_value = Integer.valueOf(req.queryParams("rating_value"));
            System.out.println(feedback_text +"---"+rating_value+"=="+ID);
            if (course_feedbackService.addFeedback(user, ID, feedback_text, rating_value)) {
                map.put("msg_type", "success");
                map.put("msg", "Thank you for the feedback");
                map.put("courseid", ID);
            } else {
                map.put("msg_type", "error");
                map.put("msg", "Database Error: Feedback couldn't be registered.");
                map.put("courseid", ID);
            }
            return new ModelAndView(map , "feedback/feedback.ftl");
        }
        catch (NullPointerException ex)
        {
            return login(req);
        }
    }
}
