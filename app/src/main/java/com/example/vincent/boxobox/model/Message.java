package com.example.vincent.boxobox.model;

import java.util.Date;

/**
 * Created by Vincent on 05/07/2017.
 */

public class Message {

    String _id;
    String username;
    String roomId;
    Date date;
    String message;

    public Message(String _id, String username, String roomId, Date date, String message) {
        this._id = _id;
        this.username = username;
        this.roomId = roomId;
        this.date = date;
        this.message = message;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
