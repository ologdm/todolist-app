package com.example.todolistsemplice.main;

// in questo caso non ho bisogno della ripository

@SuppressWarnings("unused")
public class MainPresenter implements Contract.Presenter {

    //attributo
    private Contract.View view;

    // costruttore
    public MainPresenter (Contract.View v) {
        this.view = v;
    }




    // TODO metodi da implementare
    @Override
    public void addItemClick() {

    }

    @Override
    public void onItemClick() {

    }
}
