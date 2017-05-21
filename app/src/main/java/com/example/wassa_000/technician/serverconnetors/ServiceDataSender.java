package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.wassa_000.technician.builder.ServiceFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;

/**
 * Created by Ghulam Ali on 5/20/2017.
 */
public class ServiceDataSender extends AsyncTask<String, Void, String> {

    private Activity mContext;
    public ServiceDataSender(Activity mContext){
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... params) {

        ServiceFormHandler handler = new ServiceFormHandler(mContext);
        handler.setUrl(SharedFields.userLink);
        handler.setRequestMethod("POST");

        return handler.setFormParametersAndConnect(params[0]
        ,params[1],params[2],params[3],params[4]);
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equalsIgnoreCase("success")){
            UiController.showDialog("Service requested successfully",mContext);
        }else
            UiController.showDialog("Service request error",mContext);
    }

    @Override
    protected void onPreExecute() {}
}
