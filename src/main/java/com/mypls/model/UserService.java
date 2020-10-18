package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.*;
import java.util.List;

public class UserService {
    public User authenticateUser(User user) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("From User Where email = '" + user.getEmail() + "' And password = '" + user.getPassword() + "' And status = 1 ");
        List<User> resultList = q.list();
        System.out.println(resultList.size());
        for (User userData : resultList) {
            return userData;
//            if(userData.getEmail().equals(user.getEmail()) && userData.getPassword().equals(user.getPassword()) && userData.getStatus()==1)
//            {
//                session.close();
//                return userData;
//
//            }

        }
        return null;
    }

    public boolean checkUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User ");
        session.close();

        List<User> resultList = q.list();
        for (User userData : resultList) {
            if(userData.getEmail().equals(user.getEmail()))
            {
                session.close();
                return true;
            }

        }
        return false;
    }

    public List<User> getAllUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User");
        List<User> resultList = null;
        try{
            resultList = q.list();
            System.out.println(resultList);
//            for (Course course : resultList) {
//                System.out.println(course.getCourse_name());
//            }

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        session.close();
        return resultList;

    }

    public User updateUser(User user) {
        return new User();
    }
}
