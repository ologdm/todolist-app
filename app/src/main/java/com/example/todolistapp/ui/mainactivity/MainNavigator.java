package com.example.todolistapp.ui.mainactivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.todolistapp.R;


public class MainNavigator {


    public void replace(FragmentActivity fragmentActivity, Fragment fragment) {
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        manager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit();
    }



    public void addToBackstack(FragmentActivity fragmentActivity, Fragment fragment) {
        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        manager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit();
    }

}
