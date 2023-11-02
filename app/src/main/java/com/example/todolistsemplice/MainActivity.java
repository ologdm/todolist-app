package com.example.todolistsemplice;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
// MainActivity - serve per gestire l'interfaccia iniziale

// ...abbiamo 2 tipologie di start activity->
// ....1-startActivity
// ....2-startActivityForResult

public class MainActivity extends LifecycleActivity {

    // definizione codici uninvoci per le activity
    private static final int ADD_ACTIVITY_REQUEST_CODE = 1;
    private static final int SET_ACTIVITY_REQUEST_CODE = 2;


    // 3 ## IMPLEMENTAZIONE CLICK SU OGGETTO ##
    // lambda lo implemento qua perche devo poter accedere alle funzioni di activity

    @SuppressWarnings("Convert2Lambda")
    private Adapter.DimaListener myDimaListener = new Adapter.DimaListener() {
        @Override
        public void onClick(Item item) {
            Intent intent = new Intent(MainActivity.this, AdditemActivity.class);
            intent.putExtra(AdditemActivity.EXTRA_ITEM,item); // passare `item`, implemento Serializable in Item
            startActivityForResult(intent, 2); // voglio anche il return
        }
    };

    // Tramite il costruttore di Adapter salvo l'istanza su Adapter.myItemClickListener
    Adapter myAdapter = new Adapter(myDimaListener);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // specifico la view che deve creare secondo le specifiche del
        // layout indicato con tutti gli elementi presenti al suo interno

        System.out.println("ciao");

        RecyclerView todoRV = findViewById(R.id.recycleviewTodo);
        todoRV.setLayoutManager(new LinearLayoutManager(this));
        todoRV.setAdapter(myAdapter);

        Button buttonAddNew = findViewById(R.id.buttonAddNew);

        // OnClickListener = Callback
        // public void
        buttonAddNew.setOnClickListener((view) -> {
            Intent myIntent = new Intent(this, AdditemActivity.class);
            // funzione di Activity
            // -> gli passo l'intent e il codice univoco
            startActivityForResult(myIntent, ADD_ACTIVITY_REQUEST_CODE);
        });


    }

    // qua userò l'ACTIVITY_REQUEST_CODE>=0 per verificare se ho avuto risposta
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // add item ritornato
        if (requestCode == ADD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String testo = data.getStringExtra(AdditemActivity.EXTRA_TESTO);
            boolean stato = data.getBooleanExtra(AdditemActivity.EXTRA_STATO, false);
            myAdapter.addAndUpdate(testo, stato); // add() --> fa 1. aggiungi item all'array + 2. adapter.notifyDataSetChanged()
        }

        // TODO  set item ritornato
        if (requestCode == SET_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            // return elemento da AddActivity
            Item item = (Item) data.getSerializableExtra(AdditemActivity.EXTRA_ITEM);
            myAdapter.setAndUpdate(item.getTesto(), item.isStato(), item.getID());

        }


    }


}