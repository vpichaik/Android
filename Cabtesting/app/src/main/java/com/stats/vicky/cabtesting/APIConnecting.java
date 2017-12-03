package com.stats.vicky.cabtesting;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by allin on 02-12-2017.
 */

public class APIConnecting extends AsyncTask<String, Void, String> {


Login login=new Login();
EmpDetails emp=new EmpDetails();


    protected void onPreExecute() {
        login.progress();
    }


    protected String doInBackground(String... arg) {


        try {
            JSONObject postDataParams = new JSONObject();
            URL url;
            if(arg[0]=="LOGIN") {
                 url = new URL("http://106.51.155.80:90/isha/users/auth.php"); // here is your URL path
                postDataParams.put("username", arg[1]);
                postDataParams.put("password", arg[2]);
            }
            else if (arg[0]=="insert")
            {
                url = new URL("http://192.168.1.10/cab/TripDetails/TripBokking.php"); // here is your URL path
                postDataParams.put("UserName", arg[4]);
                postDataParams.put("starting_place", arg[1]);
                postDataParams.put("destination", arg[2]);
                postDataParams.put("trip_req_dt", arg[3]);


            }

            else
            {
                url = new URL(null);
            }

            Log.e("params", postDataParams.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line = "";


                while ((line = in.readLine()) != null) {
                    sb.append(arg[0]);
                    sb.append("^");
                    sb.append(line);
                    break;
                }

                in.close();

                return sb.toString();

            } else {
                return new String("false : " + responseCode);
            }
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }

    }

    @Override
    protected void onPostExecute(String result) {
        Log.i("val",result);
        String[] result_data=result.split("\\^");
        if (result_data[0].equals("LOGIN"))
        {
            if( result_data[1].equals("1"))
            {
                login.redirect(result);
            }

        }
        else if (result_data[0].equals("insert"))
        {
            if( result_data[1].equals("1"))
            {
                emp.redirect(result);
            }

        }

    }


    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}
