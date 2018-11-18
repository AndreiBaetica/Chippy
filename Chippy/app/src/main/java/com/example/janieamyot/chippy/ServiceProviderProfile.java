package com.example.janieamyot.chippy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.*;


public class ServiceProviderProfile extends AppCompatActivity {
    private Bundle bundle;
    private ServiceProvider serviceProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        serviceProvider = (ServiceProvider) bundle.get("Account");
        countriesList();
    }

    public void countriesList(){
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }

        Collections.sort(countries);
        countries.remove("Canada");
        countries.add(0,"Canada");
        countries.remove("United States");
        countries.add(1,"United States");

        Spinner spinner = findViewById(R.id.spinnerCountries);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(countryAdapter);
    }
    public void onSaveClick(View view){
        EditText field;
        boolean flag= true;
        MyDBHandler dbHandler = new MyDBHandler(this);
        field = findViewById(R.id.spEditStreetName);
        String streetName = field.getText().toString();
        if (!streetName.equals("")){
            if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
                dbHandler.editStreetName(serviceProvider.getUserName(), streetName);
            }else {
                serviceProvider.setStreetName(streetName);
            }
        }
        else{
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Street name cannot be empty.", Toast.LENGTH_LONG).show();
            flag = false;
        }
        field = findViewById(R.id.spEditStreetNumber);
        String streetNumber = field.getText().toString();
        if (TextUtils.isDigitsOnly(streetNumber)){
            if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
                dbHandler.editStreetNumber(serviceProvider.getUserName(), Integer.parseInt(streetNumber));
            }else {
                serviceProvider.setStreetNumber(streetNumber);
            }
        }
        else{
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Street number cannot be have non-numerical characters", Toast.LENGTH_LONG).show();
            flag = false;
        }
        if (streetNumber.equals("")){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Street number cannot be empty.", Toast.LENGTH_LONG).show();
            flag = false;
        }
        //optional apartment number so it can be blank
        field = findViewById(R.id.spEditApartmentNumber);
        String apartmentNumber = field.getText().toString();
        if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
            dbHandler.editApartmentNumber(serviceProvider.getUserName(), apartmentNumber);
        }else {
            serviceProvider.setApartmentNumber(apartmentNumber);
        }
        field = findViewById(R.id.spEditCity);
        String city = field.getText().toString();
        if (!city.equals("")){
            if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
            dbHandler.editCity(serviceProvider.getUserName(), city);
            }else {
                serviceProvider.setCity(city);
            }
        }
        else{
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "City cannot be empty.", Toast.LENGTH_LONG).show();
            flag = false;
        }
        Spinner spinner = findViewById(R.id.spinnerCountries);
        String country = spinner.toString();
        if (!country.equals("")){
            if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
                dbHandler.editCountry(serviceProvider.getUserName(), country);
            }else {
                serviceProvider.setCountry(country);
            }
        }
        else{
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Country cannot be empty.", Toast.LENGTH_LONG).show();
            flag = false;
        }
        field = findViewById(R.id.spEditPhone);
        String phoneNumber = field.getText().toString();
        if (!phoneNumber.equals("") && phoneNumber.length() == 12 && phoneNumber.charAt(3) == '-' && phoneNumber.charAt(7) == '-'){
            String str1 = phoneNumber.substring(0,3);
            String str2 = phoneNumber.substring(4,7);
            String str3 = phoneNumber.substring(8,phoneNumber.length());
            if(TextUtils.isDigitsOnly(str1) && TextUtils.isDigitsOnly(str2) && TextUtils.isDigitsOnly(str3)) {
                if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
                    dbHandler.editPhoneNumber(serviceProvider.getUserName(), phoneNumber);
                }else {
                    serviceProvider.setPhoneNumber(phoneNumber);
                }
            }
            else{
                field.getText().clear();
                Toast.makeText(getApplicationContext(), "Phone number should only have digits.", Toast.LENGTH_LONG).show();
                flag = false;
            }
        }
        else{
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Phone number should be in the form XXX-XXX-XXXX.", Toast.LENGTH_LONG).show();
            flag = false;
        }
        field = findViewById(R.id.spEditCompany);
        String company = field.getText().toString();
        if (!company.equals("")){
            if(dbHandler.spProfileExists(serviceProvider)) {
                dbHandler.editCompany(serviceProvider.getUserName(), company);
            }else {
                serviceProvider.setCompany(comapny);
            }
        }
        else{
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Company cannot be empty.", Toast.LENGTH_LONG).show();
            flag = false;
        }

        //description is optional so it can be blank
        field = findViewById(R.id.spEditDescription);
        String description = field.getText().toString();
        if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
            dbHandler.editDescription(serviceProvider.getUserName(), description);
        }else {
            serviceProvider.setDescription(description);
        }

        //license is optional so it can be blank
        RadioButton licensedYes = findViewById(R.id.spLicensedYes);
        RadioButton licensedNo = findViewById(R.id.spLicensedNo);
        if (licensedYes.isChecked()){
            if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
                dbHandler.editIsLicensed(serviceProvider.getUserName(), true);
            }else {
                serviceProvider.setIsLicensed(true);
            }
        }
        if (licensedNo.isChecked()){
            if(dbHandler.spProfileExists(serviceProvider.getUserName())) {
                dbHandler.editIsLicensed(serviceProvider.getUserName(), false);
            }else {
                serviceProvider.setIsLicensed(false);
            }
        }
        if(!dbHandler.spProfileExists(serviceProvider.getUserName())){
            dbHandler.editSpProfile(serviceProvider);
        }
        dbHandler.close();
        if(flag){
            Intent intent = new Intent(getApplicationContext(), ServiceProviderWelcomePage.class);
            bundle.putSerializable("Account", serviceProvider);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    public void onCancelClick(View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        bundle.putSerializable("Account", serviceProvider);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
