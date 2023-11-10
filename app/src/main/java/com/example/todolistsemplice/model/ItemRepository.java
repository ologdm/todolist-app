package com.example.todolistsemplice.model;
import java.util.ArrayList;
import java.util.List;

// deve contenere la lista degli elementi -> arraylist<Item> todoItems;
// metodo da usare -> repository.getTodoItems

@SuppressWarnings("unused")
public class ItemRepository {

    // ***** SINGLETON *****
    // 1 attributo
    private static ItemRepository instance;

    // 2 costruttore
    private ItemRepository() {
    }

    // 3 metodo istanza
    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepository();
        }
        return instance;
    }


    // *** MODEL ***
    // -> contiene i dati
    private List<Item> todoItems = new ArrayList<>();
    // metodo prendere dati
    public List<Item> getTodoItems() {
        return todoItems;
    }
}
