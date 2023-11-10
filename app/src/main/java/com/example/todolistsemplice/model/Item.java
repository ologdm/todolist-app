package com.example.todolistsemplice.model;

import java.io.Serializable;

// solo 2 elementi necessari, Testo e Check;
// lo rendo `Serializable` per poterlo passare come Oggetto in Intent


// FATTO
@SuppressWarnings("unused")
public class Item implements Serializable {

    private String testo;
    private boolean stato;
    private final int ID;



    public Item(String testo, boolean stato, int id) {
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

}

