package com.example.wassa_000.technician;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import com.example.wassa_000.technician.controller.UiController;
import com.example.wassa_000.technician.serverconnetors.FeedBackService;


public class FeedBackFragment extends Fragment {
    private Spinner spinnerCities;
    private String[] cities;

    private String city;
    private EditText name;
    private EditText email;
    private EditText phone;
    private MultiAutoCompleteTextView remarks;
    private Button submit;
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


        //initialize fields
        name = (EditText) view.findViewById(R.id.et_name);
        phone = (EditText)view.findViewById(R.id.et_phone);
        email = (EditText)view.findViewById(R.id.et_email);
        submit = (Button) view.findViewById(R.id.btnSubmit);
        remarks = (MultiAutoCompleteTextView) view.findViewById(R.id.remarks);

        ArrayAdapter<String> servicesArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cities);
        spinnerCities.setAdapter(servicesArrayAdapter);

        city = cities[0];
        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city = String.valueOf(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                work();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }



    public void work(){

        String name = this.name.getText().toString();
        String email = this.email.getText().toString();
        String phone = this.phone.getText().toString();
        String remarks = this.remarks.getText().toString();

        if (name.isEmpty()) {
            this.name.setError("Please enter name");
            return;
        }
        if (phone.isEmpty()) {
            this.phone.setError("Please enter phone number");
            return;
        }
        if (email.isEmpty()) {
            this.email.setError("Please enter email address");
            return;
        }
        if (remarks.isEmpty()) {
            UiController.showDialog("Please enter your message",getActivity());
            return;
        }

        if (!UiController.isNetworkAvailable(getContext()))
        {
            UiController.showDialog("Please connect to network",getActivity());
            return;
        }

        FeedBackService service = new FeedBackService(getContext());
        service.execute(name,phone,city,remarks,email);
    }
    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);


    }

}
