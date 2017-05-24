package com.example.wassa_000.technician.serverconnetors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.wassa_000.technician.ServiceFragment;
import com.example.wassa_000.technician.builder.ServiceFormHandler;
import com.example.wassa_000.technician.contentprovider.SharedFields;
import com.example.wassa_000.technician.controller.UiController;

import org.json.JSONObject;

/**
 * Created by Ghulam Ali on 5/20/2017.
 */
public class ServiceDataSender extends AsyncTask<String, Void, String> {

    private ServiceFragment mContext;
    public ServiceDataSender(ServiceFragment mContext){
        this.mContext = mContext;
    }
    private ProgressDialog progressDialog2;
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
        progressDialog2.dismiss();
        try {
            JSONObject object = new JSONObject(result);

            if (object.getString("req_status").equalsIgnoreCase("success")) {
                UiController.showDialog("Service requested successfully", mContext.getActivity());

                mContext.address.setText("");
                mContext.remarks.setText("");
                mContext.phone.setText("");
            } else
                UiController.showDialog("Service request error", mContext.getActivity());
        }catch (Exception e){}
    }

    @Override
    protected void onPreExecute() {
        progressDialog2 = ProgressDialog.show(mContext.getContext(), "", "Loading");
    }
}
