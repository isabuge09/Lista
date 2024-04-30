package buge.isabelly.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import buge.isabelly.lista.R;
import buge.isabelly.lista.activity.MainActivity;
import buge.isabelly.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);
        View v = holder.itemView;

        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageURI(myItem.photo);

        ImageView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        ImageView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.description);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
