package com.example.ronisproject.http.score;

import android.os.AsyncTask;

import com.example.ronisproject.http.MyHTTPInterface;
import com.example.ronisproject.support.ScoreData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

public class Score extends AsyncTask<Void, Void, Boolean> {
    public static ScoreData[] GLOBAL_SCORES;
    private String game;
    private MyHTTPInterface mListener;

    public Score(String game, MyHTTPInterface mListener) {
        this.game = game;
        this.mListener = mListener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        switch (game) {
            case "Tic Tac Toe":
                game = "tictactoe";
                break;
            case "Memory":
                game = "memorygame";
                break;
            case "Hall Of Fame":
                game = "halloffame";
                break;
            default:
                break;
        }


        URL url = null;
        try {
            if (game == "halloffame") {
                url = new URL("http://10.0.2.2:8082/superapp/users/" + game + "/");
            } else {
                url = new URL("http://10.0.2.2:8082/superapp/users/halloffame/" + game + "/");
            }

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Read the response
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            String json = "";
            while ((output = br.readLine()) != null) {
                json += output;
                System.out.println(json);
            }

            // TODO - need to implement the data into the score tables.

            Gson gson = new Gson();
            GLOBAL_SCORES = gson.fromJson(json, ScoreData[].class);
            int index = 1;

            for (ScoreData score : GLOBAL_SCORES) {
                System.out.println(score);
                score.setPlace(index);
                index ++;
                System.out.println(score);
            }

            conn.disconnect();
            return true;
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private Boolean parseLoginResponse(String responseBody) {
        System.out.println(responseBody);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (mListener != null)
            mListener.myMethod(result);
    }
}
