package com.mypls.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.mypls.model.UserService;
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
  UserService service = new UserService();

  @Override
  public ModelAndView handle(Request request, Response response) {

    Map<String, Object> vm = new HashMap<>();
//    vm.put("Username", "Welcome! "+ currentUser.getFirst_name());
    return new ModelAndView(vm , "home.ftl");
  }

  public ModelAndView home(Request req) {
    User user = getAuthenticatedUser(req);
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

      }
      return new ModelAndView(map , "home.ftl");
    }
//        return (request, response) -> new ModelAndView(map , "login.ftl");
    return new ModelAndView(map , "login.ftl");

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