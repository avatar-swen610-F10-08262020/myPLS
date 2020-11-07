package com.mypls.ui;

import static spark.Spark.*;

import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;
import org.slf4j.Logger;
/**
 * The server that initializes the set of HTTP request handlers.
 * This defines the <em>web application interface</em> for this
 * guessing game application.
 *
 * <p>
 * There are multiple ways in which you can have the client issue a
 * request and the application generate responses to requests. If your team is
 * not careful when designing your approach, you can quickly create a mess
 * where no one can remember how a particular request is issued or the response
 * gets generated. Aim for consistency in your approach for similar
 * activities or requests.
 * </p>
 *
 * <p>Design choices for how the client makes a request include:
 * <ul>
 *     <li>Request URL</li>
 *     <li>HTTP verb for request (GET, POST, PUT, DELETE and so on)</li>
 *     <li><em>Optional:</em> Inclusion of request parameters</li>
 * </ul>
 * </p>
 *
 * <p>Design choices for generating a response to a request include:
 * <ul>
 *     <li>View templates with conditional elements</li>
 *     <li>Use different view templates based on results of executing the client request</li>
 *     <li>Redirecting to a different application URL</li>
 * </ul>
 * </p>
 *
 * @author <a href='mailto:aa7510@rit.edu'>Akhter Al Amin</a>
 */
public class WebServer {

  //
  // Constants
  //

  /**
   * The URL pattern to request the Home page.
   */
  public static final String HOME_URL = "/";
  public static final String HOME_DASHBOARD_URL = "/home";

  public static final String PROFILE_URL = "/profile";
  public static final String UPDATE_PROFILE_URL = "/profile/update";
  public static final String UPDATE_PROFILE_GENERAL_URL = "/profile/update-general";
  public static final String UPDATE_PROFILE_PASSWORD_URL = "/profile/update-password";
  public static final String Login_URL = "/login";
  public static final String Login_User_URL = "/login/user";
  public static final String Signup_User_URL = "/signup/user";
  public static final String Logout_URL = "/logout";
  public static final String Activate_User_URL = "/activate/user/:id";
  public static final String FORGOT_PASSWORD_URL = "/forgot/password";
  public static final String FORGOT_PASSWORD_USER_URL = "/forgot/password/user";
  private static final Logger LOGGER = LoggerFactory.getLogger(WebServer.class);

  private static final String COURSE_URL = "/course";
  private static final String COURSE_ASSIGNED_URL = "/course/assigned";
  private static final String COURSE_CREATE_URL = "/course/create";
  private static final String COURSE_VIEW_URL = "/course/:id";
  private static final String COURSE_EDIT_URL = "/course/edit/:id";
  private static final String COURSE_DELETE_URL = "/course/delete/:id";
  private static final String COURSE_REGISTER_URL = "/course/register";
  private static final String COURSE_UPDATE_URL = "/course/update";
  private static final String COURSE_PREREQUISITE_URL = "/course/prerequisite/:id";
  private static final String COURSE_CREATE_PREREQUISITE_URL = "/course/prerequisite/create/:id";

  private static final String LESSON_CREATE_URL = "/lesson/add/:id";

  private static final String QUIZ_CREATE_URL = "/quiz/add/:id";
  private static final String QUIZ_DETAILS_URL ="/quiz/details/:id";
  private static final String QUIZ_EDIT_URL ="/quiz/edit/:id";
  private static final String QUIZ_UPDATE_URL ="/quiz/update/:id";
  private static final String QUIZ_QUESTION_UPDATE_URL ="/quiz/question/update/:id/:question_id";
  private static final String QUIZ_OPTION_UPDATE_URL ="/quiz/option/update/:id/:question_id";
  private static final String QUIZ_QUESTION_DELETE_URL ="/quiz/question/delete/:id/:question_id";
  private static final String QUIZ_QUESTION_ADD_URL ="/quiz/question/add/:id";




  private static final String USER_VIEW_URL = "/user/:id";
  private static final String USER_EDIT_URL = "/user/edit/:id";
  private static final String USER_UPDATE_URL = "/user/update/:id";

  private static final String OFFERED_URL = "/offeredcourse";
  private static final String OFFERED_VIEW_URL = "/offeredcourse/:id";
  private static final String ENROLL_URL = "/offeredcourse/enroll/:id";




  //
  // Attributes
  //

  private final TemplateEngine templateEngine;



  //
  // Constructor
  //

  /**
   * The constructor for the Web Server.
   *
   * @param templateEngine
   *    The default {@link TemplateEngine} to render views.
   */
  public WebServer(
      final TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  //
  // Public methods
  //

  /**
   * Initialize all of the HTTP routes that make up this web application.
   *
   * <p>
   * Initialization of the web server includes defining the location for static
   * files, and defining all routes for processing client requests. The method
   * returns after the web server finishes its initialization.
   * </p>
   */
  public void initialize() {
    // Configuration to serve static files
    staticFileLocation("/public");





    //// Setting any route (or filter) in Spark triggers initialization of the
    //// embedded Jetty web server.

    //// A route is set for a request verb by specifying the path for the
    //// request, and the function callback (request, response) -> {} to
    //// process the request. The order that the routes are defined is
    //// important. The first route (request-path combination) that matches
    //// is the one which is invoked. Additional documentation is at
    //// http://sparkjava.com/documentation.html and in Spark tutorials.

    //// Each route (processing function) will check if the request is valid
    //// from the client that made the request. If it is valid, the route
    //// will extract the relevant data from the request and pass it to the
    //// application object delegated with executing the request. When the
    //// delegate completes execution of the request, the route will create
    //// the parameter map that the response template needs. The data will
    //// either be in the value the delegate returns to the route after
    //// executing the request, or the route will query other application
    //// objects for the data needed.

    //// FreeMarker defines the HTML response using templates. Additional
    //// documentation is at
    //// http://freemarker.org/docs/dgui_quickstart_template.html.
    //// The Spark FreeMarkerEngine lets you pass variable values to the
    //// template via a map. Additional information is in online
    //// tutorials such as
    //// http://benjamindparrish.azurewebsites.net/adding-freemarker-to-java-spark/.

    //// These route definitions are examples. You will define the routes
    //// that are appropriate for the HTTP client interface that you define.
    //// Create separate Route classes to handle each route; this keeps your
    //// code clean; using small classes.

    // Shows the Checkers game Home page.
    get(HOME_URL, (req,res) -> new LoginController().login(req), templateEngine);
    get(HOME_DASHBOARD_URL, (req,res) -> new HomeController().home(req), templateEngine);


    get(PROFILE_URL, (req, res) -> new ProfileController().home(req), templateEngine);
    get(Login_URL, (req,res) -> new LoginController().login(req), templateEngine);
    get(Login_User_URL, (req,res) -> new LoginController().login_user(req), templateEngine);
    get(Signup_User_URL, (req,res) -> new LoginController().signup_user(req), templateEngine);
    get(Activate_User_URL, (req,res) -> new LoginController().activate_user(req), templateEngine);
    get(Logout_URL, (req,res) -> new LoginController().logout_user(req), templateEngine);
    get(FORGOT_PASSWORD_URL, (req,res) -> new LoginController().forgot_password(req), templateEngine);
    get(USER_VIEW_URL, (req, res) -> new UserController().user_details(req), templateEngine);
    get(USER_EDIT_URL, (req, res) -> new UserController().user_edit(req), templateEngine);


    get(COURSE_URL, (req, res) -> new CourseController().home(req), templateEngine);
    get(COURSE_CREATE_URL, (req, res) -> new CourseController().create(req), templateEngine);
    get(COURSE_VIEW_URL, (req, res) -> new CourseController().singleview(req), templateEngine);
    get(COURSE_DELETE_URL, (req, res) -> new CourseController().delete(req), templateEngine);
    get(COURSE_EDIT_URL, (req, res) -> new CourseController().edit(req), templateEngine);
    get(COURSE_PREREQUISITE_URL, (req, res) -> new CourseController().prerequisite(req), templateEngine);
    get(COURSE_ASSIGNED_URL, (req, res) -> new CourseController().assignedCourseList(req), templateEngine);
    get(QUIZ_DETAILS_URL, (req, res) -> new QuizController().quiz_details(req), templateEngine);
    get(QUIZ_EDIT_URL, (req, res) -> new QuizController().quiz_edit(req), templateEngine);

    get(OFFERED_URL, (req, res) -> new EnrollController().home(req), templateEngine);
    get(OFFERED_VIEW_URL, (req, res) -> new EnrollController().show(req), templateEngine);
    get(ENROLL_URL, (req, res) -> new EnrollController().enroll(req), templateEngine);

    post(Signup_User_URL, (req,res) -> new LoginController().register_user(req,res), templateEngine);
    post(Login_URL, (req,res) -> new LoginController().authenticateUser(req), templateEngine);
    post(Logout_URL, (req,res) -> new LoginController().logout_user(req), templateEngine);
    post(FORGOT_PASSWORD_USER_URL, (req,res) -> new LoginController().forgot_password_user(req), templateEngine);
    post(UPDATE_PROFILE_PASSWORD_URL, (req, res) -> new ProfileController().updatePassword(req), templateEngine);
    post(UPDATE_PROFILE_GENERAL_URL, (req, res) -> new ProfileController().updateGeneral(req), templateEngine);
    post(COURSE_REGISTER_URL, (req, res) -> new CourseController().registerClass(req), templateEngine);
    post(COURSE_UPDATE_URL, (req, res) -> new CourseController().updateClass(req), templateEngine);
    post(COURSE_CREATE_PREREQUISITE_URL, (req, res) -> new CourseController().createPrerequisite(req), templateEngine);
    post(USER_UPDATE_URL, (req, res) -> new UserController().user_update(req), templateEngine);
    post(LESSON_CREATE_URL, (req, res) -> new LessonController().lesson_add(req), templateEngine);
    post(QUIZ_CREATE_URL, (req, res) -> new QuizController().quiz_add(req), templateEngine);
    post(QUIZ_UPDATE_URL, (req, res) -> new QuizController().quiz_update(req), templateEngine);
    post(QUIZ_QUESTION_UPDATE_URL, (req, res) -> new QuizController().quiz_question_update(req), templateEngine);
    post(QUIZ_OPTION_UPDATE_URL, (req, res) -> new QuizController().quiz_option_update(req), templateEngine);
    post(QUIZ_QUESTION_DELETE_URL, (req, res) -> new QuizController().quiz_question_delete(req), templateEngine);
    post(QUIZ_QUESTION_ADD_URL, (req, res) -> new QuizController().quiz_question_add(req), templateEngine);





  }

}