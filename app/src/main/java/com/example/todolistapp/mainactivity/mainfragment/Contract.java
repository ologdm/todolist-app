package com.example.todolistapp.mainactivity.mainfragment;

import com.example.todolistapp.repository.Item;

import java.util.List;



@SuppressWarnings("unused")
public interface Contract {


    public interface View {
        // aggiorno lista adapter
        void updateUi(List<Item> list);
        // intent per second
        void startSecondActivity(Item item);
    }


    public interface Presenter{


        // due metodi per aprire SecondActivity
        void onAddButtonClick();
        void onItemClick (Item item);


        // due metodi per gestire elementi lista
        void addItem (String testo, boolean stato);
        void setItem (Item item);


        void updateUiList();


    }

}
