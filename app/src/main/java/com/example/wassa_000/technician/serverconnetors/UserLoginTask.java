package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wassa_000.technician.LoginActivity;
import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.builder.LoginFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;
import com.example.wassa_000.technician.factory.BeanFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 5/25/2017.
 */
public class UserLoginTask extends AsyncTask<Void, Void, String> {

    private LoginActivity mContext;
    private ProgressDialog progressDialog2;

    public UserLoginTask(final LoginActivity context) {
        this.mContext = context;

    }


    @Override
    protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.

        try {

            LoginFormHandler handler = new LoginFormHandler(mContext);
            handler.setUrl(SharedFields.userLink);
            handler.setRequestMethod("POST");

            return handler.setFormParametersAndConnect();
        } catch (Exception e) {
            return "";
        }


    }

    @Override
    protected void onPreExecute() {
        if (!((Activity) mContext).isFinishing()) {
            //show dialog
            progressDialog2 = ProgressDialog.show(mContext, "", "Loading");
        }


    }

    private boolean isMessageAppeard = false;
    @Override
    protected void onPostExecute(final String success) {

        if (progressDialog2 != null)
            progressDialog2.dismiss();

        try {
            JSONArray array = new JSONArray(success);

            for(int n = 0; n < array.length(); n++)
            {
                JSONObject object = array.getJSONObject(n);
                showMessage(object);
                // do some stuff....
            }

        } catch (JSONException e) {
            Log.v("jsonException", "msg:" + e.toString());
            try {
                Log.v("msg",  e.toString());
                showMessage(new JSONObject(success));
            }catch (JSONException ex){
                //Toast.makeText(mContext, String.valueOf(success), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.v("exception", "msg:" + e.toString());
        }
    }
    public void showMessage(JSONObject object)throws JSONException{

        if (!isMessageAppeard) {
            isMessageAppeard = true;
            if (object.getString("id") != null && !object.getString("id").isEmpty()) {
                Toast.makeText(mContext, "You are logged in successfully", Toast.LENGTH_SHORT).show();
                Customer c = BeanFactory.getCustomer();
                c.setId(object.getString("id"));
                BeanFactory.setCustomer(c);


            } else
                UiController.showDialog("Info:" + object.toString(), mContext);
        }
    }

}


