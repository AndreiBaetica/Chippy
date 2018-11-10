package com.example.janieamyot.chippy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AdminServiceEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public String categorySel;

    Category[] catList = {new Category("Plumbing"), new Category("Auto"),
            new Category("Electrical"), new Category("Yard-work"), new Category("Renovations"),
            new Category("Heating"), new Category("Housekeeping"), new Category("Care-taking"),
            new Category("Miscellaneous")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_service_editor);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        //Create Spinner
        Spinner spinnerCat = findViewById(R.id.category_drop);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.CategoryArr, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(adapter);
        spinnerCat.setOnItemSelectedListener(this);
    }

    public void onClickCreate(View view) {
        //TODO: Validate fields
        EditText field = findViewById(R.id.name);
        String name = field.getText().toString();
        field = findViewById(R.id.rate);
        double rate = Double.valueOf(field.getText().toString());


        createService(name, rate);

        Intent intent = new Intent(getApplicationContext(), AdminWelcomePage.class);
        startActivity(intent);
    }

    private void createService(String name, double rate) {
        MyDBHandler dbHandler = new MyDBHandler(this);


        Service service = new Service(rate, name, categorySel);
        dbHandler.addService(service);
        dbHandler.close();
    }

    public void onClickCancel(){
        Intent intent = new Intent(getApplicationContext(), AdminWelcomePage.class);
        startActivity(intent);
    }

    public void onClickDelete() {
        //TODO: Need to find a way to intuitively select a service.
        //deleteService(service);
    }

    private void deleteService(Service service) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.deleteService(service.getName());
        dbHandler.close();
    }

    public void onClickEdit(){
        //TODO: Get new name, rate, category. EditText defaults should be current values.
        //TODO: Need to find a way to intuitively select a service.
        //editService(service);
    }

    private void editService(Service oldService, String newName, double newRate, Category newCategory) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.deleteService(oldService.getName());
        Service newService = new Service(newRate, newName, categorySel);
        dbHandler.addService(newService);
        dbHandler.close();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String catSelected = parent.getItemAtPosition(position).toString();
        categorySel = catSelected;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
