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

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

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
    }



    private ArrayList<String> displayAvailabilities() {

        ArrayList<String> listAvailabilities = new ArrayList<String>();

        ServiceProvider account = (ServiceProvider) bundle.get("Account");
        JSONObject week = null;
        try {
            week = new JSONObject(account.getAvailabilities());

        ArrayList<Integer[]> monday = sequentialSplit(week.getJSONArray("monday"));
        String mo = "Monday:\n    ";
        for (Integer[] array : monday) {
            int start = array[0];
            int end = array[array.length];
            mo = mo.concat(start + ":00-" + end + ":00\n    ");
        }
        listAvailabilities.add(mo);

        ArrayList<Integer[]> tuesday = sequentialSplit(week.getJSONArray("tuesday"));
            String tu = "Tuesday:\n    ";
            for (Integer[] array : tuesday) {
                int start = array[0];
                int end = array[array.length];
                tu = tu.concat(start + ":00-" + end + ":00\n    ");
            }
            listAvailabilities.add(tu);

        ArrayList<Integer[]> wednesday = sequentialSplit(week.getJSONArray("wednesday"));
            String we = "Wednesday:\n    ";
            for (Integer[] array : wednesday) {
                int start = array[0];
                int end = array[array.length];
                we = we.concat(start + ":00-" + end + ":00\n    ");
            }
            listAvailabilities.add(we);

        ArrayList<Integer[]> thursday = sequentialSplit(week.getJSONArray("thursday"));
            String th = "Thursday:\n    ";
            for (Integer[] array : thursday) {
                int start = array[0];
                int end = array[array.length];
                th = th.concat(start + ":00-" + end + ":00\n    ");
            }
            listAvailabilities.add(th);

        ArrayList<Integer[]> friday = sequentialSplit(week.getJSONArray("friday"));
            String fr = "Friday:\n    ";
            for (Integer[] array : friday) {
                int start = array[0];
                int end = array[array.length];
                fr = fr.concat(start + ":00-" + end + ":00\n    ");
            }
            listAvailabilities.add(fr);

        ArrayList<Integer[]> saturday = sequentialSplit(week.getJSONArray("saturday"));
            String sa = "Saturday:\n    ";
            for (Integer[] array : saturday) {
                int start = array[0];
                int end = array[array.length];
                sa = sa.concat(start + ":00-" + end + ":00\n    ");
            }
            listAvailabilities.add(sa);

        ArrayList<Integer[]> sunday = sequentialSplit(week.getJSONArray("sunday"));
            String su = "Sunday:\n    ";
            for (Integer[] array : sunday) {
                int start = array[0];
                int end = array[array.length];
                su = su.concat(start + ":00-" + end + ":00\n    ");
            }
            listAvailabilities.add(su);

        } catch (JSONException e) {
            //TODO
        }

        return listAvailabilities;
    }

    private ArrayList<Integer[]> sequentialSplit(JSONArray jsonArray) throws JSONException {
        ArrayList<Integer> parsingList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            parsingList.add(jsonArray.getInt(i));
        }

        Integer[] dayArray = (Integer[]) parsingList.toArray();

        ArrayList<Integer[]> mainList = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        temp.add(dayArray[0]);

        for (int i = 0; i < dayArray.length -1; i++) {
            if (dayArray[i+1] == dayArray[i] + 1) {
                temp.add(dayArray[i+1]);
            } else {
                Integer[] tempArray = (Integer[]) temp.toArray();
                mainList.add(tempArray);
                temp = new ArrayList<>();
                temp.add(dayArray[i+1]);
            }
        }
        Integer[] tempArray = (Integer[]) temp.toArray();
        mainList.add(tempArray);
        return mainList;
    }
}
