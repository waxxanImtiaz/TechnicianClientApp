package com.example.wassa_000.technician.serverconnetors;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.wassa_000.technician.builder.ComplainFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;

/**
 * Created by Admin on 5/19/2017.
 */

public class ComplainService extends AsyncTask<String, Void, String>  {

    private Context mContext;
    public ComplainService(Context mContext){
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

         if (result.equalsIgnoreCase("true")){
             Toast.makeText(mContext, "Complain submitted succesffully", Toast.LENGTH_SHORT).show();
         }else
             Toast.makeText(mContext, "Complain not submitted", Toast.LENGTH_SHORT).show();
        }

    @Override
    protected void onPreExecute() {}
}
