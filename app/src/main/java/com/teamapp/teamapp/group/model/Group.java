package com.teamapp.teamapp.group.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.teamapp.teamapp.event.model.EventData;

import java.io.File;
import java.io.Serializable;

/**
 * Created by karim on 5/17/18.
 */

public class Group implements Serializable{
    @SerializedName("Group_id")
    @Expose
    int Group_id;
    @SerializedName("Group_name")
    @Expose
    String Group_name;
    @SerializedName("Group_description")
    @Expose
    String Group_description;
    @SerializedName("Super_group_id")
    @Expose
    String Super_group_id;
    @SerializedName("Group_picture")
    @Expose
    String Group_picture;
    @SerializedName("head_id")
    @Expose
    int head_id;
    @SerializedName("Community_Community_id1")
    @Expose
    int Community_Community_id1;


    public Group() {
    }

    public Group(String group_name, String group_description, String super_group_id, int head_id, int community_Community_id1) {
        Group_name = group_name;
        Group_description = group_description;
        Super_group_id = super_group_id;
        this.head_id = head_id;
        Community_Community_id1 = community_Community_id1;
    }

    public int getGroup_id() {
        return Group_id;
    }

    public void setGroup_id(int group_id) {
        Group_id = group_id;
    }

    public String getGroup_name() {
        return Group_name;
    }

    public void setGroup_name(String group_name) {
        Group_name = group_name;
    }

    public String getGroup_description() {
        return Group_description;
    }

    public void setGroup_description(String group_description) {
        Group_description = group_description;
    }

    public String getSuper_group_id() {
        return Super_group_id;
    }

    public void setSuper_group_id(String super_group_id) {
        Super_group_id = super_group_id;
    }

    public String getGroup_picture() {
        return Group_picture;
    }

        public void setGroup_picture(String group_picture) {
        Group_picture = group_picture;
    }

    public int getHead_id() {
        return head_id;
    }

    public void setHead_id(int head_id) {
        this.head_id = head_id;
    }

    public int getCommunity_Community_id1() {
        return Community_Community_id1;
    }

    public void setCommunity_Community_id1(int community_Community_id1) {
        Community_Community_id1 = community_Community_id1;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Group) {
            Group group = (Group) obj;
            return this.getGroup_name().equals(group.getGroup_name());
        }
        return super.equals(obj);
    }
}
