package com.example.todolistapp.ui.mainactivity.detail;

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

import com.example.todolistapp.R;
import com.example.todolistapp.data.Item;

// TODO: 06/01/25 con viewmodel


public class DetailFragment extends Fragment {


    public static final String EXTRA_TESTO = "add_text";
    public static final String EXTRA_STATO = "add_state";
    public static final String EXTRA_ITEM = "add_item";
    public static final String EXTRA_ID = "add_id";

    Button buttonBack;
    Button buttonSave;
    EditText editText;
    CheckBox checkBox;

    private Item item = null;


    public static DetailFragment create(Item item) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ITEM, item);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }


    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    ) {
        return inflater
            .inflate(R.layout.fragment_detail_todo, container, false);
    }


    @Override
    public void onViewCreated(
        @NonNull View view,
        @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        item = (Item) getArguments().getSerializable(EXTRA_ITEM);
        if (item != null) {
            editText.setText(item.getTesto());
            checkBox.setChecked(item.isStato());
        }


        buttonBack = view.findViewById(R.id.buttonBack);
        buttonSave = view.findViewById(R.id.buttonSave);
        editText = view.findViewById(R.id.etTesto);
        checkBox = view.findViewById(R.id.ckBox);


        buttonSave.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            // item can be partial o complete
            // need to define separately ev
            bundle.putString(EXTRA_TESTO, editText.getText().toString());
            bundle.putBoolean(EXTRA_STATO, checkBox.isChecked());
            if (item != null) {
                bundle.putInt(EXTRA_ID, item.getID());
            }

            getParentFragmentManager()
                .setFragmentResult("key_manager_01", bundle);


            getActivity().onBackPressed(); // torna all'attività di prima
            // getParentFragmentManager().popBackStack(); // alternativa
        });


        buttonBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }
}
