package com.example.ronisproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ronisproject.R;
import com.example.ronisproject.http.MyHTTPInterface;
import com.example.ronisproject.http.score.Score;
import com.example.ronisproject.support.ScoreData;
import com.google.android.material.textfield.TextInputLayout;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout menu_TicTacToe;
    private LinearLayout menu_Memory;
    private LinearLayout menu_GOAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();
        initBTNs();


    }




    private void initBTNs() {
        menu_TicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToScore("Tic Tac Toe");}
        });

        menu_Memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToScore("Memory");}
        });

        menu_GOAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToScore("Hall Of Fame");}
        });
    }

    private void goToScore(String game) {


        Score success = (Score) new Score(game, new MyHTTPInterface() {
            @Override
            public void myMethod(boolean result) {
                if (result == true) {

                    finish();
                    Intent intent = new Intent(MenuActivity.this, ScoreActivity.class);
                    intent.putExtra("game", game);
                    startActivity(intent);
                }
            }
        }).execute();



    }


    private void findViews() {
        menu_TicTacToe = findViewById(R.id.menu_TicTacToe);
        menu_Memory = findViewById(R.id.menu_Memory);
        menu_GOAT = findViewById(R.id.menu_GOAT);
    }
}
