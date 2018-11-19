package com.example.janieamyot.chippy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SPServicesFragment extends Fragment {

    Bundle bundle;
    public Service serviceSelected;
    private Button addToSPlist;
    private Button deleteFromSPlist;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sp_services, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        bundle = intent.getExtras();

        //UI changes here
        //Use getActivity() when referring to ServiceProviderWelcomePage
        //ex. instead of this use getActivity()

        //this list view displayes the SP own services they provide
        ListView serviceListSP = getActivity().findViewById(R.id.spServicesList);
        final ArrayList<String> listServices = displayServices();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listServices);
        serviceListSP.setAdapter(adapter);
        final MyDBHandler dbHandler = new MyDBHandler(getActivity());

        //listener that selects a service from the SP list and then it can delete it
        serviceListSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String serviceString = listServices.get(position);
                String serviceName = serviceString.substring(serviceString.indexOf("[") + 1, serviceString.indexOf("]"));
                serviceSelected = dbHandler.findService(serviceName);
                dbHandler.close();

            }
        });

        //add button that opens edit services activity with all available activities to add to SPown list
        addToSPlist = findViewById(R.id.spServicesAdd);
        addToSPlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSPeditServicesActivity();

            }

        });
        // delete button that deletes ONE OF THE SP OWN SERVICES
        deleteFromSPlist = findViewById(R.id.spServicesDelete);
        deleteFromSPlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteServiceFromSPlist();

            }
        });
    }// end onActivityCreated

    private ArrayList<String> displayServices() {
        String services = "";

        Integer counter2 = 1;
        MyDBHandler dbHandler = new MyDBHandler(this);
        ArrayList<Service> serviceList = dbHandler.findAllSpServices();
        ArrayList<String> listServices = new ArrayList<String>();

        if (serviceList == null) {
            return listServices;
        }

        for (Service service : serviceList) {
            services = (counter2.toString() + " " + service.toString());

            services = services.concat(" ");

            listServices.add(services);
            counter2++;
        }


        dbHandler.close();
        return listServices;
    }


    public void openSPeditServicesActivity() {
        Intent intent = new Intent(this, ServiceProviderEditServices.class);
        startActivity(intent);
    }

    public void  deleteServiceFromSPlist{

        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.deleteSpService( .getUserName , service.getName()); //TODO HELP GET USERNAME

        Intent intent = new Intent(this, SPServicesFragment.class);
        startActivity(intent);

    }

}//end SPServicesFragment class
