package com.example.janieamyot.chippy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import android.text.Editable;
import android.widget.EditText;
import java.io.IOException;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static com.example.janieamyot.chippy.R.id.emailField;
import static com.example.janieamyot.chippy.R.id.firstNameField;
import static com.example.janieamyot.chippy.R.id.lastNameField;
import static com.example.janieamyot.chippy.R.id.userNameField;
import static com.example.janieamyot.chippy.R.id.passwordField;

public class CreateNewAccount extends AppCompatActivity {

    private enum FieldType {NAME, EMAIL, PASSWORD}
    private enum AccountType {ADMIN, SERVICE, USER}

    private AccountType accountType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.button_admin:
                if (checked) {
                    accountType = AccountType.ADMIN;
                }
            case R.id.button_serviceprovider:
                if (checked) {
                    accountType = AccountType.SERVICE;
                }
            case R.id.button_homeowner:
                if (checked) {
                    accountType = AccountType.USER;
                }
        }
    }

    private boolean isValid(String input, FieldType type){
        switch (type){
            case NAME:
                return !(input.equals(""));
            case EMAIL:
                boolean ret = true;
                try {
                    InternetAddress email = new InternetAddress(input);
                    email.validate();
                } catch (AddressException e){
                    ret = false;
                }
                return ret;
            case PASSWORD:
                char[] inputChars = input.toCharArray();
                return (inputChars.length >= 7);
        }
        return false;
    }


    private void onClickNext(){
        Boolean validInputs = true;

        String firstName;
        String lastName;
        String email;
        String username;
        String password;

        EditText field;

        //checking input validity
        field = findViewById(firstNameField);
        firstName = field.getText().toString();
        if (!isValid(firstName, FieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "First name must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(lastNameField);
        lastName = field.getText().toString();
        if (!isValid(lastName, FieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Last name must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(emailField);
        email = field.getText().toString();
        if (!isValid(email, FieldType.EMAIL)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Invalid email.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(userNameField);
        username = field.getText().toString();
        if (!isValid(username, FieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Username must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(passwordField);
        password = field.getText().toString();
        if (!isValid(password, FieldType.PASSWORD)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "First name must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        if (accountType == null) {
            validInputs = false;
        }

        //account creation
        if (validInputs) {
            MyDBHandler dbHandler = new MyDBHandler(this);
            switch(accountType) {
                case ADMIN:
                    Admin admin = new Admin(firstName, lastName, username, password, email);
                    dbHandler.addAccount(admin);
                    dbHandler.close();
                    Intent intent = new Intent(getApplicationContext(), AdminWelcomePage.class);
                    startActivity(intent);
                case SERVICE:
                    ServiceProvider serviceProvider = new ServiceProvider(firstName, lastName, username, password, email);
                    dbHandler.addAccount(serviceProvider);
                    dbHandler.close();
                case USER:
                    HomeOwner homeOwner = new HomeOwner(firstName, lastName, username, password, email);
                    dbHandler.addAccount(homeOwner);
                    dbHandler.close();
            }
        } else {
            //TODO: handle invalid inputs
        }
        //TODO: check for field uniqueness


    }
}
