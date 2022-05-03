package com.example.skullkingscorer;

import android.content.Context;
import android.os.Handler;

import androidx.room.Room;

import com.example.skullkingscorer.model.AppDatabase;
import com.example.skullkingscorer.model.HighScore;
import com.example.skullkingscorer.viewmodel.Player;

import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class SkullKingRepository {

    AppDatabase db;

    ArrayList<HighScore> highScores;
    ArrayList<HighScore> highScoresSorted = new ArrayList<>();

    @Inject
    public SkullKingRepository(@ApplicationContext Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "highscore-database").build();
    }

    public void saveHighScore(Player player) {
        HighScore newHighScore = new HighScore();
        newHighScore.name = player.getName();
        newHighScore.score = player.getScore();
        newHighScore.id = db.getSkullKingDao().createHighScore(newHighScore);
        this.highScores.add(newHighScore);
        sort(newHighScore);
    }

    public void sort(HighScore newHS) {
        boolean inserted = false;
        int i = 0;
        int number = this.highScoresSorted.size();
        if(number == 0){
            this.highScoresSorted.add(newHS);
        } else {
            while (!inserted) {
                if (newHS.score > this.highScoresSorted.get(i).score) {
                    this.highScoresSorted.add(i, newHS);
                    inserted = true;
                } else if (i == number - 1) {
                    this.highScoresSorted.add(i, newHS);
                    inserted = true;
                } else if (newHS.score < this.highScoresSorted.get(i).score && newHS.score > this.highScoresSorted.get(i + 1).score) {
                    this.highScoresSorted.add(i, newHS);
                    inserted = true;
                }
                i++;
            }
        }
    }

    public ArrayList<HighScore> getHighScores() {
        if (highScores == null) {
            new Thread(() -> {
                highScores = (ArrayList<HighScore>) db.getSkullKingDao().getHighScores();
            }).start();
        }
        for(int i=0; i<highScores.size(); i++) {
            sort(highScores.get(i));
        }
        return highScoresSorted;
    }
}
