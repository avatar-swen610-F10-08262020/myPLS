package com.mypls.model;

public class UserDummy {
//    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private String newPassword;
    private Integer userTypeID;


    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getrepeatPassword() {
        return repeatPassword;
    }

    public void setrepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getuserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(Integer userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
