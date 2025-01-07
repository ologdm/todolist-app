package com.example.todolistapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class DataSourceShared {

    private static final String PREFS_LIST = "key01";


    Context context; // tramite costruttore
    Gson gson = new Gson();
    SharedPreferences prefs;


    // costruttore
    DataSourceShared(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
    }


    // FUNZIONI CONVERSIONE
    // 1. List -> Json
    private String getJson(List<Item> list) {
        String stringJson = gson.toJson(list);
        return stringJson;
    }

    // 2. Json -> List
    private List<Item> getListFromJson(String jsonString) {
        Type listType = new TypeToken<List<Item>>() {
        }.getType();
        List<Item> itemList = gson.fromJson(jsonString, listType);
        return itemList;
    }


    // SALVO/ CARICO FATTO

    // -> salvo in locale
    // ricevo lista, salvo su Shared
    void saveInLocal(List<Item> itemList) {
        // conversione
        String jsonString = getJson(itemList);
        //salva
        prefs.edit()
            .putString(PREFS_LIST, jsonString)
            .apply();
    }


    // -> carico da locale
    public List<Item> loadFromLocal() {
        // ottieni Json da SharedPref
        String jsonString = prefs.getString(PREFS_LIST, null);
        // ottieni lista convertita
        List<Item>itemList = getListFromJson(jsonString);
        return itemList;
    }


}
