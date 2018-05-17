package com.example.nene.movie20.models;

/**
 * Created by sam- on 2018/5/9.
 */

public class User {
    private int id;
    private String mobile;
    private String row_id;
    private User_profile user_profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public User_profile getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(User_profile user_profile) {
        this.user_profile = user_profile;
    }
}
