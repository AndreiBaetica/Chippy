package com.example.janieamyot.chippy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

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

    public void createService(String name, double rate, Category category){
        MyDBHandler dbHandler = new MyDBHandler(this);

        //ToDo; replace temporary category check with dropdown menu
        boolean categoryExists = false;
        for (Category cat : catList) {
            if (cat.equals(category)) {
                categoryExists = true;
            }
        }
        if (!categoryExists) {
            return;
        }
        //end of temporary code

        Service service = new Service(name, rate, category);
        dbHandler.addService(service);
        dbHandler.close();
    }

}
