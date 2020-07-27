package com.example.practica1.CustomA;


import android.content.Context;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica1.Clases.ListQuestion;
import com.example.practica1.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class CustomAdapter extends RecyclerView.Adapter{

    ArrayList personNames;
    Context context;
    int p;
    int posCorrecta;
    TextView actualizarPuntos;
    SoundPool spE, spC;
    int sonidoError, sonidoCorrecto;



    public CustomAdapter(Context context, ArrayList personNames, int pR, TextView txtP,SoundPool spE1, SoundPool spC1,int sonidoError1,int sonidoCorrecto1){
        this.context=context;
        this.personNames=personNames;
        p = ListQuestion.Puntuacion.getPunt();
        posCorrecta = pR;
        actualizarPuntos = txtP;
        this.spE = spE1;
        this.spC = spC1;
        this.sonidoError = sonidoError1;
        this.sonidoCorrecto = sonidoCorrecto1;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public MyViewHolder(View itemView){
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, final int position){
        ((MyViewHolder)holder).name.setText((String) personNames.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==posCorrecta){
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                    ListQuestion.Puntuacion.setPunt(p+3);
                    Toast.makeText(context,"Correcto, Puntuacion: "+ ListQuestion.Puntuacion.getPunt(), Toast.LENGTH_SHORT).show();

                    //s=s+3;
                }
                else{
                    spE.play(sonidoError, 1, 1, 1, 0 ,0);
                    ListQuestion.Puntuacion.setPunt(p-2);
                    Toast.makeText(context,"Incorrecto, Puntuacion: "+ ListQuestion.Puntuacion.getPunt(), Toast.LENGTH_SHORT).show();

                    //s=s-2;
                }
                actualizarPuntos.setText(String.valueOf(ListQuestion.Puntuacion.getPunt()));
            }
        });
    }


}
