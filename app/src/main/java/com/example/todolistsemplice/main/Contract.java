package com.example.todolistsemplice.main;

import com.example.todolistsemplice.model.Item;

import java.util.List;

// uso il contract in modo che ho il permesso
// di usare solo i metodi selezionati
// con la nuova istanza


@SuppressWarnings("unused")
public interface Contract {


    // ****  1°PARTE MVP ****


    interface View {
        // aggiorno lista adapter
        void updateUi(List<Item> list);
        // intent per second
        void startSecondActivity(Item item);
    }


    interface Presenter{


        // ****  1°PARTE MVP ****
        // due metodi per aprire SecondActivity
        void onAddButtonClick();
        void onItemClick (Item item);

        // ****  2°PARTE MVP ****
        // due metodi per gestire elementi lista
        void addItem (String testo, boolean stato);
        void setItem (Item item);

        // ****  3°PARTE MVP ****
        void loadData();


    }

}
