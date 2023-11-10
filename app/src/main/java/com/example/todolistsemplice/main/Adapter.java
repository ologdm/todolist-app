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

    // 1. list
    List<Item> listAdapter = new ArrayList<>();


    // costruttore
    // settaggio Dimalistener
    public Adapter(DimaListener lambda) {
        this.myDimaListener = lambda;

        listAdapter.add(new Item("hello1", false, 0));
        listAdapter.add(new Item("hello2", false, 1));
        listAdapter.add(new Item("hello3", false, 2));
        listAdapter.add(new Item("hello4", false, 4));
    }


    // 2. Add nuovoElemento e aggiungi ID che dovro passare su MainActivity.onActivityResults();
    public void addAndUpdate(String testo, boolean stato) {
        //creazione ID - paragono
        int idMax = 0;
        for (int i = 0; i < listAdapter.size(); i++) {
            Item item = listAdapter.get(i);
            if (item.getID() > idMax) {
                idMax = item.getID();
            }
        }

        Item item = new Item(testo, stato, idMax + 1);
        listAdapter.add(item);
        listAdapter.add(item);
        listAdapter.add(item);
        listAdapter.add(item);

        notifyDataSetChanged();
    }

    // input (testo, stato, id)
    public void setAndUpdate(String testo, boolean stato, int ID) {
        // nuovi elementi aggiunti di defalult

        for (int i = 0; i < listAdapter.size(); i++) {
            Item item = listAdapter.get(i); // prendo elementi dalla mia lista per check
            if (ID == item.getID()) {   // se ID nuovo elemento == ID gia esistente in lista
                //allora sovrascrivi testo e stato del item esistente
                item.setText(testo);
                item.setStato(stato);
                // id non devo aggiornarlo
                //listAdapter. ????;
            }
        }
        // aggiungi l'elemento inserito sopra n volte
        notifyDataSetChanged();
    }


    // 3.1
    @NonNull @Override
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
        Item item = listAdapter.get(position);
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
        return listAdapter.size();
    }


    // ## IMPLEMENTAZIONE CLICK SU OGGETTO ##

    // 1. Interfaccia NomeListener
    public interface DimaListener {
        void onClick(Item item);
    }

    // 2. dove verrà salvata l'istanza
    final private DimaListener myDimaListener;
    // 3. istanzio con il costruttore, ma potrei creare un setListener anche

    

}

