package com.example.janieamyot.chippy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminWelcomePage extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome_page);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        TextView welcome = findViewById(R.id.welcomeMessage);
        String welcomeMessage = "Welcome " + extractAccount();
        welcome.setText(welcomeMessage);
    }

    private String displayAccounts(){
        String accounts = "";

        Integer counter = 1;
        MyDBHandler dbHandler = new MyDBHandler(this);
        ArrayList<Account> accountList = dbHandler.findAllAccounts();
        for(Account account : accountList) {
            accounts.concat(counter.toString() + " " + account.toString() + "\n");
            counter ++;
        }
        dbHandler.close();
        return accounts;
    }

    private String extractAccount(){
        Admin admin = (Admin) bundle.get("Account");
        return admin.getUserName();
    }

    public void OnClickLogOut(View view){

        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);

    }

}
