package com.mypls.ui;

import com.mypls.model.User;
import com.mypls.model.UserDummy;


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
import com.mypls.model.UserService;

import java.util.*;

import static spark.Spark.halt;

public class LoginController {
    UserService service = new UserService();
    private static final String USER_SESSION_ID = "user";
    public TemplateViewRoute login() {
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome to MyPLS!");
        return (request, response) -> new ModelAndView(vm , "login.ftl");
    }

    public TemplateViewRoute login_user() {
        Map<String, Object> vm = new HashMap<>();
        return (request, response) -> new ModelAndView(vm , "login_user.ftl");
    }

    public TemplateViewRoute signup_user() {
        Map<String, Object> vm = new HashMap<>();
        return (request, response) -> new ModelAndView(vm , "signup_user.ftl");
    }

    public ModelAndView logout_user(Request req){
        Map<String, Object> vm = new HashMap<>();
        removeAuthenticatedUser(req);
        return new ModelAndView(vm , "login.ftl");
    }

    public  ModelAndView authenticate_user(Request req, Response res){
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
        User result = service.authenticate_user(user);
        if(result != null) {
            addAuthenticatedUser(req, result);
            map.put("Username", result.getFirst_name());

            return new ModelAndView(map , "home.ftl");
        } else {
            map.put("error", "User Not Found.");
        }

        return new ModelAndView(map , "login_user.ftl");
    }

    public ModelAndView register_user(Request req, Response res){
        Map<String, Object> map = new HashMap<>();
        UserDummy userData = new UserDummy();
        User user = new User();

        try {
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(req.body(), params, "UTF-8");
            //////////////////////////////////////////////////
//            Set keySet = params.keySet();
//            Iterator keyIterator = keySet.iterator();
//
//            while( keyIterator.hasNext( ) ) {
//                Object key = keyIterator.next( );
//                System.out.print( "Key: " + key + ", " );
//
//                Collection values = (Collection) map.get( key );
//                Iterator valuesIterator = values.iterator( );
//                while( valuesIterator.hasNext( ) ) {
//                    System.out.print( "Value: " + valuesIterator.next( ) + ". " );
//                }
//                System.out.print( "\n" );
//            }
//            //////////////////////////////////////////////////

            BeanUtils.populate(userData, params);
            user.setFirst_Name(userData.getfirstName());
            user.setLast_Name(userData.getlastName());
            user.setEmail(userData.getEmail());
            user.setPassword(userData.getPassword());
            user.setUserTypeID(userData.getuserTypeID());
//            System.out.println(user.getEmail());
//            System.out.println(user.getfirstName());
//            System.out.println(user.getlastName());
//            System.out.println(user.getPassword());
//            System.out.println(user.getuserTypeID());


        } catch (Exception e) {
            e.printStackTrace();
            halt(501);
            return null;
        }
        if(!service.check_user(user)){
            Session session = null;
            try{
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
//                System.out.println(user.getEmail());
//                System.out.println(user.getFirst_name());
//                System.out.println(user.getLast_Name());
//                System.out.println(user.getUserTypeID());

                session.save(user);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                session.close();
                map.put("message","Account Has Been Successfully Created.");
                return new ModelAndView(map , "login_user.ftl");
            }

        }
        else
            map.put("error","Email Address Already Exist.");
        return new ModelAndView(map , "signup_user.ftl");
    }

    private void addAuthenticatedUser(Request request, User u) {
        request.session().attribute(USER_SESSION_ID, u);

    }

    private void removeAuthenticatedUser(Request request) {
        request.session().removeAttribute(USER_SESSION_ID);

    }

    private User getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
    }
}
