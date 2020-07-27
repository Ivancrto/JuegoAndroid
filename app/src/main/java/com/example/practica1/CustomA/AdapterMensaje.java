package com.example.practica1.CustomA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica1.HolderMensaje;
import com.example.practica1.Mensaje;
import com.example.practica1.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMensaje extends RecyclerView.Adapter<HolderMensaje> {

    List<Mensaje> listaMensaje = new ArrayList<Mensaje>();
    Context context;

    public AdapterMensaje(Context context) {
        this.context = context;
    }

    public void añadirMensaje(Mensaje m){
        listaMensaje.add(m);
        notifyItemInserted(listaMensaje.size());
    }

    @NonNull
    @Override
    public HolderMensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tarjetamensaje, parent,false);
        return new HolderMensaje(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensaje holder, int position) {
        holder.getNombre().setText(listaMensaje.get(position).getNombre());
        holder.getMensaje().setText(listaMensaje.get(position).getMensaje());
        holder.getHora().setText(listaMensaje.get(position).getHora());

    }

    @Override
    public int getItemCount() {
        return listaMensaje.size();
    }
}
