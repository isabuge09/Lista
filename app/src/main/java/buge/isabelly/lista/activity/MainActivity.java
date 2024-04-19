package buge.isabelly.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import buge.isabelly.lista.R;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);//obtemos o botao FAB
        fabAddItem.setOnClickListener(new View.OnClickListener() {//registra o click no botao
            @Override
            public void onClick(View v) {//metodo executado apos o botao ser clicado
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);//cria intent explicito para navegar para newitemactivity
                startActivityForResult(i, NEW_ITEM_REQUEST);//executa o intent com o metodo startActivityForResult
            }
        });


    }
}