package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class LessonService {
    public List<Lesson> getLessonByCourse(Long course_id){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From Lesson Where course_id = '" + course_id.toString()+"' And status = 1 ORDER BY week_id ");
        List<Lesson> resultList = q.list();
        return resultList;

    }

    public Lesson getLessonByID(Long lesson_id){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From Lesson Where id = '" + lesson_id.toString()+"' And status = 1");
        List<Lesson> resultList = q.list();
        for(Lesson lesson : resultList) {
            return lesson;
        }
        return null;
    }

    public boolean deleteIndividualLesson(Long lesson_id) {
        Lesson currLesson = getLessonByID(lesson_id);
        System.out.println(currLesson);
        if (currLesson == null) {
            return false;
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            currLesson.setStatus(0);
            session.update(currLesson);
            session.flush();
            session.getTransaction().commit();
            return true;
        }
    }
}
