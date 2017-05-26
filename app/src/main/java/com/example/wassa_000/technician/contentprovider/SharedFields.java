package com.example.wassa_000.technician.contentprovider;

import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ghulam Ali on 4/15/2017.
 */
public class SharedFields {
    public static SharedPreferences.Editor editor;
    public static final String serverLink = "http://serveasy.pk/andriod_services/";
    public static final String userLink = serverLink.concat("users.php");

    public static String userId = "2";

    public static final String DEBUG_MESSAGE = "server_error";

    //PERMISSION CODES

    public static final int INTERNET_PERMISSION = 10;
    public static final int NETWORK_STATE = 120;

    //Facebook login fields

    public static final int REQUEST_CODE_FB = 12;
    public static final int RESULT_CODE_FB = 26;

    public static final String fbId = "id";

    public static Map<Integer,String> areas = new HashMap<>();
    public static Map<Integer,String> cities = new HashMap<>();
    public static Map<Integer,String> services = new HashMap<>();

}
