package com.example.practica1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderMensaje extends RecyclerView.ViewHolder {

    TextView nombre;
    TextView mensaje;
    TextView hora;


    public HolderMensaje(@NonNull View itemView) {
        super(itemView);

        nombre = (TextView) itemView.findViewById(R.id.nombretarjeta);
        hora = (TextView) itemView.findViewById(R.id.horatarjeta);
        mensaje = (TextView) itemView.findViewById(R.id.mensajetarjeta);
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }


}
