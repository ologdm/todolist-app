package com.example.todolistapp.second;

interface Contract {

    interface View {
        void saveActivityInput();
    }

    interface Presenter {
        void returnDataOnCLick();
    }


}
