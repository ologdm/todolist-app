package com.example.todolistsemplice.mainactivity.detailsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todolistsemplice.R;
import com.example.todolistsemplice.repository.Item;


public class DetailsFragment extends Fragment {

    // fff
    public static final String EXTRA_TESTO = "testo da aggiungere";
    public static final String EXTRA_STATO = "stato da agiungere";
    public static final String EXTRA_ITEM = "item da visualizzare";
    public static final String EXTRA_ID = "ID da agiungere";

    Button buttonBack;
    Button buttonSave;
    EditText editText;
    CheckBox checkBox;

    private Item item = null;


    //Factory - OK
    public static DetailsFragment create(Item item) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ITEM, item);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }


    //1. CREAZIONE VIEW - OK
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }


    // 2. LOGICA
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // assegnazione ok
        buttonBack = view.findViewById(R.id.buttonBack);
        buttonSave = view.findViewById(R.id.buttonSave);
        editText = view.findViewById(R.id.etTesto);
        checkBox = view.findViewById(R.id.ckBox);


        // TODO - Ricezione item
        // getArguments() - da chi mi ha creato
        // getArguments() non è mai nullo (def factory,  l'item puo essere nullo)
        // getSerializable() puo ritornare nullo
        // creo item solo se ho key
        item = (Item) getArguments().getSerializable(EXTRA_ITEM);
        if (item != null) {

            editText.setText(item.getTesto());
            checkBox.setChecked(item.isStato());
        }


        // devo settare 2 stati, nuovo elemento + esistente
        buttonSave.setOnClickListener(v -> {
            // key manager - "keyManager01"

            Bundle bundle = new Bundle();

            bundle.putString(EXTRA_TESTO, editText.getText().toString());
            bundle.putBoolean(EXTRA_STATO,  checkBox.isChecked());

            if (item != null) {
                bundle.putInt(EXTRA_ID,  item.getID());
            }

            getParentFragmentManager()
                .setFragmentResult("keyManager01", bundle);


            getActivity().onBackPressed();
            // getParentFragmentManager().popBackStack(); -
        });



        buttonBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }
}
