package com.example.todolistapp.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class Repository {

    private static Repository instance;

    private Context context;
    DataSource dataSource;
    // = new MyLocalData(context, )


    private List<TodoItem> itemList = new ArrayList<>();


    private Repository(Context context) {
        this.context = context;
        dataSource = new DataSource(context);
    }


    public static Repository getInstance(Context context) {
        if (instance == null) {
            // context.getApplicationContext() -> prendo il Context app e non activity
            instance = new Repository(context);
        }
        return instance;
    }


    public List<TodoItem> getItemList() {
        List<TodoItem> listcheck = dataSource.loadFromLocal();
        if (listcheck ==null) { // TODO il codice si decve fermare qua
            return itemList;
        }
        itemList = listcheck; // TODO il codice arriva qua
        return listcheck;
    }


    public void setItemList(List<TodoItem> itemList) {
        // ***SHARED PREFERENCES*** -> salvo in locale
        dataSource.saveInLocal(itemList);
    }


    // Add nuovoElemento e aggiungi ID
    // non posso passargli (item),
    // dato che è  ancora un elemento da costruire
    public void addItem(String testo, boolean stato) {
        //creazione ID di testa - paragono con i vecchi
        int idMax = 0;
        for (int i = 0; i < itemList.size(); i++) {
            TodoItem item = itemList.get(i);
            if (item.getID() > idMax) {
                idMax = item.getID();
            }
        }
        // assegnazione nuovo id all'elemento in testa
        TodoItem item = new TodoItem(testo, stato, idMax + 1);
        itemList.add(item);
        setItemList(itemList);
    }



    // aggiorno testo e stato se ID nuovo == ID esistente
    public void setItem(TodoItem item) {
        String testo = item.getTesto();
        boolean stato = item.isStato();
        int ID = item.getID();

        for (int i = 0; i < itemList.size(); i++) {
            // prendo elementi dalla mia lista per check
            TodoItem itemToUpdate = itemList.get(i);
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
