package com.udacity.course3.reviews.model;

public class CommentMongo {

    private int id;
    private String comment_content;
    private String comment_date;
    private String comment_user_name;


    public CommentMongo(int id, String comment_content, String comment_date, String comment_user_name) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_user_name() {
        return comment_user_name;
    }

    public void setComment_user_name(String comment_user_name) {
        this.comment_user_name = comment_user_name;
    }
}
