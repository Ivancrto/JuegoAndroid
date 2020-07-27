package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.practica1.Clases.ListQuestion;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class Q5 extends AppCompatActivity {

    private TextView txtPunt5,txtPreg5,txtp;
    private Button btnSalir5, btnSiguiente5;
    private ImageButton imgBtn1,imgBtn2,imgBtn3,imgBtn4;
    private ConstraintLayout FondoQ5;
    private boolean usado =false;
    private int pregunta,punt;
    private int p;
    private ToggleButton b;


    SoundPool spE, spC;
    int sonidoError, sonidoCorrecto;

    ListQuestion lq = new ListQuestion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q5);

        FondoQ5 = findViewById(R.id.fondoQ5);


        //Randomizador de preguntas
        pregunta = (int) ( Math.random() * 2 + 10);

        txtPunt5 = findViewById(R.id.txtN5);
        txtp = findViewById(R.id.tpunt5);
        txtPreg5 = findViewById(R.id.txtPreg5);

        b = findViewById(R.id.tb5);
        if(!Play_Stop){
            b.setChecked(false);
        }

        btnSalir5 =findViewById(R.id.btnExit5);
        btnSiguiente5 = findViewById(R.id.btnNext5);
        imgBtn1 = findViewById(R.id.imgB1);
        imgBtn2 = findViewById(R.id.imgB2);
        imgBtn3 = findViewById(R.id.imgB3);
        imgBtn4 = findViewById(R.id.imgB4);
        btnSiguiente5.setVisibility(View.INVISIBLE);

        spE = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spC = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoError = spE.load(this, R.raw.incorrecto, 1);
        sonidoCorrecto = spC.load(this, R.raw.correcto, 1);
        RellenarPreferencia();

        //Dependiendo del valor del randomizador sale una pregunta u otra
        switch (pregunta){
            case 10:
                txtPreg5.setText("¿Cúal de estas imagenes corresponde a un cuadro de Velázquez?");

                imgBtn1.setImageResource(R.drawable.foto1_1);
                imgBtn2.setImageResource(R.drawable.foto1_2);
                imgBtn3.setImageResource(R.drawable.foto1_3);
                imgBtn4.setImageResource(R.drawable.foto1_4);
                break;

            case 11:
                txtPreg5.setText("¿Cúal de estas imagenes pertenece a una arquitectura griega??");
                imgBtn1.setImageResource(R.drawable.foto2_1);
                imgBtn2.setImageResource(R.drawable.foto2_2);
                imgBtn3.setImageResource(R.drawable.foto2_3);
                imgBtn4.setImageResource(R.drawable.foto2_4);
                break;

        }

        btnSalir5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListQuestion.setPos((int)Math.random()*5 + 2);
                Salir();
                finish();
            }
        });

        btnSiguiente5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usado) {
                    Lanzar();
                    finish();
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        p = bundle.getInt("Punt5");
        String s = bundle.getString("Punt5");
        txtPunt5.setText(s);


        //Controlador de botones, si es correcto el boton que se pulsa suma 3 puntos, sino resta 2.

        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usado) {
                    switch(pregunta){
                        case 10:
                            punt = ListQuestion.Puntuacion.getPunt();
                            punt = punt - 2;
                            ListQuestion.Puntuacion.setPunt(punt);
                            txtPunt5.setText(Integer.toString(punt));
                            crearToast(false);
                            spE.play(sonidoError, 1, 1, 1, 0 ,0);
                            usado = true;

                            break;
                        case 11:
                            punt = ListQuestion.Puntuacion.getPunt();
                            punt = punt + 3;
                            ListQuestion.Puntuacion.setPunt(punt);
                            txtPunt5.setText(Integer.toString(punt));
                            spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                            crearToast(true);
                            usado = true;
                            break;
                    }
                }
                btnSiguiente5.setVisibility(View.VISIBLE);

            }
        });

        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usado) {
                    switch(pregunta){
                        case 10:
                            punt = ListQuestion.Puntuacion.getPunt();
                            punt = punt + 3;
                            ListQuestion.Puntuacion.setPunt(punt);
                            txtPunt5.setText(Integer.toString(punt));
                            usado=true;
                            spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                            crearToast(true);
                            break;
                        case 11:
                            punt = ListQuestion.Puntuacion.getPunt();
                            punt = punt - 2;
                            ListQuestion.Puntuacion.setPunt(punt);
                            txtPunt5.setText(Integer.toString(punt));
                            spE.play(sonidoError, 1, 1, 1, 0 ,0);
                            usado=true;
                            crearToast(false);
                            break;
                    }
                }
                btnSiguiente5.setVisibility(View.VISIBLE);
            }
        });

        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usado) {
                    punt = ListQuestion.Puntuacion.getPunt();
                    punt = punt - 2;
                    ListQuestion.Puntuacion.setPunt(punt);
                    txtPunt5.setText(Integer.toString(punt));
                    spE.play(sonidoError, 1, 1, 1, 0 ,0);
                    usado =true;
                    crearToast(false);
                }
                btnSiguiente5.setVisibility(View.VISIBLE);

            }
        });

        imgBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usado) {
                    punt = ListQuestion.Puntuacion.getPunt();
                    punt = punt - 2;
                    ListQuestion.Puntuacion.setPunt(punt);
                    txtPunt5.setText(Integer.toString(punt));
                    spE.play(sonidoError, 1, 1, 1, 0 ,0);
                    usado = true;
                    crearToast(false);
                }
                btnSiguiente5.setVisibility(View.VISIBLE);
            }
        });
    }

    //Vuelve a la actividad inicial
    public void Salir (){
        Intent i = new Intent (this,MainActivity.class);

        startActivity(i);
    }

    //Pasa a la siguiente actividad
    public void Lanzar (){
        txtPunt5.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));
        Intent i = new Intent (this, Q6.class);
      //  Intent i = new Intent (this, QFinal.class);
        i.putExtra("Punt5",txtPunt5.getText().toString());
        startActivity(i);
    }
    public void crearToast(boolean b){
        if(b){
            Toast.makeText(this,"Correcto, Puntuacion: "+ ListQuestion.Puntuacion.getPunt(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Incorrecto, Puntuacion: "+ ListQuestion.Puntuacion.getPunt(), Toast.LENGTH_SHORT).show();
        }

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

                FondoQ5.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoQ5.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoQ5.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtp.setTextColor(Color.BLACK);
                txtPunt5.setTextColor(Color.BLACK);
                txtPreg5.setTextColor(Color.BLACK);
                btnSalir5.setTextColor(Color.BLACK);
                btnSiguiente5.setTextColor(Color.BLACK);

                break;
            case "Naranja":
                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPunt5.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPreg5.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSalir5.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSiguiente5.setTextColor(Color.parseColor("#FFDF8A44"));

                break;
            case "Violeta":

                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                txtPunt5.setTextColor(Color.parseColor("#FF971A9D"));
                txtPreg5.setTextColor(Color.parseColor("#FF971A9D"));
                btnSalir5.setTextColor(Color.parseColor("#FF971A9D"));
                btnSiguiente5.setTextColor(Color.parseColor("#FF971A9D"));

                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":

                txtp.setTextSize(14);
                txtPunt5.setTextSize(14);
                txtPreg5.setTextSize(18);
                btnSalir5.setTextSize(14);
                btnSiguiente5.setTextSize(14);
                break;

            case "Medio":

                txtp.setTextSize(16);
                txtPunt5.setTextSize(16);
                txtPreg5.setTextSize(21);
                btnSalir5.setTextSize(16);
                btnSiguiente5.setTextSize(16);
                break;

            case "Grande":

                txtp.setTextSize(18);
                txtPunt5.setTextSize(18);
                txtPreg5.setTextSize(24);
                btnSalir5.setTextSize(18);
                btnSiguiente5.setTextSize(18);
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

}
