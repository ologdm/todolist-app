package com.example.todolistapp.data.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@SuppressWarnings("unused")
@Entity(tableName = "todo_entities")
public class TodoEntity {

    @PrimaryKey(autoGenerate = true) private int id;
    private final String todoText;
    private final boolean state;


    // constructor
    public TodoEntity(String todoText, boolean state) {
        this.todoText = todoText;
        this.state = state;
    }


    // getters
    public String getTodoText() {
        return todoText;
    }

    public boolean isState() {
        return state;
    }

}
