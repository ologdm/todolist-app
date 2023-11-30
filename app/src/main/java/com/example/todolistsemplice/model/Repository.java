package com.example.todolistsemplice.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

// deve contenere la lista degli elementi -> arraylist<Item> todoItems;
// metodo da usare -> repository.getTodoItems

// REPOSITORY
// ADD, CANC, MOD simile a


@SuppressWarnings("unused")
public class Repository {

    // ***** SINGLETON *****
    // 1 attributo
    private static Repository instance;
    // 2 costruttore
    private Repository() {
    }
    // 3 getIstanza
    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }



    // *** MODEL ***

    // -> Punto salvataggio dati
    private List<Item> itemList = new ArrayList<>();


    // get + set dati
    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }


    // **** 2 °PARTE MVP ****
    // sposto da Adapter su Repository:

    // Add nuovoElemento e aggiungi ID
    // non posso passargli (item),
    // dato che è  ancora un elemento da costruire
    public void addItem(String testo, boolean stato) {
        //creazione ID - paragono
        int idMax = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (item.getID() > idMax) {
                idMax = item.getID();
            }
        }
        Item item = new Item(testo, stato, idMax + 1);
        itemList.add(item);
    }
    // aggiorno testo e stato se ID nuovo == ID esistente
    public void setItem(Item tem) {
        String testo = tem.getTesto();
        boolean stato = tem.isStato();
        int ID = tem.getID();

        for (int i = 0; i < itemList.size(); i++) {
            // prendo elementi dalla mia lista per check
            Item itemToUpdate = itemList.get(i);
            // se ID nuovo == ID esistente
            if (ID == itemToUpdate.getID()) {
                // sovrascrivi testo e stato item esistente
                itemToUpdate.setText(testo);
                itemToUpdate.setStato(stato);
                // id non devo aggiornarlo
            }
        }
    }

}
