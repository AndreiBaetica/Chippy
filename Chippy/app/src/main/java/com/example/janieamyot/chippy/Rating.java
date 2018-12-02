package com.example.janieamyot.chippy;

public class Rating {
    private int rating;
    private String comment;
    private Booking booking;
    private int ratingID;

    public Rating (int rating, int ratingID){
        this.rating =rating;
        this.ratingID = ratingID;
    }
    public Rating (int rating, int ratingID, String comment){
        this.rating=rating;
        this.comment=comment;
        this.ratingID=ratingID;
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

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }
}
