package com.example.janieamyot.chippy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OnLoginClick(View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
    public void OnCreateAccountClick(View view){
        Intent intent = new Intent(getApplicationContext(), CreateNewAccount.class);
        startActivity(intent);
    }
}
