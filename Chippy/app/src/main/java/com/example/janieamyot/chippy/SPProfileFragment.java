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

        TextView welcome = getView().findViewById(R.id.welcomeMessage);
        String welcomeMessage = "Welcome " + extractAccount() + ", logged as Service Provider.";
        welcome.setText(welcomeMessage);
    }

    private String extractAccount(){
        ServiceProvider serviceProvider = (ServiceProvider) bundle.get("Account");
        return serviceProvider.getUserName();
    }
}
