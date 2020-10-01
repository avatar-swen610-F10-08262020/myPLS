package com.mypls.model;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Integer user_type_id;
    @ColumnDefault("0")
    private Integer status;

    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_Name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_Name() {
        return last_name;
    }

    public void setLast_Name(String last_name) {
        this.last_name = last_name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserTypeID() {
        return user_type_id;
    }

    public void setUserTypeID(Integer user_type_id) {
        this.user_type_id = user_type_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", first_name='" + first_name + '\''
                + ", email='" + email + '\'' + '}';
    }

}
