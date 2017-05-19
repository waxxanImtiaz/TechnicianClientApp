package com.example.wassa_000.technician.contentprovider;

import android.content.SharedPreferences;

/**
 * Created by Ghulam Ali on 4/15/2017.
 */
public class SharedFields {
    public static SharedPreferences.Editor editor;
    public static final String serverLink = "http://serveasy.pk/andriod_services/";
    public static final String userLink = serverLink.concat("users.php");



    public static final String DEBUG_MESSAGE = "server_error";
}
