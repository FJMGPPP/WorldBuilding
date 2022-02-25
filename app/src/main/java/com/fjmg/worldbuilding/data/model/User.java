package com.fjmg.worldbuilding.data.model;

import java.io.Serializable;

public class User implements Serializable
{

    public static final String TAG ="User" ;
    public static final String PASSWORDTAG = "Password";
    public static final String RECORDTAG = "Record";
    public static final String UID = "UID";
    String uid;
    String email;
    String password;
    String matchPassword;
    String username;
    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }
    public User(String email, String password , String matchPassword,  String username)
    {
        this.email = email;
        this.password = password;
        this.matchPassword = matchPassword;
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

