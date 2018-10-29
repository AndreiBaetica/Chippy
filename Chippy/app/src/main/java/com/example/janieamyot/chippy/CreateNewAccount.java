package com.example.janieamyot.chippy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;


import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static com.example.janieamyot.chippy.R.id.emailField;
import static com.example.janieamyot.chippy.R.id.firstNameField;
import static com.example.janieamyot.chippy.R.id.lastNameField;
import static com.example.janieamyot.chippy.R.id.start;
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
    //TODO: Refinement: Refactor select methods into one switch statement
    public void selectAdmin(View view) {
        accountType = AccountType.ADMIN;
    }

    public void selectService(View view) {
        accountType = AccountType.SERVICE;
    }

    public void selectUser(View view) {
        accountType = AccountType.USER;
    }

    private boolean isInvalid(String input, FieldType type){
        switch (type){
            case NAME:
                return (input.equals(""));
            case EMAIL:
                boolean ret = true;
                try {
                    InternetAddress email = new InternetAddress(input);
                    email.validate();
                } catch (AddressException e){
                    ret = false;
                }
                if(!(input.substring(input.indexOf('@')+1).contains(".") && input.substring(input.indexOf('.')+1).length()>=2)){
                    ret = false;
                }
                return !ret;
            case PASSWORD:
                char[] inputChars = input.toCharArray();
                return (inputChars.length < 5);
        }
        return true;
    }


    public void onClickNext(View view){
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
        if (isInvalid(firstName, FieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "First name must not be empty.", Toast.LENGTH_LONG).show();
            validInputs = false;
        }
        field = findViewById(lastNameField);
        lastName = field.getText().toString();
        if (isInvalid(lastName, FieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Last name must not be empty.", Toast.LENGTH_LONG).show();
            validInputs = false;
        }
        field = findViewById(emailField);
        email = field.getText().toString();
        if (isInvalid(email, FieldType.EMAIL)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Invalid email.", Toast.LENGTH_LONG).show();
            validInputs = false;
        }
        field = findViewById(userNameField);
        username = field.getText().toString();
        if (isInvalid(username, FieldType.NAME)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Username must not be empty.", Toast.LENGTH_LONG).show();
            validInputs = false;
        }
        field = findViewById(passwordField);
        password = field.getText().toString();
        if (isInvalid(password, FieldType.PASSWORD)){
            field.getText().clear();
            Toast.makeText(getApplicationContext(), "Password must contain at least 5 characters", Toast.LENGTH_LONG).show();
            validInputs = false;
        }
        if (accountType == null) {
            Toast.makeText(getApplicationContext(), "Account type not selected", Toast.LENGTH_LONG).show();
            validInputs = false;
        }
        MyDBHandler dbHandler = new MyDBHandler(this);
        if (dbHandler.adminExists() && accountType == AccountType.ADMIN) {
            Toast.makeText(getApplicationContext(), "Admin account already exist, no permission to make multiple.", Toast.LENGTH_LONG).show();
            validInputs = false;
            accountType = null;
        }
        if (dbHandler.findAccountByUserName(username) != null){
            Toast.makeText(getApplicationContext(), "Username already in use.", Toast.LENGTH_LONG).show();
            validInputs = false;
        }

        //account creation
        if (validInputs) {
            Intent intent;
            Bundle bundle = new Bundle();
            switch(accountType) {
                case ADMIN:
                    Admin admin = new Admin(firstName, lastName, username, password, email);
                    dbHandler.addAccount(admin);
                    dbHandler.close();
                    intent = new Intent(getApplicationContext(), AdminWelcomePage.class);
                    bundle.putSerializable("Account", admin);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

                case SERVICE:
                    ServiceProvider serviceProvider = new ServiceProvider(firstName, lastName, username, password, email);
                    dbHandler.addAccount(serviceProvider);
                    intent = new Intent(getApplicationContext(), ServiceProviderWelcomePage.class);
                    bundle.putSerializable("Account", serviceProvider);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

                case USER:
                    HomeOwner homeOwner = new HomeOwner(firstName, lastName, username, password, email);
                    dbHandler.addAccount(homeOwner);
                    dbHandler.close();
                    intent = new Intent(getApplicationContext(), HomeOwnerWelcomePage.class);
                    bundle.putSerializable("Account", homeOwner);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

            }
        }
        //TODO: check for field uniqueness


    }
}
