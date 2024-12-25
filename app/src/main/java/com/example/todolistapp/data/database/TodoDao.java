package com.example.todolistapp.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert()
    void insertSingle(TodoEntity entity);

    @Update
    void updateSingle(TodoEntity entity);

    @Query("SELECT * FROM todo_entities WHERE id=:id ")
    TodoEntity readSingle(int id);

    @Query("SELECT * FROM todo_entities")
    List<TodoEntity> readAll();


    @Delete
    void deleteSingle();

    @Delete
    void deleteAll();

}
