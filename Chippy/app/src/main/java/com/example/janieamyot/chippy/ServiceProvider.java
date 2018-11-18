package com.example.janieamyot.chippy;


import java.util.ArrayList;


public class ServiceProvider extends Account{
    private int streetNumber;
    private String apartmentNumber;
    private String streetName;
    private String city;
    private String country;
    private String company;
    private String description;
    private String phoneNumber;
    private boolean isLicensed;
    private String availabilities;
    private ArrayList<Service> services;
    

    public ServiceProvider(){
        super();
    }

    public ServiceProvider(String name, String lastName, String userName, String password, String email){
    super(name,lastName,userName, password, email);
  }



  //TODO - show list of services the SP has to chose from - similar to ADMIN displayed services
    /*
    private ArrayList<String> displayServices(){
        String services = "";

        Integer counter2 = 1;
        MyDBHandler dbHandler = new MyDBHandler(this);
        ArrayList<Service> serviceList = dbHandler.findAllServices();
        ArrayList<String> listServices = new ArrayList<String>();

        if (serviceList==null){
            return listServices;
        }

        for(Service service : serviceList) {
            services = (counter2.toString() + " " + service.toString());

                services=services.concat(" ");

            listServices.add(services);
            counter2 ++;
        }


        dbHandler.close();
        return listServices;
    }
    */
    //-------------------------------END Display -----------------------------------------

    //TODO - The SP picks a service from the list and ADDs to his profile in array of services - combine this with DB after Janie finishes
   // public void AddToMyService(Service newMyService){

       // for (int i = 0; i<myOfferedServices.length; i++){
            //loops through and adds another service into stored
            //use this with DB and then display this list with similar code from
            //ADMIN that diplays accounts and services

          //  }
     //   }
    //-------------------------------END of ADD -----------------------------------------

    //TODO - The SP picks a service and DELETS it from his profile array - combine this with DB after Janie finishes
  // public void DeleteMyService(Service newMyService){

    /* Code below is an idea - we just modify:
          if (service == null) {
            return;
        }
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.deleteService(service.getName());

        finish();
        startActivity(getIntent());
    * */

    //-------------------------------END of DELETE -----------------------------------------

  //  }

  //  }


    public ServiceProvider(String name, String lastName, String userName, String password, String email, int streetNumber, String apartmentNumber, String streetName, String city, String country, String company, String description, boolean isLicensed, String phoneNumber, String availabilities, ArrayList<Service> services){
        super(name,lastName,userName, password, email);
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.company = company;
        this.description = description;
        this.isLicensed = isLicensed;
        this.phoneNumber = phoneNumber;
        this.availabilities = availabilities;
        this.services = services;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void setStreetNumber(Integer streetNumber){
        this.streetNumber=streetNumber;
    }
    public void setApartmentNumber(String apartmentNumber){
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
    public void setLicensed(boolean isLiscenced){
        this.isLicensed =isLiscenced;
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
    public String getApartmentNumber(){
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
    public boolean isLicensed() {
        return isLicensed;
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
    public ArrayList<Service> getServices() { return services; }
    public String getAvailabilities() { return availabilities; }

}