package com.example.todolistsemplice.main;


import com.example.todolistsemplice.model.Item;
import com.example.todolistsemplice.model.Repository;

import java.util.List;

// *** su view devo avere metodi limitati
// *** repository lo posso avere completo non serve interfaccia


@SuppressWarnings("unused")
public class MainPresenter implements Contract.Presenter {


    // ATTRIBUTI

    private Contract.View view;

    // ** 3 PARTE **
    //istanzio singleton repository
    private Repository repository = Repository.getInstance();

    List<Item> itemList; // salvare lista in locale



    // COSTRUTTORE
    public MainPresenter(Contract.View v) {
        this.view = v;
    }


    // ****  1°PARTE MVP ****
    // sposto codice: da onCreate -> Presenter -> sotto in Activity

    @Override
    public void onAddButtonClick() {
        view.startSecondActivity(null); // gli passo 'null', dato che non ho item in ingresso
    }

    @Override
    public void onItemClick(Item item) {  // gli passo l'item del adapter
        view.startSecondActivity(item);
    }


    // **** 2°PARTE MVP ****

    // metodi singolo item
    // add e set: spostato da adapter -> repository

    @Override
    public void addItem(String testo, boolean stato) {
        // passo dati a repository
        repository.addItem(testo, stato);
        // aggiorno listaPresenter
        updatePresenterList();
    }


    @Override
    public void setItem(Item item) {
        repository.setItem(item);
        // aggiorno listaPresenter
        updatePresenterList();
    }


    // ** 3 PARTE **  REPOSITORY

    // 1. UpdateUi from Repository
    @Override
    public void loadData() {
        // 1.get dati da repository (cache)
        List<Item> list = repository.getItemList();
        // 2.passo dati ad adapter per mostrarli
        view.updateUi(list);
    }


    private void updatePresenterList (){
        itemList = repository.getItemList();
    }


}
