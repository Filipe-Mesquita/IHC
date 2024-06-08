package com.wordle.wordle;

public class Player {

    private String username;
    private int score;


    public Player(String username) {
        this.username = username;
        score = 0;
    }

    public String getUsername()
    {
        return username;
    }

    public int getScore()
    {
        return score;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setScore(int socre)
    {
        this.score = socre;
    }
}
