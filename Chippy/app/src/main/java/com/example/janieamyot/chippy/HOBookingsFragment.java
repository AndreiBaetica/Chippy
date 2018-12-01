package com.example.janieamyot.chippy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HOBookingsFragment extends Fragment {

    Bundle bundle;
    private HomeOwner homeOwner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_ho_bookings, container, false);
    }
}
