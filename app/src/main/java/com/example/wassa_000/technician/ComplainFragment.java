package com.example.wassa_000.technician;

/**
 * Created by wassa_000 on 3/23/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;


public class ComplainFragment extends Fragment{

    private MultiAutoCompleteTextView tvComplain;
    private Button btnSubmit;
    public ComplainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_complain, container, false);

        tvComplain = (MultiAutoCompleteTextView)v.findViewById(R.id.complain);
        btnSubmit = (Button)v.findViewById(R.id.btn_submit);
        // Inflate the layout for this fragment

//        btnSubmit.setOnClickListener(new ButtonOnClickListener());
        return v;
    }

    private class ButtonOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

        }
    }
}