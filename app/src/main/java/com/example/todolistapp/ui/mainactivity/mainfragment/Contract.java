package com.example.todolistapp.ui.mainactivity.mainfragment;

import com.example.todolistapp.data.TodoItem;

import java.util.List;



@SuppressWarnings("unused")
public interface Contract {


    public interface View {
        // aggiorno lista adapter
        void updateUi(List<TodoItem> list);
        // intent per second
        void startSecondActivity(TodoItem item);
    }


    public interface Presenter{


        // due metodi per aprire SecondActivity
        void onAddButtonClick();
        void onItemClick (TodoItem item);


        // due metodi per gestire elementi lista
        void addItem (String testo, boolean stato);
        void setItem (TodoItem item);


        void updateUiList();


    }

}
