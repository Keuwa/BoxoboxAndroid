package com.example.vincent.boxobox.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 10/07/2017.
 */

public class Question implements Parcelable {
    private String _id;
    private String question;
    private String answer_left;
    private String answer_right;
    private Date date;
    private User user;

    public Question(String question, User user, String answer_left, String answer_right, Date date,String _id ) {
        this.question = question;
        this.user = user;
        this.answer_left = answer_left;
        this.answer_right = answer_right;
        this.date = date;
        this._id = _id;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer_left() {
        return answer_left;
    }

    public void setAnswer_left(String answer_left) {
        this.answer_left = answer_left;
    }

    public String getAnswer_right() {
        return answer_right;
    }

    public void setAnswer_right(String answer_right) {
        this.answer_right = answer_right;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    protected Question(Parcel in) {
        _id = in.readString();
        question = in.readString();
        answer_left = in.readString();
        answer_right = in.readString();
        long tmpDate = in.readLong();
        date = tmpDate != -1 ? new Date(tmpDate) : null;
        user = (User) in.readValue(User.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(question);
        dest.writeString(answer_left);
        dest.writeString(answer_right);
        dest.writeLong(date != null ? date.getTime() : -1L);
        dest.writeValue(user);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}