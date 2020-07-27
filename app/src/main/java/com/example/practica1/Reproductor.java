package com.example.practica1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.practica1.Clases.Song;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;
import static com.example.practica1.principalLogin.positionSong;
import static com.example.practica1.principalLogin.songs;

public class Reproductor extends AppCompatActivity {

    private Button btnSalirR;
    private ConstraintLayout fondoReproductor;
    private ToggleButton b;
    private static TextView txtGrupo;
    private static TextView txtCancion;
    private TextView txtSonando;
    private Button btnSalir;
    private static ImageView imgSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);


        txtGrupo = findViewById(R.id.txtGroupName);
        txtCancion = findViewById(R.id.txtSongName);
        imgSong = findViewById(R.id.imgSong);
        b = findViewById(R.id.tbReproductor);
        txtSonando = findViewById(R.id.txtSonando);
        btnSalir = findViewById(R.id.btnExitReproductor);

        txtGrupo.setText(songs.get(positionSong).getGroupName());
        txtCancion.setText(songs.get(positionSong).getSongName());
        imgSong.setImageBitmap(songs.get(positionSong).getImgLink());



        if(!Play_Stop){
            b.setChecked(false);
        }

        btnSalirR = findViewById(R.id.btnExitReproductor);
        fondoReproductor = findViewById(R.id.fondoReproductor);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerReproductor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        RellenarPreferencia();
        AdapterReproductor adapterReproductor = new AdapterReproductor(this, songs) {



            @Override
            public int getItemCount() {

                return songs.size();
            }
        };
        recyclerView.setAdapter(adapterReproductor);
    }

    public void Salir (View v){
        Intent i = new Intent (this,MainActivity.class);
        startActivity(i);
    }

    public void RellenarPreferencia(){
        String fondo = Preferencia.getFondo();
        String color = Preferencia.getColorTexto();
        String tamaño = Preferencia.getTamañoTexto();
        RellenarFondo(fondo);
        RellenarColorTexto(color);
        RellenarTamañoTexto(tamaño);

    }
    public void RellenarFondo(String fondo){
        switch (fondo){
            case "Verde":

                fondoReproductor.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                fondoReproductor.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                fondoReproductor.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
        }
    }

    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":

                txtGrupo.setTextColor(Color.BLACK);
                txtCancion.setTextColor(Color.BLACK);
                txtSonando.setTextColor(Color.BLACK);
                btnSalir.setTextColor(Color.BLACK);

                break;
            case "Naranja":

                txtGrupo.setTextColor(Color.parseColor("#FFDF8A44"));
                txtCancion.setTextColor(Color.parseColor("#FFDF8A44"));
                txtSonando.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSalir.setTextColor(Color.parseColor("#FFDF8A44"));

                break;
            case "Violeta":

                txtGrupo.setTextColor(Color.parseColor("#FF971A9D"));
                txtCancion.setTextColor(Color.parseColor("#FF971A9D"));
                txtSonando.setTextColor(Color.parseColor("#FF971A9D"));
                btnSalir.setTextColor(Color.parseColor("#FF971A9D"));

                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":
                txtSonando.setTextSize(32);


                break;
            case "Medio":
                txtSonando.setTextSize(26);


                break;
            case "Grande":
                txtSonando.setTextSize(40);


                break;
            default:
                break;
        }
    }


    public void Play_StopMusic(View v){
        if(music.isPlaying()){
            Play_Stop=false;
            music.pause();
        }else{
            Play_Stop=true;
            music.play();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.pause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(Play_Stop){
            music.play();
        }

    }







    public abstract static class AdapterReproductor extends RecyclerView.Adapter{
        ArrayList<Song> songNames;
        Context context;




        public AdapterReproductor(Context context, ArrayList songNames){
            this.context=context;
            this.songNames =songNames;


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
            ((MyViewHolder)holder).name.setText((String) songNames.get(position).getSongName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(Play_Stop) {
                        try {
                            music.dispose();
                            AssetManager am = context.getAssets();
                            AssetFileDescriptor afd = am.openFd(songNames.get(position).getSongLink());
                            music = new GameMusic(afd);
                            music.setLooping(true);
                        } catch (IOException e) {

                        }
                        Toast.makeText(context, "Iniciando: "+ songNames.get(position).getSongName(),Toast.LENGTH_SHORT).show();
                        txtGrupo.setText(songNames.get(position).getGroupName());
                        txtCancion.setText(songNames.get(position).getSongName());
                        imgSong.setImageBitmap(songs.get(position).getImgLink());
                        positionSong = position;
                        music.play();

                    }
                    else{
                        Toast.makeText(context, "Activa el sonido antes de elegir",Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }


    }
}
