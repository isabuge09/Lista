package buge.isabelly.lista.activity;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import buge.isabelly.lista.R;
import buge.isabelly.lista.adapter.MyAdapter;
import buge.isabelly.lista.model.MainActivityViewModel;
import buge.isabelly.lista.model.MyItem;
import buge.isabelly.lista.util.Util;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    MyAdapter myAdapter;


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

        RecyclerView rvItens = findViewById(R.id.rvItens);//obtemos o RecyclerView
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItems();

        myAdapter = new MyAdapter(this, itens);//cria myAdapter
        rvItens.setAdapter(myAdapter);//seta myAdapter no RecyclerView

        rvItens.setHasFixedSize(true);//indica ao RecycleView que nao tem variação de tamanho entre os itens da lista

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);//cria gerenciador de layout do tipo linear
        rvItens.setLayoutManager(layoutManager);//seta gerenciador de layout no RecyclerView

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);//criam decorador para a lista
        rvItens.addItemDecoration(dividerItemDecoration);//setamos o decorador no RecyclerView
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verifica se as condicoes foram cumpridas
        if (requestCode == NEW_ITEM_REQUEST){
            if (resultCode == Activity.RESULT_OK) {
                //btemos os dados retornados por NewItemActivity e os guardamos dentro de myItem
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                MyItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();

                try {
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI, 100, 100);
                    myItem.photo = photo;
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }

                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                List<MyItem> itens = vm.getItems();

                itens.add(myItem);//adicionamos o item a uma lista de itens
                myAdapter.notifyItemInserted(itens.size()-1);// notifica o Adapter para que o novo item seja exibido na tela
            }
        }
    }

}