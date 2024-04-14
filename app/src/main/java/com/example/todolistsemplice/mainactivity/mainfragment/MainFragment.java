package com.example.todolistsemplice.mainactivity.mainfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistsemplice.R;
import com.example.todolistsemplice.mainactivity.Navigator;
import com.example.todolistsemplice.mainactivity.detailsfragment.DetailsFragment;
import com.example.todolistsemplice.repository.Item;


import java.util.List;

// TODO
// 1. creazione view
// 2. assegnazione elementi interfaccia
// 3. context - getActivity() o requireActivity();
// 4. lancia detailed per aggiunta nuovo elemento


public class MainFragment extends Fragment implements Contract.View {


    final public static int INVALID_ID = -1;

    // ************+!!!!!!!!!
    // -> non decvo cacharla, crasha perche non ho activity ancora nel fragment
    //FragmentActivity frActivity = requireActivity();


    // DICHIARAZIONE - OK
    RecyclerView recyclerView;
    Button buttonAdd;
    Navigator navigator = new Navigator();

    Contract.Presenter presenter;


    // CLICK ITEM - OK
    Adapter adapter = new Adapter(item -> {
        presenter.onItemClick(item);
    });


    // 1. CREAZIONE VIEW - OK
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // 2. LOGICA
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // 2.1
        presenter = new MainPresenter(this, getContext());


        // 2.2 - Assegnazione
        recyclerView = view.findViewById(R.id.RVFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        buttonAdd = view.findViewById(R.id.buttonAdd);


        // 2.3 Add - OK
        buttonAdd.setOnClickListener(view1 -> {
            presenter.onAddButtonClick();
        });


        // 2.6 - Salva stato ChecBbox (modificato da Adapter RV)
        adapter.setCheckboxListener(new Adapter.CheckboxListener() {
            @Override
            public void esegui(Item item) {
                presenter.setItem(item);
            }
        });


        // 2.4 Aggiorna Adapter da Repository all'inizio
        presenter.updateUiList();


        // 2.5
        getDataFromDetails();


        //adapter.salvaStatoCheckBox(); // TODO con funzione diretta


    }



    // CONTRACT METHODS

    // 1° METODO OK
    @Override
    public void updateUi(List<Item> list) {
        adapter.updateList(list);
    }


    // 2° METODO OK
    @Override
    public void startSecondActivity(Item item) {
        if (item == null) {
            // creo Fragment vuoto
            DetailsFragment detailsFragment = DetailsFragment.create(null);
            navigator.backstackPush(requireActivity(), detailsFragment);
        } else {
            //Creo Fragment con item
            DetailsFragment detailsFragment = DetailsFragment.create(item);
            navigator.backstackPush(requireActivity(), detailsFragment);
        }
    }
    // ####################



    // FragmentResult
    private void getDataFromDetails() {
        // key manager - "keyManager01"

        getParentFragmentManager()
            .setFragmentResultListener(
                "keyManager01",
                this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String testo = result.getString(DetailsFragment.EXTRA_TESTO);
                        boolean stato = result.getBoolean(DetailsFragment.EXTRA_STATO);
                        // INVALID_ID = -1 (definito da me)
                        int id = result.getInt(DetailsFragment.EXTRA_ID, INVALID_ID);

                        // Decido se usare addItem, setItem
                        if (id == INVALID_ID) {
                            presenter.addItem(testo, stato);
                        } else {
                            presenter.setItem(new Item(testo, stato, id));
                        }
                    }
                });
    }



}



// Eugi Argomenti
// android ViewModel -> pattern MVVM
//                      livedata (che implemeta il pattern observable)

