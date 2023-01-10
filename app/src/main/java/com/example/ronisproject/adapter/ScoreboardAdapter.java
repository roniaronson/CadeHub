package com.example.ronisproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ronisproject.R;
import com.example.ronisproject.support.ScoreData;

import java.util.List;

public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.ScoreViewHolder> {
    private List<ScoreData> scores;

    public ScoreboardAdapter(List<ScoreData> scores) {
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoreboard_row, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ScoreData score = scores.get(position);
        holder.placeTextView.setText(String.valueOf(score.getPlace()) + ".");
        holder.emailTextView.setText(score.getEmail());
        holder.scoreTextView.setText(String.valueOf(score.getScore()));
    }
    @Override
    public int getItemCount() {
        return scores.size();
    }
    static class ScoreViewHolder extends RecyclerView.ViewHolder{
        TextView placeTextView;
        TextView emailTextView;
        TextView scoreTextView;
        ScoreViewHolder(View view) {
            super(view);
            placeTextView = view.findViewById(R.id.place_text_view);
            emailTextView = view.findViewById(R.id.email_text_view);
            scoreTextView = view.findViewById(R.id.score_text_view);
        }
    }
}

