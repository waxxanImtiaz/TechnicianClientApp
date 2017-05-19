package com.example.wassa_000.technician.builder;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 5/19/2017.
 */

public class ComplainFormHandler extends ServerConnectionBuilder {

    //call first
    public void setUrl(String link){
        this.link  = link;
    }
    //call second
    public void setRequestMethod(String requestMethod){
        this.reqMethod = requestMethod;
    }

    public void  setFormParameters(String userId,String complain){
        try {
            Map<String,String> arguments = new HashMap<>();
            arguments.put("userid", userId);
            arguments.put("complain_text", complain);
            arguments.put("complain", "true");
            StringBuilder sj = new StringBuilder();
            for(Map.Entry<String,String> entry : arguments.entrySet()) {
                sj.append(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&");
            }
            byte[] out = sj.toString().getBytes();

            http.setFixedLengthStreamingMode(out.length);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            http.connect();
            try
            {
                OutputStream os = urlConnection.getOutputStream();
                os.write(out);
            }
            catch (Exception e)
            {

            }

        }catch (Exception e){}
    }
}
