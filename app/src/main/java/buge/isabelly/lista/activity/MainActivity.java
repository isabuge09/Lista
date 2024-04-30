package buge.isabelly.lista.activity;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import buge.isabelly.lista.R;
import buge.isabelly.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST){
            if (resultCode == Activity.RESULT_OK) {
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                MyItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();

                itens.add(myItem);
            }
        }
    }
}