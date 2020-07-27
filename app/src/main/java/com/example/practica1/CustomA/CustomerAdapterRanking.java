package com.example.practica1.CustomA;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica1.Clases.JugadorRanking;
import com.example.practica1.R;

import java.util.ArrayList;

public class CustomerAdapterRanking extends RecyclerView.Adapter {
    ArrayList<JugadorRanking> UserRanking;
    Context context;

    public CustomerAdapterRanking(Context context, ArrayList UserR){
        this.UserRanking=UserR;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).name.setText((String) UserRanking.get(position).getNickJ() + " " + UserRanking.get(position).getPuntos());
        holder.itemView.setOnClickListener( new View.OnClickListener(){
            public void onClick(View view){

            }
        });

    }

    @Override
    public int getItemCount() {
        return UserRanking.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        public MyViewHolder(View itemView){
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
        }
    }
}
