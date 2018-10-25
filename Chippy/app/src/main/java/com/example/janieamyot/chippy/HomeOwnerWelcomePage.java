package com.example.janieamyot.chippy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeOwnerWelcomePage extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_welcome_page);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        TextView welcome = findViewById(R.id.welcomeMessage);
        String welcomeMessage = "Welcome " + extractAccount();
        welcome.setText(welcomeMessage);
    }

    private String extractAccount(){
        HomeOwner homeOwner = (HomeOwner) bundle.get("Account");
        return homeOwner.getUserName();
    }
    public void OnClickLogOut(View view){

        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);

    }
}
