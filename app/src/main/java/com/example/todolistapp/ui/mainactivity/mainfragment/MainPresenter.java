package com.example.todolistapp.ui.mainactivity.mainfragment;


import android.content.Context;

import com.example.todolistapp.data.TodoItem;
import com.example.todolistapp.data.Repository;

// *** su view devo avere metodi limitati
// *** repository lo posso avere completo non serve interfaccia


@SuppressWarnings("unused")
public class MainPresenter implements Contract.Presenter {

    private Contract.View view;
    Context context;
    private final Repository repository;


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
    public void onItemClick(TodoItem item) {  // gli passo l'item del adapter
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
    public void setItem(TodoItem item) {
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
