package com.example.janieamyot.chippy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ServiceProvider extends Account{

    private Service [] myOfferedServices = new Service[];

    public ServiceProvider(){
        super();
    }

    public ServiceProvider(String name, String lastName, String userName, String password, String email){
    super(name,lastName,userName, password, email);
  }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        bundle = intent.getExtras();

        //UI changes here
        //Use getActivity() when referring to ServiceProviderWelcomePage
        //ex. instead of this use getActivity()

        ListView serviceList = getActivity().findViewById(R.id.spServicesList);
        final ArrayList<String> listServices = displayServices();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, listServices);
        serviceList.setAdapter(adapter);
        final MyDBHandler dbHandler = new MyDBHandler(getActivity());

        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: ADD LOGIC HERE

            }
        });

    }

    private ArrayList<String> displayServices(){
        // TODO: ADD LOGIC HERE
        ArrayList<String> listServices = new ArrayList<String>();

        return listServices;
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