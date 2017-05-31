package com.example.wassa_000.technician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wassa_000.technician.adapter.PymentHistoryAdapter;
import com.example.wassa_000.technician.adapter.ServiceDetailsAdapter;
import com.example.wassa_000.technician.beans.Service;
import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.beans.Feedback;
import com.example.wassa_000.technician.beans.PaymentHistory;
import com.example.wassa_000.technician.factory.BeanFactory;

import java.util.List;

public class MyAccount extends AppCompatActivity {

    private TextView tvEmail;
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvGender;
    private TextView tvCity;
    private TextView tvMobileNumber;
    private Customer customer;
    private List<Feedback> feedbacks;
    private List<Service> service;
    private List<PaymentHistory> paymentHistories;

    private ListView listPayDetails;
    private ListView listServicesDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvName = (TextView) findViewById(R.id.tvName);
//        tvAddress = (TextView)findViewById(R.id.tvAddress);
//        tvGender = (TextView)findViewById(R.id.tvGender);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvMobileNumber = (TextView) findViewById(R.id.tvMobileNumber);


        listPayDetails = (ListView) findViewById(R.id.listPayDetails);
        listServicesDetails = (ListView) findViewById(R.id.listServicesDetails);
        customer = BeanFactory.getCustomer();

        tvEmail.setText(customer.getEmail());
        tvName.setText(customer.getName());
//        tvAddress.setText(customer.getAddress());
//        for (Map.Entry e : SharedFields.cities) {
        tvCity.setText(customer.getCity());
//        }
        tvMobileNumber.setText(customer.getMobile());
//        tvGender.setText(customer.getGender());

        feedbacks = BeanFactory.getFeedbacks();
        service = BeanFactory.getService();
        paymentHistories = BeanFactory.getPaymentHistories();

        List<PaymentHistory> paymentHistories = BeanFactory.getPaymentHistories();
        Log.i("paymentHistories","paymentHistories size="+paymentHistories.size());

        if (paymentHistories == null || paymentHistories.size() <= 0) {
            findViewById(R.id.tvPaymentHistory).setVisibility(View.GONE);
            listPayDetails.setVisibility(View.GONE);
        } else {
            PymentHistoryAdapter adapter = new PymentHistoryAdapter(MyAccount.this, paymentHistories);

            listPayDetails.setAdapter(adapter);
        }

        List<Service> services = BeanFactory.getService();

        Log.i("services","services size="+services.size());
        if (services == null || services.size()<=0){
            findViewById(R.id.tvServiceDetails).setVisibility(View.GONE);
            listServicesDetails.setVisibility(View.GONE);
        }
        else
        {
            ServiceDetailsAdapter adapter = new ServiceDetailsAdapter(MyAccount.this,BeanFactory.getService());
            listServicesDetails.setAdapter(adapter);
        }

    }
}
