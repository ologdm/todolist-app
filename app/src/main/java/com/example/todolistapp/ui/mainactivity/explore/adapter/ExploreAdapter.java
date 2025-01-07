package com.example.todolistapp.ui.mainactivity.explore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.data.Item;
import com.example.todolistapp.R;

import java.util.ArrayList;
import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<TodoViewholder> {

    List<Item> itemList = new ArrayList<>();


    public ExploreAdapter(ItemListener itemListener, CheckboxListener checkboxListener) {
        this.myItemListener = itemListener;
        this.checkboxListener = checkboxListener;
    }


    @NonNull
    @Override
    public TodoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myView = inflater.inflate(R.layout.vh_explore_todo, parent, false);
        return new TodoViewholder(myView);
    }


    @Override
    public void onBindViewHolder(@NonNull TodoViewholder holder, int position) {
        Item item = itemList.get(position);

        TextView textView = holder.itemView.findViewById(R.id.todo_text);
        CheckBox checkBox = holder.itemView.findViewById(R.id.todo_checkbox);

        // callbacks
        holder.itemView.setOnClickListener(v ->
            myItemListener.onClick(item) // # call1
        );

        checkBox.setOnCheckedChangeListener((view, isChecked) -> {
            // setOnCheckedChangeListene -ha un bug, lo fa per ogni elemento, quindi e da segnare
            // if (isChecked != item.isStato()) cos
            // il programma va in loop e crasha perche troppe volte sì aggiorna la Ui
            if (isChecked != item.isStato()) {
                item.setStato(isChecked);
                checkboxListener.play(item); // #  call2
            }
        });
        // l'aggiornamento degli elementi della view si devonno fare dopo il set
        // (non si sa perche ma altrimenti se scorro gli elementi ritornano nel loro
        // stato di default)
        textView.setText(item.getTesto());
        checkBox.setChecked(item.isStato());

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    // my methods
    public void updateList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }


    // CALLBACKS #####################################
    public interface ItemListener {
        void onClick(Item item);
    }

    public interface CheckboxListener {
        void play(Item item);
    }

    private final ItemListener myItemListener;
    private final CheckboxListener checkboxListener;


}

