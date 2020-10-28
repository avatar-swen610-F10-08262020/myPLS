package com.mypls.ui;

import com.mypls.Application;
import com.mypls.model.Course;
import com.mypls.model.Lesson;
import com.mypls.util.HibernateUtil;
import spark.ModelAndView;
import spark.Request;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.*;

import static spark.Spark.staticFiles;

public class LessonController extends CourseController{
    public ModelAndView lesson_add(Request req) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        Long course_id = Long.parseLong(req.params(":id"));

        req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));

        Map<String, String[]> parameterMap = req.raw().getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        List<String> metaData = new ArrayList<String>();
        parameterSet.stream().forEach(each->{
            String join = String.join(",",parameterMap.get(each));
            System.out.println("this is the key: " + each);
            System.out.println("this is value: " + join);
            metaData.add(join);
        });
        System.out.println("file name value is: " + metaData.get(0));
        System.out.println("week id is: " + metaData.get(1));

        Part uploadFile = null;
        try{
            uploadFile = req.raw().getPart("lesson_content_path");
            System.out.println("content type:"+uploadFile.getContentType());
            System.out.println("file name:"+uploadFile.getSubmittedFileName().substring(uploadFile.getSubmittedFileName().lastIndexOf('.'), uploadFile.getSubmittedFileName().length()));

        }
        catch (IOException | ServletException e){
            e.printStackTrace();

        }
        try(InputStream inStream = uploadFile.getInputStream()){

            byte[] buffer = new byte[inStream.available()];
            inStream.read(buffer);
//            image/png, audio/aac, image/bmp, image/jpeg, audio/mpeg. audio/mp3, video/mpeg,
//            application/pdf, application/vnd.ms-powerpoint, application/vnd.openxmlformats-officedocument.presentationml.presentation
//            application/x-tar, application/java-archive, application/zip

            String extensions = uploadFile.getSubmittedFileName().substring(uploadFile.getSubmittedFileName().lastIndexOf('.'), uploadFile.getSubmittedFileName().length());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String filePath = metaData.get(0)+"_" + timestamp.getTime() + extensions;
            File targetFile = new File("src/main/resources/public/lesson/"+filePath);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Lesson lesson = new Lesson();
            lesson.setContent_type(uploadFile.getContentType());
            lesson.setCourse_id(course_id);
            lesson.setLesson_content_path(filePath);
            lesson.setWeek_id(Long.parseLong(metaData.get(1)));
            lesson.setStatus(1);

            session.save(lesson);
            session.getTransaction().commit();

        }
        return singleview(req);
    }
}
