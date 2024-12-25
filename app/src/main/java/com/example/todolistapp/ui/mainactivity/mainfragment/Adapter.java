package com.example.todolistapp.ui.mainactivity.mainfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.data.TodoItem;
import com.example.todolistapp.R;

import java.util.ArrayList;
import java.util.List;

//  1. TODO aggiungere persistenza elementi aggiunti per quando giro lo schermo
//  2. callback per click su cambio stato di checkBoxSuRV


public class Adapter extends RecyclerView.Adapter<TodoViewholder> {
    // Adapter e Viewholder sono una static inner class di RecycleView, per quello c'e il punto
    // Adapter <accetta solo Tipo Viewholder>


    List<TodoItem> itemList = new ArrayList<>();


    // costruttore
    // settaggio Dimalistener per click Item
    public Adapter(DimaListener listener) {
        this.myDimaListener = listener;
    }


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
        TodoItem item = itemList.get(position);
        // 2
        TextView textView = holder.itemView.findViewById(R.id.etTodoViewholder);
        CheckBox checkBox = holder.itemView.findViewById(R.id.ckboxTodoViewholder);


        // TODO 3. SetListener per click su cambio stato di checkBoxSuRV ( anche Switch,RadioButton)
        // Listener che cambia stato solo su RV, non salva in repository
        checkBox.setOnCheckedChangeListener((view, isChecked) -> {
            if (isChecked != item.isStato()) {
                // setStato Item
                item.setStato(isChecked);

                // !!!!!!!!! salva item // -> metodo Adapter
                salvaStatoCheckbox(item);
            }


            // setOnCheckedChangeListene -ha un bug, lo fa per ogni elemento, quindi e da segnare
            // if (isChecked != item.isStato()) cos
            // il programma va in loop e crasha perche troppe volte sì aggiorna la Ui

        });


        // 4 !!!
        // l'aggiornamento degli elementi della view si devonno fare dopo il set
        // (non si sa perche ma altrimenti se scorro gli elementi ritornano nel loro
        // stato di default)
        textView.setText(item.getTesto());
        checkBox.setChecked(item.isStato());


        // 5  """"IMPLEMENTAZIONE CLICK SU OGGETTO""""""
        // holder -> contenitore view, contiene la itemView
        // 1) myDimaListener.onClick() - def. su Activity (serve intent o arguments)
        // 2) OnClickListener implementata invece qua, verrà passata
        // alla funzione .onTouchEvent() della view di button
        holder.itemView.setOnClickListener(v -> myDimaListener.onClick(item));
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    // ###  IMPLEMENTAZIONE CLICK SU OGGETTO  ###
    public interface DimaListener {
        void onClick(TodoItem item);
    }

    final private DimaListener myDimaListener;


    // #####  MVP   ######
    // prendo i dati da updateUi <- presenter <- repository
    public void updateList(List<TodoItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }


    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // CALLBACK PER CHECKBOX SU MAIN
    public interface CheckboxListener {
        void esegui(TodoItem item);
    }

    private CheckboxListener checkboxListener;

    public void setCheckboxListener(CheckboxListener listener) {
        checkboxListener = listener;
    }


    //funzione, solo per mascherare la callback
    private void salvaStatoCheckbox(TodoItem item) {
        checkboxListener.esegui(item);
    }


}

