package com.example.todolistapp.second;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.todolistapp.repository.Item;
import com.example.todolistapp.LCActivity;
import com.example.todolistapp.R;

// SecondActivity
// -> AddItem
// -> SetItem


public class SecondActivity extends LCActivity implements Contract.View {

    // 1 def code uninvoci
    public static final String EXTRA_TESTO = "testo da aggiungere";
    public static final String EXTRA_STATO = "stato da agiungere";
    //public static final String EXTRA_ID = "ID da agiungere"; - non serve

    public static final String EXTRA_ITEM = "item da visualizzare";

    // 2 dichiaro view
    EditText editText;
    CheckBox checkBox;
    Button buttonSave;
    Button buttonBack;

    // 3 istanza presenter
    Contract.Presenter presenter = new SecondPresenter(this);

    // 4 Intent MainActivity - ricevo item su itemClick
    Item item;

    //Item item2 = getIntent().getSerializableExtra( EXTRA_ITEM,  Item.class); // metodo cast interno con reflection


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element_old);


        // 5 assegnazione view
        editText = findViewById(R.id.etTesto);
        checkBox = findViewById(R.id.ckBox);
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);

        // 6 button click
        buttonBack.setOnClickListener((v) -> {
            finish();
        });

        // ritorno i dati al main
        buttonSave.setOnClickListener((v) -> {
            presenter.returnDataOnCLick();
        });


        item = (Item) getIntent().getSerializableExtra(EXTRA_ITEM);
        if (item != null) {
            editText.setText(item.getTesto());
            checkBox.setChecked(item.isStato());
        }

    }


    // **** MVP ****
    @Override
    public void saveActivityInput() {


        Intent myIntent = new Intent();

        // 1. caso set -> ritorno item modificato
        if (item != null) {
            item.setText(editText.getText().toString());
            item.setStato(checkBox.isChecked());
            // invio item a main
            myIntent.putExtra(EXTRA_ITEM, item);

        }
        // 2. caso add (item==null)
        // -> passo testo + stato, cosi repository può costruire nuovo elemento
        else {
            myIntent.putExtra(EXTRA_TESTO, editText.getText().toString());
            myIntent.putExtra(EXTRA_STATO, checkBox.isChecked());
        }

        // RESULT_OK
        setResult(Activity.RESULT_OK, myIntent);
        finish();
    }
}
