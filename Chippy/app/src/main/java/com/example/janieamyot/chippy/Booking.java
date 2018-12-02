package com.example.janieamyot.chippy;
import java.util.*;

public class Booking {
    private Calendar startTime;
    private Calendar endTime;
    private Rating rating;
    private int bookingId;
    private Service service;
    private ServiceProvider serviceProvider;
    private HomeOwner homeOwner;

    public Booking (Service service, ServiceProvider serviceProvider, HomeOwner homeOwner, Calendar startTime, Calendar endTime, int bookingId){
        this.endTime=endTime;
        this.startTime=startTime;
        this.bookingId = bookingId;
        this.homeOwner = homeOwner;
        this.service = service;
        this.serviceProvider = serviceProvider;
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

    public HomeOwner getHomeOwner() {
        return homeOwner;
    }

    public Service getService() {
        return service;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setHomeOwner(HomeOwner homeOwner) {
        this.homeOwner = homeOwner;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }
}
