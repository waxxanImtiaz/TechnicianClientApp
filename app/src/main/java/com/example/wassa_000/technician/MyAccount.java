package com.example.wassa_000.technician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.factory.BeanFactory;

import java.util.Map;

public class MyAccount extends AppCompatActivity {

    private TextView tvEmail;
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvGender;
    private TextView tvCity;
    private TextView tvMobileNumber;
    private Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvName = (TextView)findViewById(R.id.tvName);
//        tvAddress = (TextView)findViewById(R.id.tvAddress);
//        tvGender = (TextView)findViewById(R.id.tvGender);
        tvCity = (TextView)findViewById(R.id.tvCity);
        tvMobileNumber = (TextView)findViewById(R.id.tvMobileNumber);

        customer = BeanFactory.getCustomer();

        tvEmail.setText(customer.getEmail());
        tvName.setText(customer.getName());
//        tvAddress.setText(customer.getAddress());
//        for (Map.Entry e : SharedFields.cities) {
            tvCity.setText(customer.getCity());
//        }
        tvMobileNumber.setText(customer.getMobile());
//        tvGender.setText(customer.getGender());
    }
}
