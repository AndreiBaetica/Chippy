package com.example.janieamyot.chippy;
import java.util.*;

public class Booking {
    private Calendar startTime;
    private Calendar endTime;
    private Rating rating;
    private int bookingId;

    public Booking (Calendar startTime, Calendar endTime, int bookingId){
        this.endTime=endTime;
        this.startTime=startTime;
        this.bookingId = bookingId;
    }
    public void setRating(Rating rating){
        this.rating=rating;
    }
    public void setStartTime(int day, int hour, int minute){
        startTime = Calendar.getInstance();
        startTime.set(startTime.getTime().getYear(), startTime.getTime().getMonth(), day, hour, minute);
    }
    public void setEndTime(int day, int hour, int minute){
        startTime = Calendar.getInstance();
        startTime.set(startTime.getTime().getYear(), startTime.getTime().getMonth(), day, hour, minute);
    }
    public Calendar getStartTime(){
        return startTime;
    }
    public Calendar getEndTime(){
        return endTime;
    }
    public Rating getRating(){
        return rating;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getBookingId() {
        return bookingId;
    }
}
