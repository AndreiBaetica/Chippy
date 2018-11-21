package com.example.janieamyot.chippy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SPProfileFragment extends Fragment{

    Bundle bundle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_sp_profile, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        bundle = intent.getExtras();

        TextView name = getView().findViewById(R.id.spNameField);
        name.setText(extractAccount()[0]);
        TextView lastName = getView().findViewById(R.id.spLastNameField);
        lastName.setText(extractAccount()[1]);
        TextView userName = getView().findViewById(R.id.spUserNameField);
        userName.setText(extractAccount()[2]);
        TextView email = getView().findViewById(R.id.spEmailField);
        email.setText(extractAccount()[3]);
        TextView address = getView().findViewById(R.id.spAddressField);
        if (!extractAccount()[5].equals("")) {
            String setText = extractAccount()[4] +" " + extractAccount()[6] + " Apartment " + extractAccount()[5] + " " + extractAccount()[7] + " " + extractAccount()[8];
            address.setText(setText);
        }
        else{
            String setText = extractAccount()[4] +" " + extractAccount()[6] + " " + extractAccount()[7] + " " + extractAccount()[8];
            address.setText(setText);
        }
        TextView phone = getView().findViewById(R.id.spPhoneField);
        phone.setText(extractAccount()[9]);
        TextView company = getView().findViewById(R.id.spCompanyField);
        company.setText(extractAccount()[10]);
        TextView description = getView().findViewById(R.id.spDescriptionField);
        description.setText(extractAccount()[11]);
        TextView licensed = getView().findViewById(R.id.spLicensedField);
        licensed.setText(extractAccount()[12]);
    }

    private String[] extractAccount(){
        ServiceProvider serviceProvider = (ServiceProvider) bundle.get("Account");
        String[] str = new String[13];
        str[0] = serviceProvider.getName();
        str[1] = serviceProvider.getLastName();
        str[2] = serviceProvider.getUserName();
        str[3] = serviceProvider.getEmail();
        str[4] = serviceProvider.getStreetNumber().toString();
        str[5] = serviceProvider.getApartmentNumber();
        str[6] = serviceProvider.getStreetName();
        str[7] = serviceProvider.getCity();
        str[8] = serviceProvider.getCountry();
        str[9] = serviceProvider.getPhoneNumber();
        str[10] = serviceProvider.getCompany();
        str[11] = serviceProvider.getDescription();
        if(serviceProvider.isLicensed() == true){
            str[12] = "Yes";
        }
        else{
            str[12] = "No";
        }
        return str;
    }
}
