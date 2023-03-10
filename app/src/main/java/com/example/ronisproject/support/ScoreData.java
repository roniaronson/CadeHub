package com.example.ronisproject.support;

public class ScoreData {
    String email;
    int score;
    int place = -99;

    public ScoreData() {
    }

    public ScoreData(String email, int score) {
        this.email = email;
        this.score = score;
        this.place = -99;
    }

    public ScoreData(String email, int score, int place) {
        this.email = email;
        this.score = score;
        this.place = place;
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

    @Override
    public String toString() {
        return "ScoreData{" +
                "place=" + place +
                ", email='" + email + '\'' +
                ", score=" + score +
                '}';
    }
}
