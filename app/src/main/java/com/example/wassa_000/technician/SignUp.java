package com.example.wassa_000.technician;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.contentprovider.SharedMethods;
import com.example.wassa_000.technician.contentprovider.SharedPreferencesDataLoader;
import com.example.wassa_000.technician.factory.BeanFactory;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Spinner spGender;
    private EditText etName;
    private EditText etEmail;
    private EditText etMobile;
    private EditText etPassword;
    private Button btnSubmit;
    private EditText etConformPassword;
    private String[] cities;
    private Spinner spinnerCities;

    private LoginButton loginButton;
    private String city;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_up);

        spGender = (Spinner) findViewById(R.id.sp_gender);
        spinnerCities = (Spinner) findViewById(R.id.sp_cities);
        cities = new String[]{"Karachi", "Hyderabad", "Sukkur"};
        callbackManager = CallbackManager.Factory.create();

        //register callback manager
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        ArrayAdapter<String> servicesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        spinnerCities.setAdapter(servicesArrayAdapter);

        city = cities[0];
        // Gender Drop down elements
        List<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");


        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        spGender.setAdapter(genderAdapter);

        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city = cities[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initFields();

    }

    public void initFields() {
        etConformPassword = (EditText) findViewById(R.id.et_confirm_password);
        etEmail = (EditText) findViewById(R.id.et_email);
        etMobile = (EditText) findViewById(R.id.et_contact_number);
        etName = (EditText) findViewById(R.id.et_name_sign_up);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    public void onClick(View view) {

        Customer c = BeanFactory.getCustomer();

        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError("Enter name");
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("Enter password");
            return;
        }
        if (TextUtils.isEmpty(etConformPassword.getText().toString())) {
            etConformPassword.setError("Enter confirm password");
            return;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("Enter email");
            return;
        }

        if (TextUtils.isEmpty(etMobile.getText().toString())) {
            etMobile.setError("Enter Mobile number");
            return;
        }

        if (!SharedMethods.isValidEmailAddress(etEmail.getText().toString())) {
            etEmail.setError("Invalid email address");
            return;
        }
        if (!SharedMethods.validatePhoneNumber(etMobile.getText().toString())){
            etMobile.setError("Invalid phone number");
            return;
        }

        c.setName(etName.getText().toString());
        c.setMobile(etMobile.getText().toString());
        c.setEmail(etEmail.getText().toString());
        c.setPassword(etPassword.getText().toString());
        BeanFactory.setCustomer(c);
        SharedPreferencesDataLoader.storeCustomerDataToSharedPreferences(this);

        // try{
        //    Thread.sleep(3000);
        startActivity(new Intent(SignUp.this, MainActivity.class));
        finish();
        //}catch (InterruptedException e){}
    }
}
