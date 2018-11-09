package com.example.janieamyot.chippy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminServiceEditor extends AppCompatActivity {

    Category[] catList = {new Category("Plumbing"), new Category("Auto"),
            new Category("Electrical"), new Category("Yardwork"), new Category("Renovations"),
            new Category("Heating"), new Category("Housekeeping"), new Category("Caretaking"),
            new Category("Miscellaneous")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_service_editor);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    public void onClickCreate(View view) {
        //TODO: Validate fields
        EditText field = findViewById(R.id.name);
        String name = field.getText().toString();
        field = findViewById(R.id.rate);
        double rate = Double.valueOf(field.getText().toString());
        //TODO: Replace category with dropdown implementation
        field = findViewById(R.id.category);
        String category = field.getText().toString();
        createService(name, rate, category);
    }

    private void createService(String name, double rate, String categoryName) {
        MyDBHandler dbHandler = new MyDBHandler(this);

        //TODO; replace temporary category check with dropdown menu
        Category category = null;
        for (Category cat : catList) {
            if (cat.getLabel().equals(categoryName)) {
                category = cat;

            }
        }
        if (category == null) {
            return;
        }
        //end of temporary code

        Service service = new Service(rate, name, category);
        dbHandler.addService(service);
        dbHandler.close();
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
        Service newService = new Service(newRate, newName, newCategory);
        dbHandler.addService(newService);
        dbHandler.close();
    }
}
