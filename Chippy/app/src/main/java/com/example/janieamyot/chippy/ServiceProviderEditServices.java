package com.example.janieamyot.chippy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ServiceProviderEditServices extends AppCompatActivity {

    Bundle bundle;
    private ServiceProvider account;
    private Service service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_edit_services);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        Intent intent = getIntent();
        bundle = intent.getExtras();
        account = (ServiceProvider) bundle.get("Account");


        // ALL service list display and service list listener for selecting a service to ad to SP OWN list
        ListView serviceList = findViewById(R.id.spServicesList);
        final ArrayList<String> listServices = displayServices();
        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listServices);
        serviceList.setAdapter(adapter2);
        final MyDBHandler dbHandler = new MyDBHandler(this);

        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String serviceString = listServices.get(position);

                String serviceName = serviceString.substring(serviceString.indexOf("[") + 1, serviceString.indexOf("]"));
                service = dbHandler.findService(serviceName);
                dbHandler.close();
            }
        });


} //end onCreate

    //button adds from avilable services to SP OWN list
    public void onClickAddToSPlist(View view) {
        if (service== null) {
            return;
        }
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addSpService( account.getUserName() , service.getName());
        dbHandler.close();

        Intent intent = new Intent(this, SPServicesFragment.class);
        startActivity(intent);

    }
    // goes back to FragSP
    public void onClickCancelToFragSP(View view) {

        Intent intent = new Intent(this, SPServicesFragment.class);
        startActivity(intent);

    }


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




}//end ServiceProviderEditServices class