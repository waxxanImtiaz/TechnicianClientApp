package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.wassa_000.technician.LoginActivity;
import com.example.wassa_000.technician.MyAccount;
import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.beans.Feedback;
import com.example.wassa_000.technician.builder.LoginFormHandler;
import com.example.wassa_000.technician.builder.UserInfoHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;
import com.example.wassa_000.technician.factory.BeanFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 5/29/2017.
 */

public class UserInfoService extends AsyncTask<Void, Void, String> {

    private MyAccount mContext;
    private ProgressDialog progressDialog2;

    public UserInfoService(final MyAccount context) {
        this.mContext = context;

    }


    @Override
    protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.

        try {

            UserInfoHandler handler = new UserInfoHandler(mContext);
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


    @Override
    protected void onPostExecute(final String success) {

        if (progressDialog2 != null)
            progressDialog2.dismiss();

        if (success == null) {
            Toast.makeText(mContext, "Server error", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            if (success != null && !success.isEmpty()) {
                JSONObject object = new JSONObject(success);
                JSONArray array = object.getJSONArray("all_feedback");
                List<Feedback> fb = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    Feedback f = new Feedback();

                    JSONObject ob = array.getJSONObject(i);
                    f.setDate(ob.getString("submitted_at"));
                    f.setFeedback(ob.getString("feedback"));
                    f.setRecomended(ob.getString("recommend"));
                    fb.add(f);
                }
                BeanFactory.setFeedbacks(fb);
                Log.v("data", success + ",,");
                Toast.makeText(mContext, "Data fetched succesfully,size="+fb.size(), Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
        }


    }


}


