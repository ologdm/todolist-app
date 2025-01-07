package com.example.todolistapp.ui.mainactivity;

import androidx.annotation.Nullable;

import android.os.Bundle;

import com.example.todolistapp.ui.mainactivity.explore.ExploreFragment;
import com.example.todolistapp.utils.LCActivity;
import com.example.todolistapp.R;

//LCActivity - extend AppCompatActivity

public class MainActivity extends LCActivity {


    MainNavigator mainNavigator = new MainNavigator();

    // * Context viene inizializzato su onCreate(),
    // quindi i metodi che lo usano devono essere dentro onCreate()
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // default - all
        mainNavigator.replace(this, new ExploreFragment());

    }


}