package com.example.janieamyot.chippy;

public class ServiceProvider extends Account{
    private Integer streetNumber;
    private Integer apartmentNumber;
    private String streetName;
    private String city;
    private String country;
    private String company;
    private String description;
    private String phoneNumber;
    private boolean isLicenced;
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
    public void setLicenced(boolean isLiscenced){
        this.isLicenced =isLiscenced;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setCity(String city){
        this.city=city;
    }
    public void setCountry(String country){
        this.country = country;
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
    public boolean isLicenced() {
        return isLicenced;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}