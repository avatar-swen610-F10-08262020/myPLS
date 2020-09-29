package com.mypls.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import com.mypls.model.User;
import com.mypls.util.*;
import org.hibernate.*;

/**
 * The Web Controller for the Home page.
 *
 * @author <a href='mailto:aa7510@rit.edu'>Akhter Al Amin</a>
 */
public class HomeController implements TemplateViewRoute {
  private static final String USER_SESSION_ID = "user";

  @Override
  public ModelAndView handle(Request request, Response response) {
//
//    session.beginTransaction();
//
//    Department department = new Department("java");
//    session.save(department);
//
//    session.save(new Employee("Jakab Gipsz",department));
//    session.save(new Employee("Captain Nemo",department));
//
//    session.getTransaction().commit();
//    Session session = HibernateUtil.getSessionFactory().openSession();
//    Query q = session.createQuery("From User ");
//
//    List<User> resultList = q.list();
//    System.out.println("num of user:" + resultList.size());
//    String name="";
//    for (int i=0;i<resultList.size();i++) {
//      User user = resultList.get(i);
//      System.out.println("next user: " + user.getFirst_name());
//      name = user.getFirst_name();
//    }
    User currentUser = getAuthenticatedUser(request);

    Map<String, Object> vm = new HashMap<>();
    vm.put("Username", "Welcome! "+ currentUser.getFirst_name());
    return new ModelAndView(vm , "home.ftl");
  }


  public TemplateViewRoute handle_data() {
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome Farhan!");
    return (request, response) -> new ModelAndView(vm , "profile.ftl");
  }

  private User getAuthenticatedUser(Request request) {
    return request.session().attribute(USER_SESSION_ID);
  }
}