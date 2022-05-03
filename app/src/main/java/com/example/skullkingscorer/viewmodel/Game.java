package com.example.skullkingscorer.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.skullkingscorer.SkullKingRepository;
import com.example.skullkingscorer.model.HighScore;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class Game extends ViewModel {

    private String name;
    private int round=8;
    private int numberOfPlayers;
    private int playerGettingBonus;
    ArrayList<Player> players = new ArrayList<>();
    Calculator calculator = new Calculator();
    ArrayList<HighScore> highScores = new ArrayList<>();

    SkullKingRepository repository;

    @Inject
    public Game(SkullKingRepository repository) {
        this.repository = repository;
    }

    public void setNumberOfPlayers(Integer number){
        this.numberOfPlayers = number;
    }
    public void setPlayers(ArrayList<String> playersNames){
        for(int i=0; i<playersNames.size(); i++) {
            this.players.add(new Player(playersNames.get(i), i));
        }
        incrementRound();
    }

    public void setBids(ArrayList<Integer> bids) {
        for(int i=0; i<bids.size(); i++){
            Integer bid = bids.get(i);
            this.players.get(i).setBid(bid);
        }
    }

    public void setPlayerGettingBonus(int number){
        this.playerGettingBonus = number;
    }

    public void changeBid(int bid, Player player) {
//        System.out.println(player.getBid());
        player.setBid(player.getBid() + bid);
//        System.out.println(player.getBid());
    }

    public void calculateScores(ArrayList<Integer> tricksWon) {
        for(int i=0; i<getNumberOfPlayers(); i++){
            Player currentPlayer = getPlayer(i);
            if(currentPlayer.getWageredPoints() != 0) {
                this.calculator.handleWager(currentPlayer, currentPlayer.getWageredPoints(), currentPlayer.getBid(), currentPlayer.getWon());
            }
//            System.out.println(currentPlayer.getScore());
            if(this.calculator.bidAchieved(currentPlayer.getBid(), tricksWon.get(i))){
//                System.out.println(currentPlayer.getName() + currentPlayer.getBid());
//                System.out.print(tricksWon.get(i));
                this.calculator.posScoreCalc(currentPlayer, currentPlayer.getBid(), getRound());
            } else {
//                System.out.println(currentPlayer.getName() + currentPlayer.getBid());
//                System.out.print(tricksWon.get(i));
                this.calculator.negScoreCalc(currentPlayer, currentPlayer.getBid(), getRound(), tricksWon.get(i));
            }
//            System.out.println(currentPlayer.getScore());
        }
    }

    public void addBonus(Player player, int bonusType, int fourteens, int pirates) {
        this.calculator.addBonus(player, bonusType, fourteens, pirates);
    }
    public void handleWager(Player player, int wagered, int bid, int won){
        this.calculator.handleWager(player, wagered, bid, won);
    }

    public void printScores() {
        for(int i=0; i<this.players.size(); i++){
            System.out.println(this.players.get(i).getName() + this.players.get(i).getScore());
        }
    }

    public void saveScores(){
        new Thread(() -> {
            players.forEach(player -> {
                this.repository.saveHighScore(player);
            });
        }).start();
    }

    public void incrementRound(){
        this.round++;
    }

    public int getRound(){
        return this.round;
    }

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public Player getPlayer(int playerNumber){
        for(Player player : this.players){
            if(player.getPlayerNumber() == playerNumber){
                return player;
            }
        }
        return null;
    }

    public int getPlayerGettingBonus() {
        return this.playerGettingBonus;
    }

    public ArrayList<HighScore> getHighScores() {
//        this.highScores.clear();
//        this.repository.getHighScores(highScores -> {
//            this.highScores.addAll(highScores);
//        });
        return this.highScores;
    }

    //method EndRound -> point calculations/updating scores

    //method changeBid


}
