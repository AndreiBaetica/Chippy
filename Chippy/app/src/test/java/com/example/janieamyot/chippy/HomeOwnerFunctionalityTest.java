package com.example.janieamyot.chippy;

import static org.junit.Assert.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeOwnerFunctionalityTest {

    @Test
    public void checkBookingStartTime(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        assertEquals("Check the start time of the booking object", "2018 Dec 20 08:00", sdf.format(aBooking.getStartTime().getTime()));
    }

    @Test
    public void checkBookingEndTime(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        assertEquals("Check the end time of the booking object", "2018 Dec 20 10:00", sdf.format(aBooking.getEndTime().getTime()));
    }

    @Test
    public void checkBookingId(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        assertEquals("Check the booking ID of the booking object", "1", Long.toString(aBooking.getBookingId()));
    }

    @Test
    public void checkBookingHomeOwner(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        assertEquals("Check the home owner of the booking object", "Mahatma", aBooking.getHomeOwner().getName());
    }

    @Test
    public void checkBookingServiceProvider(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        assertEquals("Check the service provider of the booking object", "Martin", aBooking.getServiceProvider().getName());
    }

    @Test
    public void checkBookingService(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        assertEquals("Check the service of the booking object", "Name: [Light bulb replacement]  Rate ($/h) 180.0  Category: Electrical", aBooking.getService().toString());
    }

    @Test
    public void checkRatingRating(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        Rating aRating = new Rating(5, 3, "Good job!", aBooking);
        assertEquals("Check the rating of the rating object", "5", Integer.toString(aRating.getRating()));
    }

    @Test
    public void checkRatingRatingId(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        Rating aRating = new Rating(5, 3, "Good job!", aBooking);
        assertEquals("Check the rating id of the rating object", "3", Integer.toString(aRating.getRatingID()));
    }

    @Test
    public void checkRatingComment(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        Rating aRating = new Rating(5, 3, "Good job!", aBooking);
        assertEquals("Check the comment of the rating object", "Good job!", aRating.getComment());
    }

    @Test
    public void checkRatingBooking(){
        Category aCategory = new Category("Electrical Work");
        Service aService = new Service(180.0, "Light bulb replacement","Electrical" );
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(aService);
        ServiceProvider aServiceProvider = new ServiceProvider("Martin", "Luther King", "mlk115", "!nonviolence173", "test@test.com", 123, "3A", "Laurier", "Ottawa", "Canada", "Chippy", "Test", true, "123-123-1234", "availabilities test", services);
        HomeOwner aHomeOwner = new HomeOwner("Mahatma", "Gandhi", "gandhi254", "$non573violence", "gandhi@gmail.com");
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 8);
        startTime.set(Calendar.DAY_OF_MONTH, 20);
        startTime.set(Calendar.MONTH, 11);
        startTime.set(Calendar.YEAR, 2018);
        startTime.set(Calendar.MINUTE, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.DAY_OF_MONTH, 20);
        endTime.set(Calendar.MONTH, 11);
        endTime.set(Calendar.YEAR, 2018);
        endTime.set(Calendar.MINUTE, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        Booking aBooking = new Booking(aService, aServiceProvider, aHomeOwner, startTime, endTime, 1);
        Rating aRating = new Rating(5, 3, "Good job!", aBooking);
        assertEquals("Check the booking of the rating object", "1", Long.toString(aRating.getBooking().getBookingId()));
    }

}
