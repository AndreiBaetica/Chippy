package com.example.janieamyot.chippy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    public void OnLoginClick(View view){
        EditText userName = findViewById(R.id.userNameField);
        EditText password = findViewById(R.id.passwordField);
        String user = userName.getText().toString();
        String pass = password.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        Account account = dbHandler.findAccountByUserName(user);
        if (account == null){
            userName.setText("");
            password.setText("");
            Toast.makeText(this, "Username not found in database", Toast.LENGTH_LONG).show();
        }
        else if(account.getPassword().equals(pass)) {
            Bundle bundle = new Bundle();
            if(account instanceof Admin){
                Intent intent = new Intent(getApplicationContext(), AdminWelcomePage.class);
                bundle.putSerializable("Account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if(account instanceof HomeOwner){
                Intent intent = new Intent(getApplicationContext(), HomeOwnerWelcomePage.class);
                bundle.putSerializable("Account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            if(account instanceof ServiceProvider){
                if(dbHandler.spProfileExists(account.getUserName())) {
                    Toast.makeText(this, "profile exists", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), ServiceProviderWelcomePage.class);
                    ServiceProvider sp = dbHandler.findServiceProvider(user);
                    bundle.putSerializable("Account", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), ServiceProviderProfile.class);
                    bundle.putSerializable("Account", account);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

        }
        else{
            userName.setText("");
            password.setText("");
            Toast.makeText(this, "Password is incorrect", Toast.LENGTH_LONG).show();
        }
    }
}
