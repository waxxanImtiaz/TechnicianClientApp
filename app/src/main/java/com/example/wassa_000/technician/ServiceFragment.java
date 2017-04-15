package com.example.wassa_000.technician;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class ServiceFragment extends Fragment {

    private Spinner spinnerServices;
    private String[] services;

    public ServiceFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        spinnerServices = (Spinner)view.findViewById(R.id.sp_services);
        services = new String[] {"Mobile Repairig","Plumber","Texi"};

        ArrayAdapter<String> servicesArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, services);
        spinnerServices.setAdapter(servicesArrayAdapter);

        return view;
    }

}
