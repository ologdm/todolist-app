package com.example.todolistsemplice.mainactivity.mainfragment;


import android.content.Context;

import com.example.todolistsemplice.repository.Item;
import com.example.todolistsemplice.repository.Repository;

// *** su view devo avere metodi limitati
// *** repository lo posso avere completo non serve interfaccia


@SuppressWarnings("unused")
public class MainPresenter implements Contract.Presenter {


    private Contract.View view;
    Context context;

    private Repository repository;

    // List<Item> itemList; - non serve


    // COSTRUTTORE
    public MainPresenter(Contract.View v, Context context) {
        this.view = v;
        this.context = context;
        repository = Repository.getInstance(context);
    }


    // CLICK PER DETAILS
    @Override
    public void onAddButtonClick() {
        view.startSecondActivity(null); // gli passo 'null', dato che non ho item in ingresso
    }

    @Override
    public void onItemClick(Item item) {  // gli passo l'item del adapter
        view.startSecondActivity(item);
    }



    // Add repos and update view
    @Override
    public void addItem(String testo, boolean stato) {
        // passo dati a repository
        repository.addItem(testo, stato);
        // aggiorno listaPresenter
        updateUiList();
    }


    // Set repos and update view
    @Override
    public void setItem(Item item) {
        //salvo dati in repository
        repository.setItem(item);
        // pesco dati da repository e updateUi
        updateUiList();
    }



    @Override
    public void updateUiList() {
        view.updateUi(repository.getItemList());
    }




}
