package com.example.wassa_000.technician.serverconnetors;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.wassa_000.technician.builder.ServiceFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;

/**
 * Created by Ghulam Ali on 5/20/2017.
 */
public class ServiceDataSender extends AsyncTask<String, Void, String> {

    private Context mContext;
    public ServiceDataSender(Context mContext){
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... params) {

        ServiceFormHandler handler = new ServiceFormHandler(mContext);
        handler.setUrl(SharedFields.userLink);
        handler.setRequestMethod("POST");

        return handler.setFormParametersAndConnect(SharedFields.userId,params[0]
        ,params[1],params[2],params[3]);
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equalsIgnoreCase("success")){
            Toast.makeText(mContext, "Service requested succesffully", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(mContext, "Service request error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {}
}
