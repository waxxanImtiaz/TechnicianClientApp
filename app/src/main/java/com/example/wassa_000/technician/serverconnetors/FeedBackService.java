package com.example.wassa_000.technician.serverconnetors;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.wassa_000.technician.builder.ComplainFormHandler;
import com.example.wassa_000.technician.builder.FeedBackFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;

/**
 * Created by Ghulam Ali on 5/20/2017.
 */
public class FeedBackService extends AsyncTask<String, Void, String> {

    private Context mContext;
    public FeedBackService(Context mContext){
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... params) {

        FeedBackFormHandler handler = new FeedBackFormHandler(mContext);
        handler.setUrl(SharedFields.userLink);
        handler.setRequestMethod("POST");

        return handler.setFormParametersAndConnect(params[0],params[1],
                params[2],params[3],params[4]);
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equalsIgnoreCase("success")){
            Toast.makeText(mContext, "Feedback submitted succesffully", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(mContext, "Feedback not submitted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {}
}
