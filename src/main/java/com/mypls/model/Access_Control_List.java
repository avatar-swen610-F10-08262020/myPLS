package com.mypls.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table
public class Access_Control_List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operation;
    private String user_type_permission;
    private String acl_permission;
    private String menu_name;
    private Integer user_type_id;

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUserTypePermission() {
        return user_type_permission;
    }

    public void setUserTypePermission(String user_type_permission) {
        this.user_type_permission = user_type_permission;
    }


    public String getACLPermission() {
        return acl_permission;
    }

    public void setACLPermission(String acl_permission) {
        this.acl_permission = acl_permission;
    }


    public String getMenuName() {
        return menu_name;
    }

    public void getMenuName(String menu_name) {
        this.menu_name = menu_name;
    }

    public Integer getUserTypeID() {
        return user_type_id;
    }

    public void setUserTypeID(Integer user_type_id) {
        this.user_type_id = user_type_id;
    }

    @Override
    public String toString() {
        return "ACL{" + "operation='" + operation + '\'' + ", user_type_permission='" + user_type_permission + '\'' + ", acl_permission='" + acl_permission + '\''
                + ", menu_name='" + menu_name + '\'' + ", user_type_id='" + user_type_id + '\'' + '}';
    }



}
