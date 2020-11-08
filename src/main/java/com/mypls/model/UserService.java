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
        }
        return null;
    }

    public boolean checkUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User Where email = '"+ user.getEmail()+"' ");

        if(q.list().size()>0)
            return true;
        return false;
    }

    public List<User> getAllUser(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User");
        List<User> resultList = null;
        try{
            resultList = q.list();
            System.out.println(resultList);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return resultList;

    }

    public List<User> getProfessors(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User Where user_type_id = 2");
        List<User> resultList = null;
        try{
            resultList = q.list();
            System.out.println(resultList);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return resultList;

    }

    public User getUserbyId(Long ID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User Where id = "+ID+" ");
        List<User> resultList = null;

        try{
            resultList = q.list();
            for(User user:resultList){
                return user;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return null;
    }

    public User updateUser(User user) {
        return new User();
    }
}
