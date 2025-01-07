package com.example.todolistapp.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class Repository {
    private Context context;
    DataSourceShared dataSource;

    private List<Item> itemList = new ArrayList<>();


    private Repository(Context context) {
        this.context = context;
        dataSource = new DataSourceShared(context);
    }


    private static Repository instance;
    // singleton
    public static Repository getInstance(Context context) {
        if (instance == null) {
            // context.getApplicationContext() -> prendo il Context app e non activity
            instance = new Repository(context);
        }
        return instance;
    }


    public List<Item> getItemList() {
        List<Item> listcheck = dataSource.loadFromLocal();
        if (listcheck ==null) {
            return itemList;
        }
        itemList = listcheck;
        return listcheck;
    }



    public void addItem(String testo, boolean stato) {
        //create new top ID - compare to old ones
        int idMax = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            if (item.getID() > idMax) {
                idMax = item.getID();
            }
        }
        Item item = new Item(testo, stato, idMax + 1);
        itemList.add(item);
        dataSource.saveInLocal(itemList);
    }



    public void setItem(Item item) {
        String text = item.getTesto();
        boolean state = item.isStato();
        int id = item.getID();

        for (int i = 0; i < itemList.size(); i++) {
            Item itemToUpdate = itemList.get(i);
            if (id == itemToUpdate.getID()) {
                itemToUpdate.setText(text);
                itemToUpdate.setStato(state);
            }
        }
        dataSource.saveInLocal(itemList);
    }




}
