package com.example.ronisproject.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ronisproject.R;
import com.example.ronisproject.adapter.ScoreboardAdapter;
import com.example.ronisproject.support.ScoreData;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    TextView score_TXT_game;
    RecyclerView score_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        findViews();
        initRecycle();


    }

    private void initRecycle() {
        score_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        // get your score data here
        List<ScoreData> scores = new ArrayList<>();
        scores.add(new ScoreData(1, "first@win.com", 100));
        scores.add(new ScoreData(2, "second@win.com", 85));
        scores.add(new ScoreData(3, "third@win.com", 70));
        ScoreboardAdapter adapter = new ScoreboardAdapter(scores);
        score_recycler_view.setAdapter(adapter);
    }


    private void findViews() {
        String gameTitle = getIntent().getStringExtra("game");
        score_TXT_game = findViewById(R.id.score_TXT_game);
        score_recycler_view = findViewById(R.id.score_recycler_view);
        score_TXT_game.setText(gameTitle);
    }

}
