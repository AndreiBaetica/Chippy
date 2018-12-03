package com.example.janieamyot.chippy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HOSearchResults extends AppCompatActivity {


    Bundle bundle;
    private HomeOwner homeOwner;
    Dialog dialog;
    ArrayList<ServiceProvider> spList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ho_search_result);

        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        homeOwner = (HomeOwner) bundle.get("Account");
        spList = (ArrayList<ServiceProvider>) bundle.get("SearchResult");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(spList.get(position).getTitle());
                text2.setText(spList.get(position).getSubTitle());
                return view;
            }
        };
        bookingList.setAdapter(adapter);

    }




    private ArrayList<ListItem> displayProviders(ArrayList<ServiceProvider> spList){

        String title = "";
        String subtitle = "";
        int bookingId;

        ArrayList<ListItem> listProviders = new ArrayList<>();

        if (spList == null) {
            return listProviders;
        }
        for (ServiceProvider sp : spList) {

            title = sp.getUserName();
            subtitle = sp.;
            bookingId = booking.getBookingId();

            listBookings.add(new ListItem(title, subtitle, bookingId));
        }

        dbHandler.close();
        return listProviders;
    }

}

