package buge.isabelly.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import buge.isabelly.lista.R;
import buge.isabelly.lista.activity.MainActivity;
import buge.isabelly.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    //o construtor da classe myAdapter
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);//obtemos um inflador de layouts
        View v = inflater.inflate(R.layout.item_list,parent,false);//cria os elementos de interface referentes a um item usando o inflador e guardamos dentro da View
        return new MyViewHolder(v);//retorna a View atraves da funcao
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//preenche a interface com os dados de um item
        MyItem myItem = itens.get(position);//obtemos o item que vai ser usado para preencher a interface
        View v = holder.itemView;//obtemos a view que esta dentro do viewholder

        //preenchemos a interface com os dados do item
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageBitmap(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView  tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.description);

    }

    //metodo que retorna a quantidade de itens que tem na lista
    @Override
    public int getItemCount() {
        return itens.size();
    }
}
