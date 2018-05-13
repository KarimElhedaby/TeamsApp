package com.teamapp.teamapp.event.model;

/**
 * Created by karim on 2/24/18.
 */

public class Comment_FeedbackData {

    private String data ;

    public Comment_FeedbackData(String data) {
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
        return "Comment_FeedbackData{" +
                "data='" + data + '\'' +
                '}';
    }
}
