package com.example.janieamyot.chippy;


public class Service {
    private double hourlyRate;
    private String name;
    private Category category;

    public Service(String name, double hourlyRate, Category category) {
        this.hourlyRate = hourlyRate;
        this.name = name;
        this.category = category;

        category.addService(this);

    }

    public double getHourlyRate(){
        return hourlyRate;
    }

    public String getName(){
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
