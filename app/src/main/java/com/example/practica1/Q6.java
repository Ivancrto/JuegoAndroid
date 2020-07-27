package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.example.practica1.Clases.ListQuestion;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class Q6 extends AppCompatActivity {

    private ConstraintLayout FondoQ6;
    VideoView video;
    MediaController mediaController;
    Button b1, b2, b3, b4;
    ToggleButton b;
    Button Siguiente, Salir;
    TextView puntuacion, txtp, txtPreg6, txtP6;
    //Audio correcto o error
    SoundPool spE, spC;
    int sonidoError, sonidoCorrecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q6);

        video = (VideoView) findViewById(R.id.videoView);
        mediaController = new MediaController(this);

        FondoQ6 = findViewById(R.id.fondoQ6);
        b = findViewById(R.id.tb6);
        b1 = findViewById(R.id.button16);
        b2 = findViewById(R.id.button26);
        b3 = findViewById(R.id.button36);
        b4 = findViewById(R.id.button46);
        Siguiente = findViewById(R.id.btnNext6);
        Salir = findViewById(R.id.btnExit6);
        txtp = findViewById(R.id.tpunt6);
        txtPreg6 = findViewById(R.id.txtPreg6);
        txtP6= findViewById(R.id.txtP6);
        puntuacion = findViewById(R.id.txtB6);
        puntuacion.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));

        b.setChecked(false);

        spE = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spC = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoError = spE.load(this, R.raw.incorrecto, 1);
        sonidoCorrecto = spC.load(this, R.raw.correcto, 1);
        RellenarPreferencia();
        //Primera pregunta video;
        video1();
        escribirBoton1();
        comprobar();



    }



    private void video1(){
        String path = "android.resource://com.example.practica1/" + R.raw.video1;
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);
        video.start();
    }
    private void escribirBoton1(){
        b1.setText("Harry Potter");
        b2.setText("Star Wars");
        b3.setText("El señor de los anillos");
        b4.setText("X-MEN");
    }
    public void comprobar(){


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundColor(Color.parseColor("#BF404F"));
                ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                spE.play(sonidoError, 1, 1, 1, 0, 0);
                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                puntuacion.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundColor(Color.parseColor("#37A456"));
                ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                puntuacion.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3.setBackgroundColor(Color.parseColor("#BF404F"));
                ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                spE.play(sonidoError, 1, 1, 1, 0, 0);
                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                puntuacion.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4.setBackgroundColor(Color.parseColor("#BF404F"));
                ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                spE.play(sonidoError, 1, 1, 1, 0, 0);
                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                puntuacion.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));
            }
        });

    }


    public void Salir (View view){
        Intent i = new Intent (this,MainActivity.class);
        b.setChecked(true);
        startActivity(i);
    }
    public void Lanzar (View view){
         Intent i = new Intent (this, QFinal.class);
        i.putExtra("Punt6",String.valueOf(ListQuestion.Puntuacion.getPunt()));
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

                FondoQ6.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoQ6.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoQ6.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtp.setTextColor(Color.BLACK);
                puntuacion.setTextColor(Color.BLACK);
                txtPreg6.setTextColor(Color.BLACK);
                Salir.setTextColor(Color.BLACK);
                Siguiente.setTextColor(Color.BLACK);
                b1.setTextColor(Color.BLACK);
                b2.setTextColor(Color.BLACK);
                b3.setTextColor(Color.BLACK);
                b4.setTextColor(Color.BLACK);
                txtp.setTextColor(Color.BLACK);
                txtP6.setTextColor(Color.BLACK);

                break;
            case "Naranja":
                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                puntuacion.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPreg6.setTextColor(Color.parseColor("#FFDF8A44"));
                Salir.setTextColor(Color.parseColor("#FFDF8A44"));
                Siguiente.setTextColor(Color.parseColor("#FFDF8A44"));
                b1.setTextColor(Color.parseColor("#FFDF8A44"));
                b2.setTextColor(Color.parseColor("#FFDF8A44"));
                b3.setTextColor(Color.parseColor("#FFDF8A44"));
                b4.setTextColor(Color.parseColor("#FFDF8A44"));
                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                txtP6.setTextColor(Color.parseColor("#FFDF8A44"));

                break;
            case "Violeta":

                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                puntuacion.setTextColor(Color.parseColor("#FF971A9D"));
                txtPreg6.setTextColor(Color.parseColor("#FF971A9D"));
                Salir.setTextColor(Color.parseColor("#FF971A9D"));
                Siguiente.setTextColor(Color.parseColor("#FF971A9D"));
                b1.setTextColor(Color.parseColor("#FF971A9D"));
                b2.setTextColor(Color.parseColor("#FF971A9D"));
                b3.setTextColor(Color.parseColor("#FF971A9D"));
                b4.setTextColor(Color.parseColor("#FF971A9D"));
                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                txtP6.setTextColor(Color.parseColor("#FF971A9D"));


                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":

                txtp.setTextSize(12);
                puntuacion.setTextSize(12);
                txtPreg6.setTextSize(18);
                Salir.setTextSize(12);
                Siguiente.setTextSize(12);
                b1.setTextSize(12);
                b2.setTextSize(12);
                b3.setTextSize(12);
                b4.setTextSize(12);
                txtp.setTextSize(12);
                txtP6.setTextSize(12);
                break;

            case "Medio":

                txtp.setTextSize(14);
                puntuacion.setTextSize(14);
                txtPreg6.setTextSize(21);
                Salir.setTextSize(14);
                Siguiente.setTextSize(14);
                b1.setTextSize(14);
                b2.setTextSize(14);
                b3.setTextSize(14);
                b4.setTextSize(14);
                txtp.setTextSize(14);
                txtP6.setTextSize(14);
                break;

            case "Grande":

                txtp.setTextSize(16);
                puntuacion.setTextSize(16);
                txtPreg6.setTextSize(24);
                Salir.setTextSize(16);
                Siguiente.setTextSize(16);
                b1.setTextSize(16);
                b2.setTextSize(16);
                b3.setTextSize(16);
                b4.setTextSize(16);
                txtp.setTextSize(16);
                txtP6.setTextSize(16);
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
        if(Play_Stop && b.isChecked()){
            music.play();
        }

    }

}
