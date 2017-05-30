package com.example.wassa_000.technician;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wassa_000.technician.adapter.PymentHistoryAdapter;
import com.example.wassa_000.technician.beans.Complain;
import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.beans.Feedback;
import com.example.wassa_000.technician.beans.PaymentHistory;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.factory.BeanFactory;
import com.example.wassa_000.technician.serverconnetors.UserInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyAccount extends AppCompatActivity {

    private TextView tvEmail;
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvGender;
    private TextView tvCity;
    private TextView tvMobileNumber;
    private Customer customer;
    private List<Feedback> feedbacks;
    private List<Complain> complain;
    private List<PaymentHistory> paymentHistories;

    private ListView listPayDetails;

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
        complain = BeanFactory.getComplain();
        paymentHistories = BeanFactory.getPaymentHistories();

        List<PaymentHistory> paymentHistories = BeanFactory.getPaymentHistories();

        if (paymentHistories == null || paymentHistories.size() <= 0) {
            findViewById(R.id.tvPaymentHistory).setVisibility(View.GONE);
            listPayDetails.setVisibility(View.GONE);
        } else {
            PymentHistoryAdapter adapter = new PymentHistoryAdapter(MyAccount.this, paymentHistories);

            listPayDetails.setAdapter(adapter);
        }

    }
}
