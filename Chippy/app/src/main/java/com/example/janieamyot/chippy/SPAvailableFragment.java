package com.example.janieamyot.chippy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SPAvailableFragment extends Fragment {

    Bundle bundle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_sp_available, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        bundle = intent.getExtras();


        //UI changes here
        //Use getActivity() when referring to ServiceProviderWelcomePage
        //ex. instead of this use getActivity()

        ListView availableList = getActivity().findViewById(R.id.spAvailableList);
        final ArrayList<String> listAvailabilities = displayAvailabilities();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, listAvailabilities);
        availableList.setAdapter(adapter);
        final MyDBHandler dbHandler = new MyDBHandler(getActivity());

        availableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: ADD LOGIC HERE

            }
        });
    }

    private ArrayList<String> displayAvailabilities(){
        // TODO: ADD LOGIC HERE
        ArrayList<String> listAvailabilities = new ArrayList<String>();

        return listAvailabilities;
    }
}
