package buge.isabelly.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import buge.isabelly.lista.R;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        ImageButton imgCI = findViewById(R.id.imbCI);//obtemos o botao pelo seu id
        imgCI.setOnClickListener(new View.OnClickListener() {//obtem o click do botao
            @Override
            public void onClick(View v) {//metodo executado quando apos o click do botao
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);//cria de um Intent implicito que possui acao de abrir documento
                photoPickerIntent.setType("image/*");//especificamos que estamos interessados em documentos que sao imagens
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); //metodo que executa o Intent
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST){
            if(resultCode == Activity.RESULT_OK) {
                photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }
}