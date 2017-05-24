package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.wassa_000.technician.FeedBackFragment;
import com.example.wassa_000.technician.builder.ComplainFormHandler;
import com.example.wassa_000.technician.builder.FeedBackFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;

/**
 * Created by Ghulam Ali on 5/20/2017.
 */
public class FeedBackService extends AsyncTask<String, Void, String> {

    private FeedBackFragment mContext;

    public FeedBackService(FeedBackFragment mContext) {
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... params) {

        FeedBackFormHandler handler = new FeedBackFormHandler(mContext.getContext());
        handler.setUrl(SharedFields.userLink);
        handler.setRequestMethod("POST");

        return handler.setFormParametersAndConnect(params[0], params[1],
                params[2], params[3], params[4],params[5]);
    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equalsIgnoreCase("success")) {

            UiController.showDialog("Feedback submitted succesffully", mContext.getActivity());

            //mContext.email.setText("");
            mContext.name.setText("");
            mContext.remarks.setText("");
            mContext.phone.setText("");

        } else
            UiController.showDialog("Feedback not submitted", mContext.getActivity());

    }

    @Override
    protected void onPreExecute() {
    }
}
