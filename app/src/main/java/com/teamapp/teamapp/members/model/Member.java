package com.teamapp.teamapp.members.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by karim on 5/17/18.
 */

public class Member {

    @SerializedName("Member_id")
    @Expose
    int Member_id;
    @SerializedName("Groups_Group_id")
    @Expose
    int Groups_Group_id;
    @SerializedName("Groups_Community_Community_id1")
    @Expose
    int Groups_Community_Community_id1;
    @SerializedName("user_id")
    @Expose
    int user_id;

    public Member() {
    }

    public Member(int groups_Group_id, int groups_Community_Community_id1, int user_id) {
        Groups_Group_id = groups_Group_id;
        Groups_Community_Community_id1 = groups_Community_Community_id1;
        this.user_id = user_id;
    }

    public int getMember_id() {
        return Member_id;
    }

    public void setMember_id(int member_id) {
        Member_id = member_id;
    }

    public int getGroups_Group_id() {
        return Groups_Group_id;
    }

    public void setGroups_Group_id(int groups_Group_id) {
        Groups_Group_id = groups_Group_id;
    }

    public int getGroups_Community_Community_id1() {
        return Groups_Community_Community_id1;
    }

    public void setGroups_Community_Community_id1(int groups_Community_Community_id1) {
        Groups_Community_Community_id1 = groups_Community_Community_id1;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "Member_id=" + Member_id +
                ", Groups_Group_id=" + Groups_Group_id +
                ", Groups_Community_Community_id1=" + Groups_Community_Community_id1 +
                ", user_id=" + user_id +
                '}';
    }
}
