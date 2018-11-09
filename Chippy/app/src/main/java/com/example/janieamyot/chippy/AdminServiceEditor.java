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

    public void onClickCreate(View view){
        EditText field = findViewById(R.id.name);
        String name = field.getText().toString();
        field = findViewById(R.id.rate);
        double rate = Double.valueOf(field.getText().toString());
        //TODO: Replace category with dropdown implementation
        field = findViewById(R.id.category);
        String category = field.getText().toString();
        createService(name, rate, category);
    }

    private void createService(String name, double rate, String categoryName){
        MyDBHandler dbHandler = new MyDBHandler(this);

        //TODO; replace temporary category check with dropdown menu
        Category category = null;
        for (Category cat : catList) {
            if (cat.getLabel().equals(categoryName)) {
                category = cat;

            }
        }
        if (category == null) {
            Toast.makeText(getApplicationContext(), "ERROR ERROR ERROR ERROR", Toast.LENGTH_LONG).show();
            return;
        }
        //end of temporary code

        Service service = new Service(rate, name, category);
        dbHandler.addService(service);
        dbHandler.close();
    }

}
