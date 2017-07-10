package com.example.vincent.boxobox.model;

import java.util.Date;

/**
 * Created by Vincent on 10/07/2017.
 */

public class Answer {
    private String answer;
    private Question question;
    private Date date;
    private User user;


    public Answer(String answer, Question question, User user, Date date) {
        this.answer = answer;
        this.question = question;
        this.user = user;
        this.date = date;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
