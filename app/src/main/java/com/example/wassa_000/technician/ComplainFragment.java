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

import com.example.wassa_000.technician.builder.ComplainFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;
import com.example.wassa_000.technician.serverconnetors.ComplainService;


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
        btnSubmit = (Button)v.findViewById(R.id.btnSubmit);

        // Inflate the layout for this fragment

        return v;
    }

    private class ButtonOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){

            String complain = tvComplain.getText().toString();

            if (complain.isEmpty()){
                UiController.showDialog("Please enter complain",getActivity());
            }
            else{
                if (!UiController.isNetworkAvailable(getContext()))
                {
                    UiController.showDialog("Please connect to network",getActivity());
                    return;
                }
                ComplainService service = new ComplainService(getContext());
                service.execute(complain);
            }
        }
    }
    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        btnSubmit.setOnClickListener(new ButtonOnClickListener());

    }

}