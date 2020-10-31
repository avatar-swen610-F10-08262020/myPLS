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
}
