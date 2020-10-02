package com.mypls;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.mypls.model.User;
import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

import com.mypls.ui.WebServer;
import java.sql.*;
import static spark.Spark.port;


/**
 * The entry point for the WebCheckers web application.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public final class Application {
  private static final Logger LOG = Logger.getLogger(Application.class.getName());
  // Application Launch method
  //

  /**
   * Entry point for the WebCheckers web application.
   *
   * <p>
   * It wires the application components together.  This is an example
   * of <a href='https://en.wikipedia.org/wiki/Dependency_injection'>Dependency Injection</a>
   * </p>
   *
   * @param args
   *    Command line arguments; none expected.
   */
  public static void main(String[] args) {

    // The application uses FreeMarker templates to generate the HTML
    // responses sent back to the client. This will be the engine processing
    // the templates and associated data.
    final TemplateEngine templateEngine = new FreeMarkerEngine();

    // inject the game center and freemarker engine into web server
    final WebServer webServer = new WebServer(templateEngine);

    port(8080);

    // inject web server into application
    final Application app = new Application(webServer);

//    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "adminroot")) {
//      // create a Statement
//      try (Statement stmt = conn.createStatement()) {
//        //execute query
//        try (ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'")) {
//          //position result to first
//          rs.first();
//          System.out.println(rs.getString(1)); //result is "Hello World!"
//        }
//      }
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }

    // start the application up


    app.initialize();
  }

  //
  // Attributes
  //

  private final WebServer webServer;

  //
  // Constructor
  //

  private Application(final WebServer webServer) {
    this.webServer = webServer;
  }

  //
  // Private methods
  //

  private void initialize() {
    LOG.fine("WebCheckers is initializing.");

    // configure Spark and startup the Jetty web server
    webServer.initialize();



//    EMAIL_TOKEN = appProps.getProperty("mypls.email.token");
//    System.out.println("my pls Email Token: "+EMAIL_TOKEN);

    // other applications might have additional services to configure

    LOG.fine("WebCheckers initialization complete.");
  }

}