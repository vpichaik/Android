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






    protected void onPreExecute() {

    }


    protected String doInBackground(String... arg) {

        Log.i("val",arg[0]);
        try {
            JSONObject postDataParams = new JSONObject();
            URL url;
            if(arg[0]=="LOGIN") {
                url = new URL("http://106.51.155.80:90/cab/users/auth.php"); // here is your URL path
                postDataParams.put("username", arg[1]);
                postDataParams.put("password", arg[2]);
            }
            else if (arg[0]=="insert")
            {
                url = new URL("http://106.51.155.80:90/cab/TripDetails/TripBokking.php"); // here is your URL path
                postDataParams.put("UserName", arg[4]);
                postDataParams.put("starting_place", arg[1]);
                postDataParams.put("destination", arg[2]);
                postDataParams.put("trip_req_dt", arg[3]);


            }
            else if (arg[0]=="trip")
            {
                url = new URL("http://106.51.155.80:90/cab/TripDetails/TripStatus.php"); // here is your URL path
                postDataParams.put("UserName", arg[1]);
            }
            else if (arg[0]=="sts_upd")
            {
                url = new URL("http://106.51.155.80:90/cab/TripDetails/TripStatusUpd.php"); // here is your URL path
                postDataParams.put("UserName", arg[1]);
            }
            else if (arg[0]=="trip_dtls" || arg[0]=="cncl_dtls") {
                url = new URL("http://106.51.155.80:90/cab/TripDetails/TripInfo.php"); // here is your URL path
                postDataParams.put("UserName", arg[1]);

            }
            else if (arg[0]=="trip_dtls_n") {
                url = new URL("http://106.51.155.80:90/cab/TripDetails/TripInfo.php"); // here is your URL path
                postDataParams.put("UserName", arg[1]);

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

                Log.i("vales",sb.toString());

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
        { Login login = new Login();
            if( result_data[1].equals("1"))
            {
                login.redirect(result);
            }
            else
            {
                login.toas();
            }

        }
        else if (result_data[0].equals("insert"))
        { EmpDetails emp = new EmpDetails();
            if( result_data[1].equals("1"))
            {
                emp.redirect(result);
            }

        }
        else if (result_data[0].equals("trip"))
        { StatusActivity stats = new StatusActivity();

            stats.afterRefresh(result);

        }
        else if (result_data[0].equals("sts_upd"))
        {
            StatusActivity stats = new StatusActivity();
            stats.afterRefresh(result);



        }
        else if (result_data[0].equals("trip_dtls"))
        {
            StatusActivity stats = new StatusActivity();

            stats.afterRefresh(result);

        }
        else if (result_data[0].equals("cncl_dtls"))
        {
            StatusActivity stats = new StatusActivity();
            stats.afterRefresh(result);
        }
        else//if (result_data[0].equals("trip_dtls_n"))
        {
            Navigationmove nm = new Navigationmove();
            nm.afterRefresh(result);

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