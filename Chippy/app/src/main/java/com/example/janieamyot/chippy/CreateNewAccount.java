package com.example.janieamyot.chippy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private enum fieldType {NAME, EMAIL, PASSWORD}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
    }

    private boolean isValid(String input, fieldType type){
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


    private void nextClick(){
        Boolean validInputs = true;

        String firstName;
        String lastName;
        String email;
        String username;
        String password;

        EditText field;

        field = findViewById(firstNameField);
        firstName = field.getText().toString();
        if (!isValid(firstName, fieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "First name must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(lastNameField);
        lastName = field.getText().toString();
        if (!isValid(lastName, fieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Last name must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(emailField);
        email = field.getText().toString();
        if (!isValid(email, fieldType.EMAIL)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Invalid email.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(userNameField);
        username = field.getText().toString();
        if (!isValid(username, fieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Username must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }
        field = findViewById(passwordField);
        password = field.getText().toString();
        if (!isValid(password, fieldType.PASSWORD)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "First name must not be empty.", Toast.LENGTH_LONG);
            validInputs = false;
        }

        //TODO: check for field uniqueness, create account and add to DB.


    }
}
