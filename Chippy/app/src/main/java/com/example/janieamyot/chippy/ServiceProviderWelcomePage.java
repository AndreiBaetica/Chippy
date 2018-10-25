package com.example.janieamyot.chippy;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ServiceProviderWelcomePage extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_welcome_page);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        TextView welcome = findViewById(R.id.welcomeMessage);
        String welcomeMessage = "Welcome " + extractAccount();
        welcome.setText(welcomeMessage);
    }

    private String extractAccount(){
        ServiceProvider serviceProvider = (ServiceProvider) bundle.get("Account");
        return serviceProvider.getUserName();
    }
    public void OnClickLogOut(View view){

        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);

    }
}

