package com.example.janieamyot.chippy;

public class ServiceProvider extends Account{

    private Service [] myOfferedServices = new Service[];

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
    public void AddToMyService(Service newMyService){

        for (int i = 0; i<myOfferedServices.length; i++){
            //loops through and adds another service into stored
            //use this with DB and then display this list with similar code from
            //ADMIN that diplays accounts and services

            }
        }
    //-------------------------------END of ADD -----------------------------------------

    //TODO - The SP picks a service and DELETS it from his profile array - combine this with DB after Janie finishes
    public void DeleteMyService(Service newMyService){

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

    }

    }

}