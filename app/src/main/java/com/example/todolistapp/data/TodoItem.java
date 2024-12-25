package com.example.todolistapp.data;

import java.io.Serializable;


// FATTO
@SuppressWarnings("unused")
public class TodoItem implements Serializable {

    private String testo;
    private boolean stato;
    private final int ID;



    public TodoItem(String testo, boolean stato, int id) {
        this.testo = testo;
        this.stato = stato;
        this.ID = id;
    }


    // getters
    public String getTesto() {
        return testo;
    }
    public boolean isStato() {
        return stato;
    }
    public int getID() {
        return ID;
    }


    // setters
    public void setText(String testo) {
        this.testo = testo;
    }
    public void setStato(boolean stato) {
        this.stato = stato;
    }
    public void seStato(boolean stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Item{" +
            "testo='" + testo + '\'' +
            ", stato=" + stato +
            ", ID=" + ID +
            '}';
    }
}

