package com.example.wassa_000.technician.contentprovider;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wassa_000.technician.beans.Customer;
import com.example.wassa_000.technician.factory.BeanFactory;

import java.util.prefs.PreferencesFactory;

/**
 * Created by Ghulam Ali on 4/15/2017.
 */
public class SharedPreferencesDataLoader {

    public static boolean getCustomer(Context context){
        SharedPreferences prefs = com.example.wassa_000.technician.contentprovider.PreferencesFactory.getSharedPreferences(context);
        try{

            Customer customer = BeanFactory.getCustomer();

            customer.setName(prefs.getString("name",""));
            customer.setEmail(prefs.getString("username",""));
            customer.setPassword(prefs.getString("password",""));
//            customer.setAddress(prefs.getString("address",""));
            customer.setGender(prefs.getString("gender",""));
            customer.setMobile(prefs.getString("mobile",""));
            return true;
        }
        catch (NullPointerException e)
        {
            return false;
        }
    }
    public static void storeCustomerDataToSharedPreferences(Context context){
        SharedFields.editor = com.example.wassa_000.technician.contentprovider.PreferencesFactory.getEditor(context);
        try {
            Customer customer = BeanFactory.getCustomer();
            SharedFields.editor.putString("username", customer.getEmail());
            SharedFields.editor.putString("password", customer.getPassword());
            SharedFields.editor.putString("name", customer.getName());
            SharedFields.editor.putString("mobile", customer.getMobile());

            SharedFields.editor.putString("fbId", customer.getFbId());
            SharedFields.editor.putString("address", customer.getAddress());
            SharedFields.editor.putString("gender", customer.getGender());
            SharedFields.editor.commit();
        }catch (NullPointerException e){}
    }
}
