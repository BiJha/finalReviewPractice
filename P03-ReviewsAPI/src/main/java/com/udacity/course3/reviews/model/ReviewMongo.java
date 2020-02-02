package com.udacity.course3.reviews.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class ReviewMongo {

    @Id
    private int id;
    private int rating;
    private String review_content;
    private String review_date;
    private String review_user_name;

    private List<CommentMongo> comments = new ArrayList<CommentMongo>();

    public ReviewMongo(int id, int rating, String review_content, String review_date, String review_user_name) {
        this.id = id;
        this.rating = rating;
        this.review_content = review_content;
        this.review_date = review_date;
        this.review_user_name = review_user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public String getReview_user_name() {
        return review_user_name;
    }

    public void setReview_user_name(String review_user_name) {
        this.review_user_name = review_user_name;
    }

    public List<CommentMongo> getComments() {
        return comments;
    }

    public void setComments(List<CommentMongo> comments) {
        this.comments = comments;
    }
}
