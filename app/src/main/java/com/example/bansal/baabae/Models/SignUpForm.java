package com.example.bansal.baabae.Models;

import java.io.Serializable;

public class SignUpForm implements Serializable {
    private  String Name;
    private  String Number;
    private  String CollegeName;
    private  String CourseName;
    private  String EmailId;
    private  String Password;

    public void setName(String name) {
       Name=name;
    }
 public String getName() {
        return Name;
 }


    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String collegeName) {
        CollegeName = collegeName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
