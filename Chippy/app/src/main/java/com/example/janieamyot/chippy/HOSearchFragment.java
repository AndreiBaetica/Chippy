package com.example.janieamyot.chippy;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HOSearchFragment extends Fragment{

    Bundle bundle;
    private HomeOwner homeOwner;
    public ImageButton dateButton;
    public TextView dateSelected;
    public Spinner serviceSpinner;
    final Calendar c = Calendar.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_ho_search, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        bundle = intent.getExtras();
        homeOwner = (HomeOwner) bundle.get("Account");

        dateButton = (ImageButton) getActivity().findViewById(R.id.dateButton);
        dateSelected = (TextView) getActivity().findViewById(R.id.dateSelected);
        serviceSpinner = getActivity().findViewById(R.id.hoServiceSearch);

        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDate(v);
            }
        });
    }

    public void setDate(View v) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String str = dayOfMonth + "-" + (monthOfYear+1) + "-" + year;
                dateSelected.setText(str);
            }
        }, year, month, day);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.show();
    }

}
