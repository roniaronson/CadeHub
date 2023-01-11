package com.example.ronisproject.activities;

import static com.example.ronisproject.http.score.Score.GLOBAL_SCORES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ronisproject.R;
import com.example.ronisproject.adapter.ScoreboardAdapter;
import com.example.ronisproject.http.score.Score;
import com.example.ronisproject.support.ScoreData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    TextView score_TXT_game;
    RecyclerView score_recycler_view;
    private MaterialButton score_BTN_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        findViews();
        initBTN();
        initRecycle();


    }

    private void initBTN() {
        score_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ScoreActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecycle() {
        score_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        ScoreboardAdapter adapter = new ScoreboardAdapter(Arrays.asList(GLOBAL_SCORES));
        score_recycler_view.setAdapter(adapter);
    }


    private void findViews() {
        String gameTitle = getIntent().getStringExtra("game");
        score_TXT_game = findViewById(R.id.score_TXT_game);
        score_recycler_view = findViewById(R.id.score_recycler_view);
        score_TXT_game.setText(gameTitle);
        score_BTN_back = findViewById(R.id.score_BTN_back);
    }

}
