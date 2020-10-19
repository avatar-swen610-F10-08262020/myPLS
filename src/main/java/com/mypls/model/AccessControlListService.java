package com.mypls.model;

import com.mypls.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class AccessControlListService {

    public List<Access_Control_List> accessControlList(User user) {
        System.out.println(user.getUser_type_id());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Access_Control_List");
//        query.setParameter("tid", user.getUserTypeID().toString());

        List<Access_Control_List> resultList = query.list();
        System.out.println("list size"+resultList.size());

        for (Access_Control_List userData : resultList) {
            System.out.println(userData.getMenuName());
        }
        session.close();
        return resultList;
    }
}
