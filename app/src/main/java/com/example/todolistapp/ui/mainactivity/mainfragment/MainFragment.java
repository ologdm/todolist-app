package com.example.todolistapp.ui.mainactivity.mainfragment;

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

import com.example.todolistapp.R;
import com.example.todolistapp.ui.mainactivity.Navigator;
import com.example.todolistapp.ui.mainactivity.detailsfragment.DetailsFragment;
import com.example.todolistapp.data.TodoItem;


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
    RecyclerView recyclerView;
    Button buttonAdd;
    Navigator navigator = new Navigator();
    Contract.Presenter presenter;

    Adapter adapter = new Adapter(item -> {
        presenter.onItemClick(item);
    });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MainPresenter(this, getContext());

        recyclerView = view.findViewById(R.id.RVFragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        buttonAdd = view.findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(view1 -> {
            presenter.onAddButtonClick();
        });


        adapter.setCheckboxListener(new Adapter.CheckboxListener() {
            @Override
            public void esegui(TodoItem item) {
                presenter.setItem(item);
            }
        });


        presenter.updateUiList();
        getDataFromDetails();


        //adapter.salvaStatoCheckBox(); // TODO con funzione diretta


    }



    // CONTRACT METHODS
    @Override
    public void updateUi(List<TodoItem> list) {
        adapter.updateList(list);
    }


    @Override
    public void startSecondActivity(TodoItem item) {
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
                            presenter.setItem(new TodoItem(testo, stato, id));
                        }
                    }
                });
    }



}



// Eugi Argomenti
// android ViewModel -> pattern MVVM
//                      livedata (che implemeta il pattern observable)

