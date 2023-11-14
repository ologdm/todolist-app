package com.example.todolistsemplice.second;

interface Contract {

    interface View {
        void saveActivityInput();
    }

    interface Presenter {
        void returnDataOnCLick();
    }


}
