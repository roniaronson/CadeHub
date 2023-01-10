package com.example.ronisproject.support;

public class ScoreData {
    int place;
    String email;
    int score;

    public ScoreData() {
    }

    public ScoreData(int place, String email, int score) {
        this.place = place;
        this.email = email;
        this.score = score;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
