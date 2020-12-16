package com.mypls.ui;

import com.mypls.model.*;
import com.mypls.util.SessionUtil;

import com.mypls.util.HibernateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spark.Spark.halt;

public class LoginController{
    UserService service = new UserService();
    EmailService emailService = new EmailService();
    SessionUtil sessionUtil = new SessionUtil();
    Professor_CourseService professorCourseService = new Professor_CourseService();


    private static final String ACL_SESSION_ID = "acl";
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private Pattern pattern;
    private Matcher matcher;


    private Map<String, Object> map;
    Session session = null;
    AccessControlListService accessControlListService = new AccessControlListService();

    public ModelAndView login(Request req) {
        User user = sessionUtil.getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            if(user.getUser_type_id() == 1){
                List<User> users = service.getAllUser();
                System.out.println(users.size());
                map.put("users", users);
            }
            else{
                return new CourseController().assignedCourseList(req);
            }
            return new ModelAndView(map , "home.ftl");
        }
//        return (request, response) -> new ModelAndView(map , "login.ftl");
        return new ModelAndView(map , "login.ftl");

    }

    public ModelAndView login_user(Request req) {
        User user = sessionUtil.getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            if(user.getUser_type_id() == 1){
                List<User> users = service.getAllUser();
                System.out.println(users.size());
                map.put("users", users);
            }
            else if(user.getUser_type_id() == 2){
                return new CourseController().assignedCourseList(req);
            }

            return new ModelAndView(map , "home.ftl");
        }

        return new ModelAndView(map , "login_user.ftl");
    }

    public ModelAndView signup_user(Request req) {

        User user = sessionUtil.getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            if(user.getUser_type_id() == 1){
                List<User> users = service.getAllUser();
                System.out.println(users.size());
                map.put("users", users);
            }
            else{
                return new CourseController().assignedCourseList(req);
            }
        }
//        return (request, response) -> new ModelAndView(map , "signup_user.ftl");
        return new ModelAndView(map , "signup_user.ftl");
    }

    public ModelAndView logout_user(Request req){
        Map<String, Object> map = new HashMap<>();
        sessionUtil.removeAuthenticatedUser(req);
        return new ModelAndView(map , "logout.ftl");
    }

    public  ModelAndView authenticateUser(Request req){
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        try {
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");
            BeanUtils.populate(user, params);
        } catch (Exception e) {
            halt(501);
            return null;
        }
        User result = service.authenticateUser(user);
        if(result != null) {
            System.out.println("Length of ACL: here");
            sessionUtil.addAuthenticatedUser(req, result);

            if(result.getUser_type_id() == 1){
                List<User> users = service.getAllUser();
                System.out.println(users.size());
                map.put("users", users);
            }
            else if(result.getUser_type_id() == 2){
                return new CourseController().assignedCourseList(req);
            }
            else if(result.getUser_type_id() == 3){
                return new CourseController().registeredCourseList(req);
            }
            System.out.println(map);
            map.put("UserType", result.getUser_type_id());
            map.put("Username", result.getFirst_name());
            return new ModelAndView(map , "home.ftl");

        } else {
            map.put("error", "User Not Found.");
            return new ModelAndView(map , "login_user.ftl");
        }


    }


    public ModelAndView register_user(Request req, Response res){
        Map<String, Object> map = new HashMap<>();
        UserDummy userData = new UserDummy();
        User user = new User();

        try {
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");

            BeanUtils.populate(userData, params);
            System.out.println(userData.getrepeatPassword()+" "+userData.getPassword());
            if(validate(userData.getPassword())) {
                if(userData.getPassword().equals(userData.getrepeatPassword())){
                    user.setFirst_name(userData.getfirstName());
                    user.setLast_name(userData.getlastName());
                    user.setEmail(userData.getEmail());
                    user.setPassword(userData.getPassword());
                    user.setUser_type_id(userData.getuserTypeID());
                    user.setStatus(0);
                }
                else{
                    map.put("error","Both password should match.");
                    return new ModelAndView(map , "signup_user.ftl");
                }

            }
            else{
                map.put("error","<ul><li>Password Should Be between 8 and 40 characters long</li>\n" +
                        "<li>Contain at least one digit.</li>\n" +
                        "<li>Contain at least one lower case character.</li>\n" +
                        "<li>Contain at least one upper case character.</li>\n" +
                        "<li>Contain at least on special character from [ @ # $ % ! . ]</li></ul>");
                return new ModelAndView(map , "signup_user.ftl");
            }

        } catch (Exception e) {
            e.printStackTrace();
            halt(501);
            return new ModelAndView(map , "signup_user.ftl");
        }
        if(!service.checkUser(user)){
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(user);
                session.flush(); // I forgot this from the previous post
                Long ID = user.getId();
                System.out.println("user ID:"+ID.toString());
                emailService.sendActivationEmail(user, ID);
                session.getTransaction().commit();
                map.put("message","You account has been successfully created. Please check your email. We have sent an activation link to your email. If you do not receive the email, contact admin to activate your account.");
                return new ModelAndView(map , "login_user.ftl");


            } catch (HibernateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new ModelAndView(map , "signup_user.ftl");
            }

        }
        else
            map.put("error","Email Address Already Exist.");
        return new ModelAndView(map , "signup_user.ftl");
    }

    public ModelAndView activate_user(Request req){
        Long ID = Long.parseLong(req.params(":id"));
        System.out.println("User ID to activate"+req.params(":id"));
        try{
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            User user = session.load(User.class, ID);
            user.setStatus(1);
            session.update(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            login(req);
        }
        Map<String, Object> map = new HashMap<>();
//        return (request, response) -> new ModelAndView(map , "login_user.ftl");
        return new ModelAndView(map , "login_user.ftl");


    }

    public ModelAndView forgot_password(Request req){
        User user = sessionUtil.getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            return new ModelAndView(map , "home.ftl");
        }
        return new ModelAndView(map , "forgot_password.ftl");
    }
    public ModelAndView forgot_password_user(Request req){
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        String response = "";
        try {
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");
            BeanUtils.populate(user, params);
            response = emailService.sendForgotPasswordEmail(user.getEmail());
            if(response == null){
                map.put("error","No User Exist With This Email.");
                return new ModelAndView(map , "forgot_password.ftl");
            }

        } catch (Exception e) {
            halt(501);
            return null;
        }
        map.put("message","Please check Your Email.");
        return new ModelAndView(map , "login_user.ftl");
    }



    private List<Access_Control_List> getACL(Request request) {
        return request.session().attribute(ACL_SESSION_ID);
    }


    public boolean validate(final String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

}
