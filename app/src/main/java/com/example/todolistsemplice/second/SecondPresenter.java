package com.example.todolistsemplice.second;

public class SecondPresenter implements Contract.Presenter {

    private Contract.View view;

    public SecondPresenter (Contract.View view){
        this.view = view;
    }



    @Override
    public void returnDataOnCLick() {
        view.saveActivityInput();
    }


}
