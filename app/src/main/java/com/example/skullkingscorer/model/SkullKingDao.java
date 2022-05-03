package com.example.skullkingscorer.model;

//CRUD
//Create
//Read
//Update
//Delete

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.skullkingscorer.viewmodel.Player;

import java.util.List;

@Dao
public interface SkullKingDao {

    @Query("SELECT * FROM highscore")
    public List<HighScore> getHighScores();

    @Insert
    public long createHighScore(HighScore highScore);

    @Update
    public void updateHighScores(HighScore highScore);

    @Delete
    public void deleteHighScore(HighScore highScore);
}