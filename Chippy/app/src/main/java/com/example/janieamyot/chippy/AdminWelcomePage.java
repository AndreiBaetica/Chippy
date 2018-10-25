package com.example.janieamyot.chippy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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


    private String extractAccount(){
        Admin admin = (Admin) bundle.get("Account");
        return admin.getUserName();
    }

    public void OnClickLogOut(View view){

        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);

    }

}
