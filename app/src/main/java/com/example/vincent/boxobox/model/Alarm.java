package com.example.vincent.boxobox.model;

import java.util.Date;

/**
 * Created by Vincent on 10/07/2017.
 */

public class Alarm {
    private Date startDate;
    private Date endDate;
    private String sensor;
    private User user;

    public Alarm(Date startDate, Date endDate, String sensor, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sensor = sensor;
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
