package com.example.todolistsemplice.additem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.todolistsemplice.model.Item;
import com.example.todolistsemplice.LCActivity;
import com.example.todolistsemplice.R;

public class AdditemActivity extends LCActivity {
    public static final String EXTRA_TESTO = "testo da aggiungere";
    public static final String EXTRA_STATO = "stato da agiungere";
    public static final String EXTRA_ID = "ID da agiungere";


    public static final String EXTRA_ITEM = "item da visualizzare";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);


        // Ricezione Intent (da MainActivity) attraverso Click su Oggetto RV
        Item item = (Item) getIntent().getSerializableExtra(EXTRA_ITEM); // metodo vecchio. cast manuale
        //Item item2 = getIntent().getSerializableExtra( EXTRA_ITEM,  Item.class); // metodo cast interno con reflection


        EditText editText = findViewById(R.id.etTestoAdditemActivity);
        CheckBox checkBox = findViewById(R.id.ckboxAdditemActivity);
        Button buttonSave = findViewById(R.id.buttonSaveAdditemActivity);
        Button buttonBack = findViewById(R.id.buttonBackAdditemActivity);



        buttonBack.setOnClickListener((v) -> {
            finish();
        });


        // Intent result -> per button AddNewElement di MainActivity
        // Passa i dati come ritorno alla main
        buttonSave.setOnClickListener((v) -> {
            Intent myIntent = new Intent();
            myIntent.putExtra(EXTRA_TESTO, editText.getText().toString());
            myIntent.putExtra(EXTRA_STATO, checkBox.isChecked());
            // item l'ho messo al di fuori di onCreate per poterlo usare
            if (item != null) {
                item.setText(editText.getText().toString());
                item.setStato(checkBox.isChecked());
                myIntent.putExtra(EXTRA_ITEM, item);
            }
            // mando result solo se RESULT_OK
            setResult(Activity.RESULT_OK, myIntent);
            finish();
        });


        // serve quando "click su Oggetto RV"
        // cambio stato 'item sopra' in base ai set che do
        if (item!=null) {
            editText.setText(item.getTesto());
            checkBox.setChecked(item.isStato());
        }


    }


}
