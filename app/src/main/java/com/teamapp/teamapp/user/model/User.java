package com.teamapp.teamapp.user.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by karim on 3/12/18.
 */

public class User implements Serializable {
    @SerializedName("User_id")
    @Expose
    private int user_id;
    @SerializedName("E_mail")
    @Expose
    private String email;
    @SerializedName("User_name")
    @Expose
    private String name;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Religious")
    @Expose
    private String religious;
    @SerializedName("Education")
    @Expose
    private String education;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("Work")
    @Expose
    private String work;
    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("Picture")
    @Expose
    private String Picture;


    public User(int user_id, String email, String name, String password, String gender) {
        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public User(String email, String name, String password, String gender) {
//        this.user_id = user_id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getReligious() {
        return religious;
    }

    public void setReligious(String religious) {
        this.religious = religious;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", religious='" + religious + '\'' +
                ", education='" + education + '\'' +
                ", skills='" + skills + '\'' +
                ", work='" + work + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", Picture='" + Picture + '\'' +
                '}';
    }
}
