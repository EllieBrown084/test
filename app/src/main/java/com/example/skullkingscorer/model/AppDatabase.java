package com.example.skullkingscorer.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {HighScore.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SkullKingDao getSkullKingDao();

}