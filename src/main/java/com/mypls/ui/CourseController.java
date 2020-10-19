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

public class CourseController {
    CourseService cService = new CourseService();
    UserService userService = new UserService();
    Professor_CourseService professorCourseService = new Professor_CourseService();
    Course_DependencyService cdService = new Course_DependencyService();
    Session session = null;
    SessionUtil sessionUtil = new SessionUtil();
    public ModelAndView home(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            List<Course> courses = cService.getAllCourses();
            try {
                map.put("courses", courses);
                System.out.println(map);
            } catch (NullPointerException ex) {
                System.out.println(ex.toString());
            }

            map.put("msg_type", "none");
            map.put("message", "none");
            return new ModelAndView(map , "course/home.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }

    }

    public ModelAndView create(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            List<User> userList =  userService.getProfessors();


            map.put("users", userList);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "course/create.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }
    }

    public ModelAndView registerClass(Request req) {
        Map<String, Object> map = new HashMap<>();
        User sessionUser = sessionUtil.getAuthenticatedUser(req);

        String courseName = req.queryParams("course_name");
        String description = req.queryParams("description");
        String course_code = req.queryParams("course_code");
        String class_size = req.queryParams("class_size");
        String start_date = req.queryParams("start_date");
        String end_date = req.queryParams("end_date");
        Long instructor = Long.parseLong(req.queryParams("instructor"));

        System.out.println(courseName + description + course_code + class_size + start_date + end_date + courseName.isEmpty() + course_code.isEmpty());

        System.out.println(cService.alreadyAvailable(courseName, course_code));
        if (cService.alreadyAvailable(courseName, course_code)) {
            map.put("msg_type", "error");
            map.put("msg", "Course has already been created");
            map.put("UserType", sessionUser.getUser_type_id());
            map.put("Username", sessionUser.getFirst_name());
            System.out.println("Not Available");
            return new ModelAndView(map , "course/create.ftl");
        } else {
            try{
                    session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    Course newCourse = new Course();
                    newCourse.setCourse_name(courseName);
                    newCourse.setClass_size(class_size);
                    newCourse.setDescription(description);
                    newCourse.setCourse_code(course_code);
                    newCourse.setStart_date(start_date);
                    newCourse.setEnd_date(end_date);
                    newCourse.setStatus(1);

                    session.save(newCourse);
                    System.out.println("Course ID:"+newCourse.getId());
                    Long course_id = newCourse.getId();

                    session.getTransaction().commit();
                    ///////////Mapping Start//////////////////


                    Professor_Course professor_course = new Professor_Course();
                    professor_course.setCourse_id(course_id);
                    professor_course.setUser_id(instructor);
                    professor_course.setStatus(1);
                    session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.save(professor_course);
                    session.flush(); // I forgot this from the previous post
                    Long ID = professor_course.getId();
                    System.out.println("professor course ID:"+ID.toString());
                    session.getTransaction().commit();
                    ///////////Mapping End//////////////////
                    map.put("UserType", sessionUser.getUser_type_id());
                    map.put("Username", sessionUser.getFirst_name());
                    map.put("msg_type", "notification");
                    map.put("msg", "Course is created");
                    return home(req);

            } catch (HibernateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                map.put("UserType", sessionUser.getUser_type_id());
                map.put("Username", sessionUser.getFirst_name());
                map.put("msg_type", "error");
                map.put("msg", "System Error: Course can not be created.");
                return home(req);
            }

        }
    }

    public ModelAndView singleview(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
            Course currCourse = cService.getIndividualCourse(ID);
            Professor_Course professor_course = professorCourseService.getCourseProfessor(ID);
            User userProfessor = userService.getUserbyId(professor_course.getUser_id());

            map.put("professor",userProfessor);
            map.put("course", currCourse);
            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "course/single.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }
    }

    public ModelAndView edit(Request req) {
        Map<String, Object> map = new HashMap<>();
        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
            System.out.println(ID);
            Course course = cService.getIndividualCourse(ID);
            List<User> users = userService.getProfessors();
            Professor_Course current_professor = professorCourseService.getCourseProfessor(ID);
            System.out.println(current_professor);

            map.put("current_professor", current_professor);
            map.put("users",users);
            map.put("course", course);
            map.put("msg_type", "none");
            map.put("msg", "none");
            return new ModelAndView(map , "course/edit.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }
    }

    public ModelAndView updateClass(Request req) {
        Map<String, Object> map = new HashMap<>();
        Long courseId = Long.valueOf(req.queryParams("course_id"));
        String courseName = req.queryParams("course_name");
        String description = req.queryParams("description");
        String course_code = req.queryParams("course_code");
        String class_size = req.queryParams("class_size");
        String start_date = req.queryParams("start_date");
        String end_date = req.queryParams("end_date");
        Long instructor = Long.parseLong(req.queryParams("instructor"));

        System.out.println(courseId + "\n" + courseName + "\n" + description + "\n" + course_code + "\n" + class_size + "\n" + start_date + "\n" + end_date + "\n" + courseName.isEmpty() + "\n" + course_code.isEmpty());

        Course currCourse = cService.getIndividualCourse(courseId);
        System.out.println(currCourse);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currCourse.setCourse_name(courseName);
        currCourse.setClass_size(class_size);
        currCourse.setDescription(description);
        currCourse.setCourse_code(course_code);
        currCourse.setStart_date(start_date);
        currCourse.setEnd_date(end_date);
        session.update(currCourse);
        session.getTransaction().commit();

        Professor_Course currProfCourse = professorCourseService.getCourseProfessor(courseId);

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        currProfCourse.setUser_id(instructor);
        currProfCourse.setCourse_id(courseId);
        currProfCourse.setStatus(1);
        session.update(currProfCourse);
        session.getTransaction().commit();


        map.put("msg_type", "notification");
        map.put("msg", "Course is updated");
//        return new ModelAndView(map , "course/create.ftl");

        map.put("course", currCourse);
        return home(req);

    }



    public ModelAndView prerequisite(Request req){
        Map<String, Object> map = new HashMap<>();

        try {
            User user = sessionUtil.getAuthenticatedUser(req);
            map.put("UserType", user.getUser_type_id());
            map.put("Username", user.getFirst_name());
            Long ID = Long.parseLong(req.params(":id"));
            List<Course> courseList = cService.getAllCourses();
            Course individualCourse = new Course();
            List<Course_Dependency> course_dependencyList = cdService.getCourseDependency(ID);
            List<Course> assignedCourse = new ArrayList<>();
            List<Course> otherCourse = new ArrayList<>();

            if (course_dependencyList.size() > 0) {
                for (Course course : courseList) {
                    if (course.getId().equals(ID)) {
                        individualCourse = course;
                        continue;
                    }
                    boolean flag = false;
                    for (Course_Dependency course_dependency : course_dependencyList) {
                        if (course.getId().equals(course_dependency.getParent_id())) {
                            assignedCourse.add(course);
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        otherCourse.add(course);
                    }
                    flag = false;

                }
                map.put("dependentCourse",individualCourse);
                map.put("courses", otherCourse);
                map.put("parentCourses", assignedCourse);
            } else {
                for (Course course : courseList) {
                    if (course.getId().equals(ID)) {
                        individualCourse = course;
                        continue;
                    }
                    otherCourse.add(course);
                }
                map.put("dependentCourse",individualCourse);
                map.put("courses", otherCourse);
                map.put("parentCourses", assignedCourse);
            }
            return new ModelAndView(map, "course/prerequisite.ftl");
        }
        catch (NullPointerException ex)
        {
            return new ModelAndView(map , "login.ftl");
        }

    }
    public ModelAndView  createPrerequisite(Request req){
        Long dependent_id = Long.parseLong(req.params(":id"));
        MultiMap<String> params = new MultiMap<String>();
        UrlEncoded.decodeTo(req.body(), params, "UTF-8");
        System.out.println(params.toString());
        ////////Add and Update///////////////////
        for (String parent_id : params.keySet())
        {
            System.out.println(dependent_id);
            System.out.println(parent_id);
            Course_Dependency course_dependency = cdService.getIndividualCourseDependency(Long.parseLong(parent_id),dependent_id);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            if(course_dependency!=null){
                course_dependency.setStatus(1);
                session.update(course_dependency);
            }
            else {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Course_Dependency newCourseDependency = new Course_Dependency();
                newCourseDependency.setDependent_id(dependent_id);
                newCourseDependency.setParent_id(Long.parseLong(parent_id));
                newCourseDependency.setStatus(1);

                session.save(newCourseDependency);
                System.out.println("Course ID:"+newCourseDependency.getId());

            }
            session.getTransaction().commit();

        }
        /////////////////////////////////////////
        List<Course_Dependency> course_dependencyList = cdService.getCourseDependency(dependent_id);
        for (Course_Dependency course_dependency: course_dependencyList){
            boolean flag = false;
            Long p_id = new Long(0);
            for( String parent_id : params.keySet()){
                p_id = Long.parseLong(parent_id);
                System.out.println(p_id.toString() + " " + course_dependency.getParent_id().toString());
                if(p_id.equals(course_dependency.getParent_id())){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                Course_Dependency course_dependency_delete = cdService.getIndividualCourseDependency(p_id,dependent_id);
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                course_dependency.setStatus(0);
                session.update(course_dependency);
                session.getTransaction().commit();
            }
            flag = false;

        }

        return prerequisite(req);
    }

    public ModelAndView delete(Request req) {
        Long ID = Long.parseLong(req.params(":id"));
        if (!cService.deleteIndividualCourse(ID)) {
            Map<String, Object> map = new HashMap<>();
            List<Course> courses = cService.getAllCourses();
            map.put("courses", courses);
            System.out.println(map);
            map.put("msg_type", "delerror");
            map.put("msg", "Couldn't delete the course");
            return new ModelAndView(map , "course/home.ftl");
        } else {
            Map<String, Object> map = new HashMap<>();
            List<Course> courses = cService.getAllCourses();
            map.put("courses", courses);
            System.out.println(map);
            map.put("msg_type", "delmsg");
            map.put("msg", "Successfully deleted a course");
            return new ModelAndView(map, "course/home.ftl");
        }
    }
}
