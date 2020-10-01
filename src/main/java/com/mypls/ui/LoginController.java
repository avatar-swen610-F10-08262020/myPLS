package com.mypls.ui;

import com.mypls.model.*;


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

public class LoginController {
    UserService service = new UserService();
    EmailService emailService = new EmailService();
    private static final String USER_SESSION_ID = "user";
    private static final String ACL_SESSION_ID = "acl";
    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private Pattern pattern;
    private Matcher matcher;


    private Map<String, Object> map;
    Session session = null;
    AccessControlListService accessControlListService = new AccessControlListService();

    public ModelAndView login(Request req) {
        User user =getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUserTypeID());
            map.put("Username", user.getFirst_name());
            return new ModelAndView(map , "home.ftl");
        }
//        return (request, response) -> new ModelAndView(map , "login.ftl");
        return new ModelAndView(map , "login.ftl");

    }

    public ModelAndView login_user(Request req) {
        User user =getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUserTypeID());
            map.put("Username", user.getFirst_name());
            return new ModelAndView(map , "home.ftl");
        }

        return new ModelAndView(map , "login_user.ftl");
    }

    public ModelAndView signup_user(Request req) {

        User user =getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUserTypeID());
            map.put("Username", user.getFirst_name());
            return new ModelAndView(map , "home.ftl");
        }
//        return (request, response) -> new ModelAndView(map , "signup_user.ftl");
        return new ModelAndView(map , "signup_user.ftl");
    }

    public ModelAndView logout_user(Request req){
        Map<String, Object> map = new HashMap<>();
        removeAuthenticatedUser(req);
        return new ModelAndView(map , "logout.ftl");
    }

    public  ModelAndView authenticateUser(Request req, Response res){
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
            System.out.println("Lenght of ACL: here");
//            List<Access_Control_List> acl = accessControlListService.accessControlList(result);
            addAuthenticatedUser(req, result);
//            System.out.println("Lenght of ACL:"+ acl.size());
//            map.put("acl", acl);
            map.put("UserType", result.getUserTypeID());
            map.put("Username", result.getFirst_name());

        } else {
            map.put("error", "User Not Found.");
            return new ModelAndView(map , "login_user.ftl");
        }
        return new ModelAndView(map , "home.ftl");

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
                    user.setFirst_Name(userData.getfirstName());
                    user.setLast_Name(userData.getlastName());
                    user.setEmail(userData.getEmail());
                    user.setPassword(userData.getPassword());
                    user.setUserTypeID(userData.getuserTypeID());
                    user.setStatus(0);
                }
                else{
                    map.put("error","Both password should match.");
                    return new ModelAndView(map , "signup_user.ftl");
                }

            }
            else{
                map.put("error","Password Should Be between 8 and 40 characters long\n" +
                        "Contain at least one digit.\n" +
                        "Contain at least one lower case character.\n" +
                        "Contain at least one upper case character.\n" +
                        "Contain at least on special character from [ @ # $ % ! . ]");
                return new ModelAndView(map , "signup_user.ftl");
            }

        } catch (Exception e) {
            e.printStackTrace();
            halt(501);
            return null;
        }
        if(!service.checkUser(user)){
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(user);
                session.flush(); // I forgot this from the previous post
                Long ID = user.getID();
                System.out.println("user ID:"+ID.toString());
                emailService.sendActivationEmail(user, ID);
                session.getTransaction().commit();



            } catch (HibernateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                session.close();
                map.put("message","You account has been successfully created. Please check your email. We have sent an activation link to your email.");
                return new ModelAndView(map , "login_user.ftl");
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
        } finally {
            session.close();
            Map<String, Object> map = new HashMap<>();
//        return (request, response) -> new ModelAndView(map , "login_user.ftl");
            return new ModelAndView(map , "login_user.ftl");
        }

    }

    public ModelAndView forgot_password(Request req){
        User user =getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("UserType", user.getUserTypeID());
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


    private void addAuthenticatedUser(Request request, User u) {
        request.session().attribute(USER_SESSION_ID, u);
//        request.session().attribute(ACL_SESSION_ID, ACL);

    }



    private void removeAuthenticatedUser(Request request) {
        request.session().removeAttribute(USER_SESSION_ID);
    }

    private User getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
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
