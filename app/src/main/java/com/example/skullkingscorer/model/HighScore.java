package com.example.skullkingscorer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
===================================================
id         |      name          |       score      |
1          |      Ellie         |        480       |
2          |      Zach          |        420       |
 */


@Entity
public class HighScore {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public int score;
}
