package buge.isabelly.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    List<MyItem> items = new ArrayList<>();//guarda a lista de itens cadastrados

    public List<MyItem> getItems() {
        return items;
    }//obtem a lista de itens
}
