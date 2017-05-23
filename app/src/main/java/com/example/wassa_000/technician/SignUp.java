package com.example.wassa_000.technician;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.contentprovider.SharedMethods;
import com.example.wassa_000.technician.contentprovider.SharedPreferencesDataLoader;
import com.example.wassa_000.technician.factory.BeanFactory;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    private AccessToken accessToken;
    CallbackManager callbackManager;
    private GraphRequest request;
    private String id;
    private String name;
    private String email;
    private String gender;
    private String birthday;
    private Profile profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        setContentView(R.layout.activity_sign_up);

        spGender = (Spinner) findViewById(R.id.sp_gender);
        spinnerCities = (Spinner) findViewById(R.id.sp_cities);
        cities = new String[]{"Karachi", "Hyderabad", "Sukkur"};
        callbackManager = CallbackManager.Factory.create();

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


    public void intiRequest(){
        request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            name = object.getString("name");
                            Log.i("firstName:","name:"+name);
                        }catch (Exception e){}

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,installed,picture");
        request.setParameters(parameters);
        request.executeAsync();
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
        accessToken = AccessToken.getCurrentAccessToken();
        //intiRequest();
        loginButton.setReadPermissions(Arrays.asList( "email"));


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


            }

            @Override
            public void onCancel() {
                // App code

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("fb_exception","excpetion:"+exception.getMessage());
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
        intiRequest();
        Toast.makeText(this, "You are logged in", Toast.LENGTH_SHORT).show();
    }
    private void startMainActivity(){
        Intent i = new Intent(SignUp.this,MainActivity.class);
        startActivity(i);
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

        startActivity(new Intent(SignUp.this, MainActivity.class));
        finish();

    }

    public void setDataAfterLogin(){

    }
}
