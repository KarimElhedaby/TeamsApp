package com.teamapp.teamapp.event.model;

import java.io.Serializable;

/**
 * Created by karim on 3/1/18.
 */

public class EventData  implements Serializable{
    private int even_month;
    private int event_day;
    private String event_title;
    private String event_description;
    private String event_community;
    private String event_location;


    public EventData() {
    }

    public EventData(int even_month, int event_day, String event_title, String event_description, String event_community, String event_location) {
        this.even_month = even_month;
        this.event_day = event_day;
        this.event_title = event_title;
        this.event_description = event_description;
        this.event_community = event_community;
        this.event_location = event_location;
    }

    public EventData(int even_month, int event_day, String event_title, String event_description) {
        this.even_month = even_month;
        this.event_day = event_day;
        this.event_title = event_title;
        this.event_description = event_description;
    }

    public int getEven_month() {
        return even_month;
    }

    public void setEven_month(int even_month) {
        this.even_month = even_month;
    }

    public int getEvent_day() {
        return event_day;
    }

    public void setEvent_day(int event_day) {
        this.event_day = event_day;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_community() {
        return event_community;
    }

    public void setEvent_community(String event_community) {
        this.event_community = event_community;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "even_month=" + even_month +
                ", event_day=" + event_day +
                ", event_title='" + event_title + '\'' +
                ", event_description='" + event_description + '\'' +
                ", event_community='" + event_community + '\'' +
                ", event_location='" + event_location + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EventData) {
            EventData eventData = (EventData) obj;
            return this.getEvent_title().equals(eventData.getEvent_title());
        }
        return super.equals(obj);
    }
}
