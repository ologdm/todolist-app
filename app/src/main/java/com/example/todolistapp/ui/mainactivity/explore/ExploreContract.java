package com.example.todolistapp.ui.mainactivity.explore;

import com.example.todolistapp.data.Item;

import java.util.List;



@SuppressWarnings("unused")
public interface ExploreContract {


    interface View {
        void updateUi(List<Item> list);
        // intent per second
        void startDetailFragment(Item item);
    }


    interface Presenter{
        // due metodi per aprire SecondActivity
        void addOnClick();
        void setOnClick(Item item);


        void addItem (String testo, boolean stato);
        void setItem (Item item);


        void updateUiList();


    }

}
