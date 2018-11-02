package com.example.janieamyot.chippy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class AdminWelcomePage extends AppCompatActivity {

    Bundle bundle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                }
        );

        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        TextView welcome = findViewById(R.id.welcomeMessage);
        String welcomeMessage = "Welcome " + extractAccount();
        welcome.setText(welcomeMessage);

        ListView accountList = findViewById(R.id.accountList);
        final ArrayList<String> list = displayAccounts();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        accountList.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String> displayAccounts(){
        String accounts = "";

        Integer counter = 1;
        MyDBHandler dbHandler = new MyDBHandler(this);
        ArrayList<Account> accountList = dbHandler.findAllAccounts();
        ArrayList<String> listAccounts = new ArrayList<String>();

        for(Account account : accountList) {
            accounts = (counter.toString() + " " + account.toString());
            if (account instanceof Admin) {
                accounts = accounts.concat(" Type: Admin");
            } if (account instanceof ServiceProvider) {
                accounts = accounts.concat(" Type: Service provider");
            } if (account instanceof HomeOwner) {
                accounts = accounts.concat(" Type: Homeowner");
            }
            listAccounts.add(accounts);
            counter ++;
        }

        dbHandler.close();
        return listAccounts;
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
