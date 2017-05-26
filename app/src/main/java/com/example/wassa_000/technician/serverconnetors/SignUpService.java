package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.wassa_000.technician.MainActivity;
import com.example.wassa_000.technician.SignUp;
import com.example.wassa_000.technician.builder.ServiceFormHandler;
import com.example.wassa_000.technician.builder.SignUpFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.contentprovider.SharedMethods;
import com.example.wassa_000.technician.contentprovider.SharedPreferencesDataLoader;
import com.example.wassa_000.technician.controller.UiController;
import com.example.wassa_000.technician.factory.BeanFactory;

import org.json.JSONObject;

/**
 * Created by Admin on 5/24/2017.
 */

public class SignUpService extends AsyncTask<String, Void, String> {


    private Activity mContext;
    private ProgressDialog progressDialog2;
    public SignUpService(Activity mContext) {
        this.mContext = mContext;
    }


    @Override
    protected String doInBackground(String... params) {

        SignUpFormHandler handler = new SignUpFormHandler(mContext);
        handler.setUrl(SharedFields.userLink);
        handler.setRequestMethod("POST");

        return handler.setFormParametersAndConnect();
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject object = new JSONObject(result);

            progressDialog2.dismiss();

            if (object.getString("req_status").equalsIgnoreCase("success")) {
                Toast.makeText(mContext, "Signed Up successfully", Toast.LENGTH_SHORT).show();
                SharedPreferencesDataLoader.storeCustomerDataToSharedPreferences(mContext);
                mContext.startActivity(new Intent(mContext, MainActivity.class));
                mContext.finish();
                SharedMethods.sendEmail(BeanFactory.getCustomer().getEmail());
            } else {
                String req = object.getString("already_registered");

                if (req != null || (TextUtils.isEmpty(req) && req.equalsIgnoreCase("true"))) {
                    UiController.showDialog("You are already registered", mContext);
                    return;
                }
                Toast.makeText(mContext, "Sign up error", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onPreExecute() {
        progressDialog2 = ProgressDialog.show(mContext, "", "Loading");
    }
}
