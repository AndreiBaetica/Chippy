package com.example.janieamyot.chippy;

public class Rating {
    private int rating;
    private String comment;

    public Rating (int rating){
        this.rating =rating;
    }
    public Rating (int rating, String comment){
        this.rating=rating;
        this.comment=comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getRating(){
        return rating;
    }
    public String getComment(){
        return comment;
    }
}
