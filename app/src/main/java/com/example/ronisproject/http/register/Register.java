package com.example.ronisproject.http.register;

import android.os.AsyncTask;

import com.example.ronisproject.activities.RegistrationActivity;
import com.example.ronisproject.http.MyHTTPInterface;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Register extends AsyncTask<Void, Void, Boolean> {
    private MyHTTPInterface mListener;

    public Register(MyHTTPInterface mListener) {
        this.mListener = mListener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            String urlString = "http://10.0.2.2:8082/superapp/users/";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Set the Content-Type and Accept headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            String email = RegistrationActivity.GLOBAL_EMAIL_REG;
            int score = 15;
            // Write the JSON data to the request body
            String jsonData = "{\"email\":\"" + email + "\"}";

            System.out.println(jsonData);

            try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
                out.write(jsonData.getBytes("UTF-8"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            connection.connect();

            int status = connection.getResponseCode();
            if (status >= 200 && status < 300) {
                // Successful response
                // Read the response body
                System.out.println("STATUS: " + status);

                try (InputStream in = connection.getInputStream()) {
                    // Do something with the response
                    System.out.println("DATA: " + in);
                }
            } else {
                // Error response
                // Read the error message from the response body
                String message;
                try (InputStream in = connection.getErrorStream()) {
                    message = readString(in);
                }
                throw new IOException("HTTP error " + status + ": " + message);
            }

            return true;

        } catch (ProtocolException | MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private String readString(InputStream in) throws IOException {

        return "";
    }

}
