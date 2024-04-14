package com.example.todolistsemplice.mainactivity;

import androidx.annotation.Nullable;

import android.os.Bundle;

import com.example.todolistsemplice.mainactivity.mainfragment.MainFragment;
import com.example.todolistsemplice.LCActivity;
import com.example.todolistsemplice.R;


public class MainActivity extends LCActivity {

    // 1 def code uninvoci
    //private static final int ADD_ITEM_REQUEST_CODE = 1;
    //private static final int SET_ITEM_REQUEST_CODE = 2;


    //RecyclerView recyclerView;
    //Button buttonAdd;
    //Contract.Presenter presenter;

/*
    Adapter adapter = new Adapter(item -> {
        // item: da adapter -> presenter -> sotto in activity
        presenter.onItemClick(item);
    });

 */

    Navigator navigator = new Navigator();


    // * Context viene inizializzato su onCreate(),
    // quindi i metodi che lo usano devono essere dentro onCreate()
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);  // OK
        //setContentView(R.layout.activity_main_old);


        // ####################################
        // FRAGMENT
        navigator.replace(this, new MainFragment());


        // TODO NAVIGAZIONE PULSANTI


        // ####################################











        /*
        // **Context** - spostato su onCreate
        presenter = new MainPresenter(this,this);


        // assegnazione view
        recyclerView = findViewById(R.id.recycleviewTodo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter); // collego adapter alla RV

        buttonAdd = findViewById(R.id.buttonAddNew);


        // 1. chiamo metodo su presenter
        // 2. implementazione metodo su activity
        buttonAdd.setOnClickListener((view) -> {
            presenter.onAddButtonClick();
        });

        presenter.loadData();
         */


    }





    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            // passa elemento a -> presenter -> repository
            // non posso usare item perchè e incompleto
            presenter.addItem(
                data.getStringExtra(SecondActivity.EXTRA_TESTO),
                data.getBooleanExtra(SecondActivity.EXTRA_STATO, false)
            );
        }

        if (requestCode == SET_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = (Item) data.getSerializableExtra(SecondActivity.EXTRA_ITEM);
            presenter.setItem(item);
        }

        presenter.loadData(); // rimanda a updateUi + aggiorna Adapter con repository
    }

     */


    /*
    // implementazione
    // aggiornamento: adapter <- presenter <- repository
    @Override
    public void updateUi(List<Item> list) {
        adapter.updateList(list);
    }

     */


    /*
    @Override
    public void startSecondActivity(Item item) {

        Intent intent = new Intent(this, SecondActivity.class);

        // addbutton
        if (item == null) {
            startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
        }
        // itemclick
        else {
            intent.putExtra(SecondActivity.EXTRA_ITEM, item);
            startActivityForResult(intent, SET_ITEM_REQUEST_CODE);
        }
    }

     */

}