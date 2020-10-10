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

        System.out.println("USER ACTUAL DATA: " + user.getFirst_name()+" "+user.getLast_Name()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getUserTypeID()+" "+user.getID() );
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            System.out.println(user.getFirst_name());
            map.put("uid", user.getUserTypeID());
            map.put("firstName", user.getFirst_name());
            map.put("lastName", user.getLast_Name());
            map.put("password", user.getPassword());
            map.put("msg_type", "none");
            return new ModelAndView(map , "profile/profile.ftl");
        }
//        return (request, response) -> new ModelAndView(map , "login.ftl");
        return new ModelAndView(map , "login.ftl");
    }

    public ModelAndView updateProfile(Request req) {
        User user = getAuthenticatedUser(req);
        Map<String, Object> map = new HashMap<>();
        System.out.println("USER ACTUAL DATA: " + user.getFirst_name()+" "+user.getLast_Name()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getUserTypeID()+" "+user.getID() );
//        User user = new User();
//        user.setEmail("pb8294@rit.edu");
//        user.setPassword("12345678");
//        User user = service.authenticateUser(user);

        UserDummy userData = new UserDummy();
        try {
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");

            BeanUtils.populate(userData, params);
            System.out.println("USER DATA FROM FORM: " + userData.getfirstName()+" "+userData.getlastName()+" "+userData.getEmail()+" "+userData.getPassword()+" "+userData.getrepeatPassword()+" "+userData.getuserTypeID() + " " + userData.getNewPassword().isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            halt(501);
            return null;
        }

        System.out.println(userData.getPassword().equals(user.getPassword()));

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            user.setFirst_Name(userData.getfirstName());
            user.setLast_Name(userData.getlastName());

            if (!userData.getNewPassword().isEmpty()) {
                System.out.println("new Password");
                if (userData.getPassword().equals(user.getPassword())) {
                    user.setPassword(userData.getNewPassword());
                    System.out.println("Curr password match");
                } else {
                    map.put("UserType", user.getUserTypeID());
                    map.put("firstName", user.getFirst_name());
                    map.put("lastName", user.getLast_Name());
                    map.put("password", user.getPassword());
                    map.put("msg_type", "error");
                    map.put("message","Current password is wrong");
                    System.out.println("Curr password not match");

                    return new ModelAndView(map , "profile/profile.ftl");
                }
            }
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
        map.put("UserType", user.getUserTypeID());
        map.put("firstName", user.getFirst_name());
        map.put("lastName", user.getLast_Name());
        map.put("password", user.getPassword());
        map.put("msg_type", "notification");
        map.put("message","Profile has been updated");
        return new ModelAndView(map , "profile/profile.ftl");

    }

    private User getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
    }


}
