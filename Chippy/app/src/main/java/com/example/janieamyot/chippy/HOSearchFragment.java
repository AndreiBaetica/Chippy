package com.example.janieamyot.chippy;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HOSearchFragment extends Fragment{

    Bundle bundle;
    private HomeOwner homeOwner;
    private enum SearchBy {SERVICE, DATETIME, RATING}
    private SearchBy searchParam;

    public ImageButton dateButton;
    public TextView dateSelected;
    public Spinner serviceSpinner;
    public Spinner startTimeSpinner;
    public Spinner endTimeSpinner;
    public Spinner ratingSpinner;
    public Button searchButton;
    public RadioGroup searchBy;
    public RadioButton searchByService;
    public RadioButton searchByDateTime;
    public RadioButton searchByRating;

    public String startTime;
    final Calendar c = Calendar.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_ho_search, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        bundle = intent.getExtras();
        homeOwner = (HomeOwner) bundle.get("Account");

        dateButton = getActivity().findViewById(R.id.dateButton);
        dateSelected = getActivity().findViewById(R.id.dateSelected);
        serviceSpinner = getActivity().findViewById(R.id.hoServiceSearch);
        startTimeSpinner = getActivity().findViewById(R.id.hoStartTimeSearch);
        endTimeSpinner = getActivity().findViewById(R.id.hoEndTimeSearch);
        ratingSpinner = getActivity().findViewById(R.id.hoRatingSearch);
        searchButton = getActivity().findViewById(R.id.hoSearchButton);

        searchBy = getActivity().findViewById(R.id.hoSearchBy);
        searchByService = getActivity().findViewById(R.id.hoSearchByService);
        searchByDateTime = getActivity().findViewById(R.id.hoSearchByDateTime);
        searchByRating = getActivity().findViewById(R.id.hoSearchByRating);

        ArrayList<String> listServices = findAllServices();
        ArrayAdapter adapterServices = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item, listServices);
        serviceSpinner.setAdapter(adapterServices);

        dateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setDate(v);
            }
        });

        checkIfStartTimeSelected();

        startTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                startTime = parentView.getItemAtPosition(position).toString();
                checkIfStartTimeSelected();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(searchParam) {
                    case SERVICE:
                        searchByService();
                        break;
                    case DATETIME:
                        searchByDateTime();
                        break;
                    case RATING:
                        searchByRating();
                        break;
                }
            }
        });

        searchByService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchParam = SearchBy.SERVICE;
                endTimeSpinner.setSelection(0);
            }
        });

        searchByDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchParam = SearchBy.DATETIME;
            }
        });

        searchByRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchParam = SearchBy.RATING;
            }
        });

    }

    //TODO Search methods for diff types of search. Include validation checks
    private void searchByService(){
        //TODO Add logic to search service providers by service in db, put in bundle and start new activity
        Toast.makeText(getActivity(),"SERVICE",Toast.LENGTH_LONG).show();
    }

    private void searchByDateTime(){
        //TODO Add logic to search service providers by datetime in db, put in bundle and start new activity
        Toast.makeText(getActivity(),"DATETIME",Toast.LENGTH_LONG).show();
    }

    private void searchByRating(){
        //TODO Add logic to search service providers by rating in db, put in bundle and start new activity
        Toast.makeText(getActivity(),"RATING",Toast.LENGTH_LONG).show();
    }

    private ArrayList<String> findAllServices(){
        ArrayList<String> listServices = new ArrayList<>();
        MyDBHandler dbHandler = new MyDBHandler(getActivity());
        ArrayList<Service> serviceList = dbHandler.findAllServices();

        if (serviceList==null){
            return listServices;
        }
        listServices.add("Select a service");
        for(Service service : serviceList) {
            listServices.add(service.getName());
        }
        dbHandler.close();
        return listServices;
    }

    public void setDate(View v) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String dayOfWeek = DateFormat.format("EEEE", new Date(year, monthOfYear, dayOfMonth-1)).toString();
                String date = " " + dayOfMonth + "-" + (monthOfYear+1) + "-" + year;
                String str = dayOfWeek + date;
                dateSelected.setText(str);
            }
        }, year, month, day);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dpd.show();
    }

    private void checkIfStartTimeSelected(){
        if (startTimeSpinner.getSelectedItemPosition() == 0){
            endTimeSpinner.setEnabled(false);
            endTimeSpinner.setClickable(false);
            endTimeSpinner.setSelection(0);
        } else {
            endTimeSpinner.setSelection(0);
            endTimeSpinner.setEnabled(true);
            endTimeSpinner.setClickable(true);
        }
    }

    //can use array instead
    private ArrayList<Integer> extractTimeRange(){
        ArrayList<Integer> range = new ArrayList<>();
        if (startTimeSpinner.getSelectedItemPosition() != 0 && endTimeSpinner.getSelectedItemPosition() != 0){
            String start = startTimeSpinner.getSelectedItem().toString();
            String end = endTimeSpinner.getSelectedItem().toString();
            int startTime = Integer.parseInt(start.substring(0,start.indexOf(":")));
            int endTime = Integer.parseInt(end.substring(0,end.indexOf(":")));
            for (int i = startTime; i < endTime; i++){
                range.add(i);
            }
        }
        return range;
    }

    private String extractDayOfWeek(){
        if (dateSelected == null) {
            return null;
        }
        String field = dateSelected.getText().toString();
        return (field.substring(0, field.indexOf("y")+1)).toLowerCase();
    }

}
