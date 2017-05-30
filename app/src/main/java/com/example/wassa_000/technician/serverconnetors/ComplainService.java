package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.wassa_000.technician.builder.ComplainFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;

/**
 * Created by Admin on 5/19/2017.
 */

public class ComplainService extends AsyncTask<String, Void, String>  {

    private Activity mContext;
    private ProgressDialog progressDialog2;
    public ComplainService(Activity mContext){
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... params) {

        ComplainFormHandler handler = new ComplainFormHandler(mContext);
        handler.setUrl(SharedFields.userLink);
        handler.setRequestMethod("POST");

        return handler.setFormParametersAndConnect("1",params[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog2.dismiss();
         if (result.equalsIgnoreCase("success")){
             UiController.showDialog("Service submitted succesffully",mContext);
         }else

             UiController.showDialog( "Service not submitted",mContext);
        }

    @Override
    protected void onPreExecute() {
        progressDialog2 = ProgressDialog.show(mContext, "", "Loading");
    }
}
