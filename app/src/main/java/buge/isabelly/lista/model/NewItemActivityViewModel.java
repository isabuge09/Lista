package buge.isabelly.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {

    Uri selectedPhotoLocation = null;//guarda o endereco URI da foto escolhida

    //obtem a lista de itens
    public Uri getSelectedPhotoLocation() {
        return selectedPhotoLocation;
    }

    //seta o endereco URI dentro do ViewModel
    public void setSelectedPhotoLocation(Uri selectedPhotoLocation) {
        this.selectedPhotoLocation = selectedPhotoLocation;
    }
}
