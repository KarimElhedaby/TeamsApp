package com.teamapp.teamapp.profile.model;

/**
 * Created by karim on 2/19/18.
 */

public class ProfileData {
    private String data ;

    public ProfileData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProfileData{" +
                "data='" + data + '\'' +
                '}';
    }
}
