package com.example.todolistsemplice.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

// deve contenere la lista degli elementi -> arraylist<Item> todoItems;
// metodo da usare -> repository.getTodoItems

// REPOSITORY
// ADD, CANC, MOD simile a


@SuppressWarnings("unused")
public class Repository {

    // 1 attributo
    private static Repository instance;

    private Context context;
    MyLocalData myLocalData;
    // = new MyLocalData(context, )


    private List<Item> itemList = new ArrayList<>();


    // 2 costruttore -> usato solo nel metodo getIstance
    private Repository(Context context) {
        this.context = context;
        myLocalData = new MyLocalData(context);
    }


    // 3 getIstanza
    public static Repository getInstance(Context context) {
        if (instance == null) {
            // context.getApplicationContext() -> prendo il Context app e non activity
            instance = new Repository(context);
        }
        return instance;
    }


    //set + get dati
    public List<Item> getItemList() {
        List<Item> listcheck = myLocalData.loadFromLocal();
        if (listcheck ==null) { // TODO il codice si decve fermare qua
            return itemList;
        }
        itemList = listcheck; // TODO il codice arriva qua
        return listcheck;
    }


    public void setItemList(List<Item> itemList) {
        // ***SHARED PREFERENCES*** -> salvo in locale
        myLocalData.saveInLocal(itemList);
    }


    // Add nuovoElemento e aggiungi ID
    // non posso passargli (item),
    // dato che è  ancora un elemento da costruire
    public void addItem(String testo, boolean stato) {
        //creazione ID di testa - paragono con i vecchi
        int idMax = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (item.getID() > idMax) {
                idMax = item.getID();
            }
        }
        // assegnazione nuovo id all'elemento in testa
        Item item = new Item(testo, stato, idMax + 1);
        itemList.add(item);
        setItemList(itemList);
    }



    // aggiorno testo e stato se ID nuovo == ID esistente
    public void setItem(Item item) {
        String testo = item.getTesto();
        boolean stato = item.isStato();
        int ID = item.getID();

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
        setItemList(itemList);
    }




}
