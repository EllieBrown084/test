package com.example.skullkingscorer.viewmodel;

public class Calculator {

    public boolean bidAchieved(int bid, int won){
        return bid == won;
    }

    public void posScoreCalc(Player player, int bid, int round){
//        System.out.println(player.getName() + player.getScore());
        if(bid == 0){
//            System.out.println("Round" + round);
            player.changeScore(round*10);
        } else {
            player.changeScore(bid*20);
        }
//        System.out.println(player.getName() + player.getScore());
    }

    public void negScoreCalc(Player player, int bid, int round, int won){
//        System.out.println(player.getName() + player.getScore());
        if(bid == 0) {
//            System.out.println("Got here");
            player.changeScore(-round*10);
        } else {
            player.changeScore(-Math.abs(bid-won)*10);
        }
//        System.out.println(player.getName() + player.getScore());
    }

    public void addBonus(Player player, int bonusType, int fourteens, int pirates){
//        System.out.println(player.getName() + player.getScore());
        if(bonusType==1) { //14 (yellow, green, or purple)
            player.changeScore(10*fourteens);
        } else if(bonusType==2) { //14 (black)
            player.changeScore(20);
        } else if(bonusType==3) { //Capturing pirates
            player.changeScore(30*pirates);
        } else if(bonusType==4) { //Capturing skull king
            player.changeScore(50);
        } else { //Loot card
            player.changeScore(20);
        }

//        System.out.println(player.getName() + player.getScore());
    }

    public void handleWager(Player player, int wagered, int bid, int won) {
//        System.out.println(player.getName() + player.getScore());
        if(bid==won){
            player.changeScore(wagered);
        } else {
            player.changeScore(-wagered);
        }
//        System.out.println(player.getName() + player.getScore());
    }
}
