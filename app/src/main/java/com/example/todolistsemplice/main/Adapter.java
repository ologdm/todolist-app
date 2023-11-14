package com.example.todolistsemplice.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistsemplice.model.Item;
import com.example.todolistsemplice.R;

import java.util.ArrayList;
import java.util.List;

//  1. TODO aggiungere persistenza elementi aggiunti per quando giro lo schermo
//  2. callback per click su cambio stato di checkBoxSuRV


public class Adapter extends RecyclerView.Adapter<TodoViewholder> {
    // Adapter e Viewholder sono una static inner class di RecycleView, per quello c'e il punto
    // Adapter <accetta solo Tipo Viewholder>


    List<Item> itemList = new ArrayList<>();


    // costruttore
    // settaggio Dimalistener per click Item
    public Adapter(DimaListener lambda) {
        this.myDimaListener = lambda;
    }


    // 3.1
    @NonNull
    @Override
    public TodoViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //standar inflater - trasformatore da xml a view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //view da inflater
        View myView = inflater.inflate(R.layout.activity_todoviewholder, parent, false);
        return new TodoViewholder(myView);
    }

    // 3.2
    // TODO  onBind si può spostare su VH per ordinare meglio il codice
    @Override
    public void onBindViewHolder(@NonNull TodoViewholder holder, int position) {
        // 1
        Item item = itemList.get(position);
        // 2
        TextView textView = holder.itemView.findViewById(R.id.etTodoViewholder);
        CheckBox checkBox = holder.itemView.findViewById(R.id.ckboxTodoViewholder);


        // 3 callback per click su cambio stato di checkBoxSuRV
        // Listener specifico che si usa anche per switch, radiobutton
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                item.setStato(isChecked);
                // l'item gia lavora sul riferimento di codice necessario per la modifica
            }
        });


        // 4 !!!
        // l'aggiornamento degli elementi della view si devonno fare dopo il set
        // (non si sa perche ma altrimenti se scorro gli elementi ritornano nel loro
        // stato di default)
        textView.setText(item.getTesto());
        checkBox.setChecked(item.isStato());


        // 5  """"IMPLEMENTAZIONE CLICK SU OGGETTO""""""
        // funzione base
        // implemento l'interfaccia funzionale e gli passo nel corpo {la mia callback}

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDimaListener.onClick(item);
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    // ## IMPLEMENTAZIONE CLICK SU OGGETTO ##

    // 1. Interfaccia NomeListener
    public interface DimaListener {
        void onClick(Item item);
    }

    // 2. dove verrà salvata l'istanza
    final private DimaListener myDimaListener;
    // 3. istanzio con il costruttore, ma potrei creare un setListener anche




    // **** 2°PARTE MVP ****

    // prendo i dati da updateUi <- presenter <- repository
    public void updateList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged(); // metodo di adapter
    }


}

