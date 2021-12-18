package com.df.tallerapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.df.tallerapi.Models.Personajes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;

public class AdapterPersonajes extends RecyclerView.Adapter<AdapterPersonajes.ViewHolder> {
    private ArrayList<Personajes> dataset;
    private Context context;

    public AdapterPersonajes(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_aplicacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Personajes r = dataset.get(position);
        holder.textViewName.setText(r.getName());

        Glide.with(context)
                .load("https://rickandmortyapi.com/api/character/avatar/" + r.getNumber() + ".jpeg")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgCharacter);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPersonajes(ArrayList<Personajes> listaPersonajes) {
        dataset.addAll(listaPersonajes);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCharacter;
        private TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);

            imgCharacter = (ImageView) itemView.findViewById(R.id.imageCharacter);
            textViewName = (TextView) itemView.findViewById(R.id.textView);
        }
    }

}
