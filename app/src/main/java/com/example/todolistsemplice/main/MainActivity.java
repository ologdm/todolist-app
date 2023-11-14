package com.example.todolistsemplice.main;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.todolistsemplice.second.SecondActivity;
import com.example.todolistsemplice.model.Item;
import com.example.todolistsemplice.LCActivity;
import com.example.todolistsemplice.R;

import java.util.List;


public class MainActivity extends LCActivity implements Contract.View {

    // 1 def code uninvoci
    private static final int ADD_ITEM_REQUEST_CODE = 1;
    private static final int SET_ITEM_REQUEST_CODE = 2;

    // dichiarazione view
    RecyclerView todolistRV;
    Button buttonAddNew;

    // istanzio presenter (view),
    // il repository (singleton) si istanzia su presenter
    Contract.Presenter presenter = new MainPresenter(this);



    // ## IMPLEMENTAZIONE CLICK SU OGGETTO ##

    // costruttore Adapter
    // salvo l'istanza su Adapter.DimaListener tramite costruttore
    // * (item) -> passo l'item attuale su onBind
    Adapter adapter = new Adapter(item -> {
        // ****  1°PARTE MVP ****
        // item: da adapter -> presenter -> sotto in activity
        presenter.onItemClick(item);
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assegnazione view
        todolistRV = findViewById(R.id.recycleviewTodo);
        todolistRV.setLayoutManager(new LinearLayoutManager(this));

        todolistRV.setAdapter(adapter); // collego adapter alla RV

        buttonAddNew = findViewById(R.id.buttonAddNew);


        // ****  1°PARTE MVP ****
        // 1. chiamo metodo su presenter
        // 2. implementazione metodo su activity
        buttonAddNew.setOnClickListener((view) -> {
            presenter.onAddButtonClick();
        });

    }



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

        // rimanda a updateUi + aggiorna Adapter con repository
        presenter.loadData();

    }


    // *** 1 PARTE ***
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


    // **** 2 °PARTE MVP ****
    // implementazione
    // aggiornamento: adapter <- presenter <- repository
    @Override
    public void updateUi(List<Item> list) {
        adapter.updateList(list);
    }


}