package com.example.wassa_000.technician;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FeedBackFragment extends Fragment {
    private Spinner spinnerCities;
    private String[] cities;
    public FeedBackFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back, container, false);
        spinnerCities = (Spinner)view.findViewById(R.id.sp_cities);
        cities = new String[] {"Karachi","Hyderabad","Sukkur"};

        ArrayAdapter<String> servicesArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cities);
        spinnerCities.setAdapter(servicesArrayAdapter);

        // Inflate the layout for this fragment
        return view;
    }





}
