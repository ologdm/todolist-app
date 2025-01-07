package com.example.todolistapp.ui.mainactivity.explore;


import android.content.Context;

import com.example.todolistapp.data.Item;
import com.example.todolistapp.data.Repository;


public class ExplorePresenter implements ExploreContract.Presenter {

    private ExploreContract.View view;
    Context context;
    private final Repository repository;


    public ExplorePresenter(ExploreContract.View v, Context context) {
        this.view = v;
        this.context = context;
        repository = Repository.getInstance(context);
    }


    // CLICK PER DETAILS
    @Override
    public void addOnClick() {
        view.startDetailFragment(null); // gli passo 'null', dato che non ho item in ingresso
    }

    @Override
    public void setOnClick(Item item) {  // gli passo l'item del adapter
        view.startDetailFragment(item);
    }



    // Add repos and update view
    // input from DetailFragment ->
    // setFragmentResultListener{..} ->
    // addItem() or setItem()
    @Override
    public void addItem(String testo, boolean stato) {
        repository.addItem(testo, stato);
        updateUiList();
    }


    // Set repos and update view
    @Override
    public void setItem(Item item) {
        repository.setItem(item);
        updateUiList();
    }


    @Override
    public void updateUiList() {
        view.updateUi(repository.getItemList());
    }


}
