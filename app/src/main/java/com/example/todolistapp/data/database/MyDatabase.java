package com.example.todolistapp.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {TodoEntity.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    abstract TodoDao todoDao();
}
