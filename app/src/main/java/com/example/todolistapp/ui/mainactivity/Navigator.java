package com.example.todolistapp.ui.mainactivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.todolistapp.R;


public class Navigator {


    // fragment nuovo
    public void replace(FragmentActivity activity, Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();

        manager.beginTransaction()
            .replace(R.id.frameLayout1, fragment)
            .commit();
    }



    public void backstackPush(FragmentActivity activity, Fragment fragment) {
        FragmentManager manager = activity.getSupportFragmentManager();

        manager.beginTransaction()
            .replace(R.id.frameLayout1, fragment)
            .addToBackStack(null)
            .commit();
    }

}
