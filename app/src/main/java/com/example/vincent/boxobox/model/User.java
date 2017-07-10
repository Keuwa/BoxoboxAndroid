package com.example.vincent.boxobox.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vincent on 05/07/2017.
 */

public class User implements Parcelable {
    private String _id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String gender;
    private String password;
    private boolean admin;

    public User(){
    }

    public User(String id,String email, String username, String password, String gender, String lastname, String firstname,boolean admin) {
        this._id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.lastname = lastname;
        this.firstname = firstname;
        this.admin = admin;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    protected User(Parcel in) {
        _id = in.readString();
        email = in.readString();
        username = in.readString();
        firstname = in.readString();
        lastname = in.readString();
        gender = in.readString();
        password = in.readString();
        admin = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(gender);
        dest.writeString(password);
        dest.writeByte((byte) (admin ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
