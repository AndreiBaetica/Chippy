package com.example.janieamyot.chippy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import org.json.*;

public class ServiceProviderAvailable extends AppCompatActivity {

    private ServiceProvider account;

    private JSONArray monday;
    private JSONArray tuesday;
    private JSONArray wednesday;
    private JSONArray thursday;
    private JSONArray friday;
    private JSONArray saturday;
    private JSONArray sunday;

    private JSONObject availabilities;

    private enum Weekday {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}
    private Weekday day = Weekday.MONDAY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_available);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        account = (ServiceProvider) savedInstanceState.get("Account");
    }

    public void setMonday(View view) {
        updateOldDay();
        day = Weekday.MONDAY;
    }
    public void setTuesday(View view) {
        updateOldDay();
        day = Weekday.TUESDAY;
    }
    public void setWednesday(View view) {
        updateOldDay();
        day = Weekday.WEDNESDAY;
    }
    public void setThursday(View view) {
        updateOldDay();
        day = Weekday.THURSDAY;
    }
    public void setFriday(View view) {
        updateOldDay();
        day = Weekday.FRIDAY;
    }
    public void setSaturday(View view) {
        updateOldDay();
        day = Weekday.SATURDAY;
    }
    public void setSunday(View view) {
        updateOldDay();
        day = Weekday.SUNDAY;
    }

    public void onClickSave(View view) throws JSONException {
        availabilities.put("monday", monday);
        availabilities.put("tuesday", tuesday);
        availabilities.put("wednesday", wednesday);
        availabilities.put("thursday", thursday);
        availabilities.put("friday", friday);
        availabilities.put("saturday", saturday);
        availabilities.put("sunday", friday);

        MyDBHandler dbHandler = new MyDBHandler(this);
        //TODO: update availabilities in DB
        dbHandler.close();
        Intent intent = new Intent(getApplicationContext(), ServiceProviderWelcomePage.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Account", account);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void populateDay() {
        if (account.getAvailabilities() == null) {
            return;
        } else {
            //TODO: Get availability for current day. Call at end of each day setter method,
            // and (maybe) once at activity creation to populate Monday.

            //potential short-term memory solution for flipping through days repeatedly: populate using the
            //class json arrays, then wipe the array, and rewrite when day is changed.
        }
    }
    private void updateOldDay() {
        switch (day) {
            case MONDAY:
                updateDay(monday);
            case TUESDAY:
                updateDay(tuesday);
            case WEDNESDAY:
                updateDay(wednesday);
            case THURSDAY:
                updateDay(thursday);
            case FRIDAY:
                updateDay(friday);
            case SATURDAY:
                updateDay(saturday);
            case SUNDAY:
                updateDay(sunday);
        }
    }

    private void updateDay(JSONArray day) {
        CheckBox box;
        box = findViewById(R.id.c0);
        if (box.isChecked()) {
            day.put(0);
        }
        box = findViewById(R.id.c1);
        if (box.isChecked()) {
            day.put(1);
        }
        box = findViewById(R.id.c2);
        if (box.isChecked()) {
            day.put(2);
        }
        box = findViewById(R.id.c3);
        if (box.isChecked()) {
            day.put(3);
        }
        box = findViewById(R.id.c4);
        if (box.isChecked()) {
            day.put(4);
        }
        box = findViewById(R.id.c5);
        if (box.isChecked()) {
            day.put(5);
        }
        box = findViewById(R.id.c6);
        if (box.isChecked()) {
            day.put(6);
        }
        box = findViewById(R.id.c7);
        if (box.isChecked()) {
            day.put(7);
        }
        box = findViewById(R.id.c8);
        if (box.isChecked()) {
            day.put(8);
        }
        box = findViewById(R.id.c9);
        if (box.isChecked()) {
            day.put(9);
        }
        box = findViewById(R.id.c10);
        if (box.isChecked()) {
            day.put(10);
        }
        box = findViewById(R.id.c11);
        if (box.isChecked()) {
            day.put(11);
        }
        box = findViewById(R.id.c12);
        if (box.isChecked()) {
            day.put(12);
        }
        box = findViewById(R.id.c13);
        if (box.isChecked()) {
            day.put(13);
        }
        box = findViewById(R.id.c14);
        if (box.isChecked()) {
            day.put(14);
        }
        box = findViewById(R.id.c15);
        if (box.isChecked()) {
            day.put(15);
        }
        box = findViewById(R.id.c16);
        if (box.isChecked()) {
            day.put(16);
        }
        box = findViewById(R.id.c17);
        if (box.isChecked()) {
            day.put(17);
        }
        box = findViewById(R.id.c18);
        if (box.isChecked()) {
            day.put(18);
        }
        box = findViewById(R.id.c19);
        if (box.isChecked()) {
            day.put(19);
        }
        box = findViewById(R.id.c20);
        if (box.isChecked()) {
            day.put(20);
        }
        box = findViewById(R.id.c21);
        if (box.isChecked()) {
            day.put(21);
        }
        box = findViewById(R.id.c22);
        if (box.isChecked()) {
            day.put(22);
        }
        box = findViewById(R.id.c23);
        if (box.isChecked()) {
            day.put(23);
        }

    }


}
