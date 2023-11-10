package com.example.todolistsemplice.main;

import com.example.todolistsemplice.model.Item;

import java.util.List;

@SuppressWarnings("unused")
public interface Contract {


    interface View {
        void UpdateUi(List<Item> list);
    }



    interface Presenter{
        void addItemClick ();
        void onItemClick ();
    }

}
