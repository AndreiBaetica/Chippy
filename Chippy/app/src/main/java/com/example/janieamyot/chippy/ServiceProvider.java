package com.example.janieamyot.chippy;

public class ServiceProvider extends Account{
    private Integer streetNumber;
    private Integer apartmentNumber;
    private String streetName;
    private String company;
    private String description;
    private boolean isLiscenced;
    public ServiceProvider(){
        super();
    }

    public ServiceProvider(String name, String lastName, String userName, String password, String email){
    super(name,lastName,userName, password, email);
  }
    public void setStreetNumber(Integer streetNumber){
        this.streetNumber=streetNumber;
    }
    public void setApartmentNumber(Integer apartmentNumber){
        this.apartmentNumber=apartmentNumber;
    }
    public void setStreetName(String streetName){
        this.streetName = streetName;
    }
    public void setCompany(String company){
        this.company=company;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setLiscenced(boolean isLiscenced){
        this.isLiscenced=isLiscenced;
    }
    public Integer getStreetNumber(){
        return streetNumber;
    }
    public Integer getApartmentNumber(){
        return apartmentNumber;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getCompany() {
        return company;
    }
    public String getDescription() {
        return description;
    }
    public boolean isLiscenced() {
        return isLiscenced;
    }
}