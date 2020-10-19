package com.mypls.ui;

import com.mypls.model.User;
import com.mypls.model.UserDummy;
import com.mypls.model.UserService;
import com.mypls.util.HibernateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import spark.ModelAndView;
import spark.Request;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

public class ProfileController {
    private static final String USER_SESSION_ID = "user";
    UserService service = new UserService();
    Session session = null;

    public ModelAndView home(Request req) {
        //User user = getAuthenticatedUser(req);
//        User user = new User();
//        user.setEmail("pb8294@rit.edu");
//        user.setPassword("12345678");
        User user = getAuthenticatedUser(req);

        System.out.println("USER ACTUAL DATA: " + user.getFirst_name()+" "+user.getLast_name()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getUser_type_id()+" "+user.getId() );
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("uid", user.getUser_type_id());
            map.put("firstName", user.getFirst_name());
            map.put("lastName", user.getLast_name());
            map.put("password", user.getPassword());
            map.put("msg_type", "none");
            return new ModelAndView(map , "profile/profile.ftl");
        }
//        return (request, response) -> new ModelAndView(map , "login.ftl");
        return new ModelAndView(map , "login.ftl");
    }

    public ModelAndView updatePassword(Request req) {
        User user = getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        System.out.println("USER ACTUAL DATA psa: " + user.getFirst_name()+" "+user.getLast_name()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getUser_type_id()+" "+user.getId() );
//        User user = new User();
//        user.setEmail("pb8294@rit.edu");
//        user.setPassword("12345678");
//        User user = service.authenticateUser(user);
        map.put("firstName", user.getFirst_name());
        map.put("lastName", user.getLast_name());
        String password = req.queryParams("password");
        String newPassword = req.queryParams("newPassword");
        String repeatPassword = req.queryParams("repeatPassword");

        System.out.println(newPassword.isEmpty());

        if (!newPassword.isEmpty()) {
            System.out.println("new Password");
            if (newPassword.equals(repeatPassword)) {
                if (password.equals(user.getPassword())) {
                    try {
                        session = HibernateUtil.getSessionFactory().openSession();
                        session.beginTransaction();
                        user.setPassword(newPassword);
                        session.update(user);
                        session.flush();
                        session.getTransaction().commit();
                    } catch (HibernateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        session.close();
                    }
                    System.out.println("Curr password match");
                } else {
                    map.put("msg_type", "error");
                    map.put("message", "Password doesn't match with current password");
                    System.out.println("Curr password not match");

                    return new ModelAndView(map, "profile/profile.ftl");
                }
            } else {
                System.out.println("Blank new password");
                map.put("msg_type", "error");
                map.put("message","New password and repeated password do not match");
                return new ModelAndView(map , "profile/profile.ftl");
            }
        } else if (newPassword.isEmpty()) {
            System.out.println("Blank new password");
            map.put("msg_type", "error");
            map.put("message","Please do not leave new password field empty");
            return new ModelAndView(map , "profile/profile.ftl");
        }
        System.out.println(user.getFirst_name());
        map.put("msg_type", "notification");
        map.put("message","Password has been updated");
        return new ModelAndView(map , "profile/profile.ftl");

    }

    public ModelAndView updateGeneral(Request req) {
        User user = getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        System.out.println("USER ACTUAL DATA: " + user.getFirst_name()+" "+user.getLast_name()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getUser_type_id()+" "+user.getId() );
//        User user = new User();
//        user.setEmail("pb8294@rit.edu");
//        user.setPassword("12345678");
//        User user = service.authenticateUser(user);
        String firstName = req.queryParams("firstName");
        String lastName = req.queryParams("lastName");
        System.out.println(firstName + " " + lastName);

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            user.setFirst_name(firstName);
            user.setLast_name(lastName);
            session.update(user);
            session.flush();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(user.getFirst_name());
        map.put("UserType", user.getUser_type_id());
        map.put("firstName", user.getFirst_name());
        map.put("lastName", user.getLast_name());
        map.put("password", user.getPassword());
        map.put("msg_type", "notification");
        map.put("message","Profile has been updated");
        return new ModelAndView(map , "profile/profile.ftl");

    }

    private User getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
    }


}
