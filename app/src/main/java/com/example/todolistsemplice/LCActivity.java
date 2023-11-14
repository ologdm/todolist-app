package com.example.todolistsemplice;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


// Scrive su Logcat -> quale funzione dell'activity sarà chiamata

public abstract class LCActivity extends AppCompatActivity  {


    public LCActivity() {
        System.out.println("xxx instantiate" + " " + this.getClass().getName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("xxx onCreate" + " " + this.getClass().getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("xxx onStart"+ " " + this.getClass().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("xxx onResume"+ " " + this.getClass().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("xxx onPause"+ " " + this.getClass().getName());

    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("xxx onStop"+ " " + this.getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("xxx onDestroy"+ " " + this.getClass().getName());
    }

}
