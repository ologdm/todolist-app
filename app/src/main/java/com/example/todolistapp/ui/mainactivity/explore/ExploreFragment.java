package com.example.todolistapp.ui.mainactivity.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.R;
import com.example.todolistapp.ui.mainactivity.MainNavigator;
import com.example.todolistapp.ui.mainactivity.detail.DetailFragment;
import com.example.todolistapp.data.Item;
import com.example.todolistapp.ui.mainactivity.explore.adapter.ExploreAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;


public class ExploreFragment extends Fragment implements ExploreContract.View {

    final public static int INVALID_ID = -1;

    RecyclerView recyclerView;
    FloatingActionButton buttonAdd;
    MainNavigator navigator = new MainNavigator();
    ExploreContract.Presenter presenter;

    ExploreAdapter adapter = new ExploreAdapter(
        item -> {
            presenter.setOnClick(item); // view.startDetailFragment()
        },
        item -> {
            presenter.setItem(item);
        });


    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    ) {
        return inflater
            .inflate(R.layout.fragment_explore_list, container, false);
    }


    @Override
    public void onViewCreated(
        @NonNull View view,
        @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ExplorePresenter(this, getContext()); // init presenter

        recyclerView = view.findViewById(R.id.recycler_view);
        buttonAdd = view.findViewById(R.id.add_button);


        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        buttonAdd.setOnClickListener(view1 -> {
            presenter.addOnClick(); // -> view.startDetailFragment()
        });

        presenter.updateUiList();
        getDataFromDetails();
    }


    // FragmentListener - fragment result
    private void getDataFromDetails() {
        // key manager - "keyManager01"
        getParentFragmentManager().setFragmentResultListener(
            "key_manager_01",
            this,
            new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    String todoText = result.getString(DetailFragment.EXTRA_TESTO);
                    boolean todoState = result.getBoolean(DetailFragment.EXTRA_STATO);
                    // INVALID_ID = -1 (definito da me)
                    int id = result.getInt(DetailFragment.EXTRA_ID, INVALID_ID);

                    if (id == INVALID_ID) {
                        presenter.addItem(todoText, todoState);
                    } else {
                        presenter.setItem(new Item(todoText, todoState, id));
                    }
                }
            });
    }


    // CONTRACT METHODS
    @Override
    public void updateUi(List<Item> list) {
        adapter.updateList(list);
    }


    // ->| addOnClick(); setOnClick(Item item)
    @Override
    public void startDetailFragment(Item item) {
        DetailFragment detailFragment;
        if (item == null) {
            detailFragment = DetailFragment.create(null);
        } else {
            detailFragment = DetailFragment.create(item);
        }
        navigator.addToBackstack(requireActivity(), detailFragment);
    }


}


