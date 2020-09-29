package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.*;
import java.util.List;

public class UserService {
    public User authenticate_user(User user) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User ");

        List<User> resultList = q.list();
        for (User userData : resultList) {
            if(userData.getEmail().equals(user.getEmail()) && userData.getPassword().equals(user.getPassword()) && userData.getStatus()==1)
                return userData;
        }
        return null;
    }

    public boolean check_user(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From User ");

        List<User> resultList = q.list();
        for (User userData : resultList) {
            if(userData.getEmail().equals(user.getEmail()))
                return true;
        }
        return false;
    }
}
