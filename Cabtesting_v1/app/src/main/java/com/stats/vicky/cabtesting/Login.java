package com.stats.vicky.cabtesting;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.Toast;
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

public class Login extends AppCompatActivity {
    EditText user;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.passwd);
    }

    public void signIn(View view) {

       new SendPostRequest().execute();



        }
    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){
            Intent i=new Intent(Login.this,ProgressBar.class);
            startActivity(i);

        }


        protected String doInBackground(String... arg) {
            String username = user.getText().toString();
            String Password = pass.getText().toString();

            try {

                URL url = new URL("http://106.51.155.80:90/isha/users/auth.php"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username",username);
                postDataParams.put("password", Password);
                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
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

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            String username = user.getText().toString();
            if (result.equals("1"))
            {
                Toast.makeText(getApplicationContext(), "success",
                        Toast.LENGTH_LONG).show();
                Intent i=new Intent(Login.this,EmpDetails.class);
                Bundle bundle = new Bundle();

                bundle.putString("username", username);

                i.putExtras(bundle);

                startActivity(i);

            }
            else {

                Toast.makeText(getApplicationContext(),  "Username and Password is wrong",
                        Toast.LENGTH_LONG).show();
                Intent i=new Intent(Login.this,Login.class);
                startActivity(i);
            }
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
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


