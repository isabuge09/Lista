package buge.isabelly.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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

        Button btnAddItem = findViewById(R.id.btnAddItem);//obtemos o botao pelo id
        btnAddItem.setOnClickListener(new View.OnClickListener() {//obtemos o click do botao
            @Override
            public void onClick(View v) {//metodo executado quando apos o click do botao

                //verifica se os campo de imagem foi preenchido pelo usuario, caso nao esteja mostra-se uma mensagem de erro
                if(photoSelected == null){
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!",  Toast.LENGTH_LONG).show();
                    return;
                }

                //verifica se os campo de titulo foi preenchido pelo usuario, caso nao esteja mostra-se uma mensagem de erro
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título!", Toast.LENGTH_LONG).show();
                    return;
                }

                //verifica se os campo de descricao foi preenchido pelo usuario, caso nao esteja mostra-se uma mensagem de erro
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição!", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();//cria o intent
                i.setData(photoSelected);//setamos o Uri da imagem escolhida
                i.putExtra("title", title);//setamos o titulo escolhido
                i.putExtra("description", description);//setamos a descricao escolhida
                setResult(Activity.RESULT_OK, i);//indica resultado da Activity
                finish();//finaliza a Activity
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST){//verifica se requestCode e referente ao fornecido na startActiviyForResult
            if(resultCode == Activity.RESULT_OK) {//verificamos se resultCode é um codigo bem sucedido
                photoSelected = data.getData();//resultado caso as condicoes seja verdadeiras
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);//obtem o Uri da imagem e guarda dentro do atributo de classe photoSelected
                imvfotoPreview.setImageURI(photoSelected);//resultado caso as condicoes seja verdadeiras
            }
        }
    }


}