package com.example.miner;

public class Player {
    private String fullname;
    private int score;

    public Player(String fullname, int score) {
        this.fullname = fullname;
        this.score = score;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player : " +fullname +"\n"+
                "score = " + score;
    }
}
