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
        address.setText(extractAccount()[4]);
        TextView phone = getView().findViewById(R.id.spPhoneField);
        phone.setText(extractAccount()[5]);
        TextView description = getView().findViewById(R.id.spDescriptionField);
        description.setText(extractAccount()[6]);
        TextView licensed = getView().findViewById(R.id.spLicensedField);
        licensed.setText(extractAccount()[7]);
    }

    private String[] extractAccount(){
        ServiceProvider serviceProvider = (ServiceProvider) bundle.get("Account");
        String[] str = new String[8];
        str[0] = serviceProvider.getName();
        str[1] = serviceProvider.getLastName();
        str[2] = serviceProvider.getUserName();
        str[3] = serviceProvider.getEmail();
        //str[4] = serviceProvider.getAddress();
        //str[5] = serviceProvider.getPhone();
        //str[6] = serviceProvider.getDescription();
        //str[7] = serviceProvider.getLicensed();
        return str;
    }
}
