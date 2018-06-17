package com.teamapp.teamapp.community.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.teamapp.teamapp.event.model.EventData;

import java.io.File;
import java.io.Serializable;


/**
 * Created by karim on 5/12/18.
 */

public class Community implements Serializable{
    @SerializedName("Community_id")
    @Expose
    int Community_id;
    @SerializedName("Community_name")
    @Expose
    String Community_name;
    @SerializedName("Community_description")
    @Expose
    String Community_description;
    @SerializedName("Community_picture")
    @Expose
    String Community_picture;
    @SerializedName("Users_User_id")
    @Expose
    int Users_User_id;


    public Community() {
    }

    public int getCommunity_id() {
        return Community_id;
    }

    public void setCommunity_id(int community_id) {
        Community_id = community_id;
    }

    public String getCommunity_name() {
        return Community_name;
    }

    public void setCommunity_name(String community_name) {
        Community_name = community_name;
    }

    public String getCommunity_description() {
        return Community_description;
    }

    public void setCommunity_description(String community_description) {
        Community_description = community_description;
    }

    public String getCommunity_picture() {
        return Community_picture;
    }

    public void setCommunity_picture(String community_picture) {
        Community_picture = community_picture;
    }

    public int getUsers_User_id() {
        return Users_User_id;
    }

    public void setUsers_User_id(int users_User_id) {
        Users_User_id = users_User_id;
    }

    public Community(String community_name, String community_description, int users_User_id) {
        Community_name = community_name;
        Community_description = community_description;
        Users_User_id = users_User_id;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Community) {
            Community community = (Community) obj;
            return this.getCommunity_name().equals(community.getCommunity_name());
        }
        return super.equals(obj);
    }
}