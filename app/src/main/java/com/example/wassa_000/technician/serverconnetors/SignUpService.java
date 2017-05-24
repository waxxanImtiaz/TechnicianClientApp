package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.wassa_000.technician.MainActivity;
import com.example.wassa_000.technician.SignUp;
import com.example.wassa_000.technician.builder.ServiceFormHandler;
import com.example.wassa_000.technician.builder.SignUpFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;

/**
 * Created by Admin on 5/24/2017.
 */

public class SignUpService extends AsyncTask<String, Void, String> {


    private Activity mContext;

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

        if (result.equalsIgnoreCase("success")) {
            UiController.showDialog("Signed Up successfully", mContext);
            mContext.startActivity(new Intent(mContext, MainActivity.class));
            mContext.finish();
        } else
            UiController.showDialog("Sign up error", mContext);
    }

    @Override
    protected void onPreExecute() {
    }
}
