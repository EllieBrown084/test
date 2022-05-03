package com.example.skullkingscorer.viewmodel;

public class Player
{
    private String name;
    private int score;
    private int bid;
    private int won;
    private int playerNumber;
    private int wageredPoints = 0;

    public Player(String inputName, int number){
        this.name = inputName;
        this.score = 0;
        this.playerNumber = number;
    }

    public void setBid(int inputBid){
        this.bid = inputBid;
    }

    public void setWon(int inputWon) {this.won = inputWon;}

    public void setWageredPoints(int wageredPoints) {this.wageredPoints = wageredPoints;}

    public void changeScore(int inputScore){
        this.score += inputScore;
    }

    public int getBid(){
        return this.bid;
    }

    public int getWon() {return this.won;}

    public int getScore(){
        return this.score;
    }

    public String getName(){
        return this.name;
    }

    public int getPlayerNumber() {return this.playerNumber;}

    public int getWageredPoints() {return this.wageredPoints;}

}
