package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.PreguntasSQLiteOpenHelper;
import com.example.practica1.Clases.ListQuestion;
import com.example.practica1.CustomA.CustomAdapter;

import java.util.ArrayList;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class Q3 extends AppCompatActivity {



    private TextView textV3,txtPunt3,txtp;
    private Button btnSalir3,btnSiguiente3;
    ArrayList preguntas;
    private ConstraintLayout FondoQ3;
    ListQuestion lq = new ListQuestion();
    private int pos3;
    PreguntasSQLiteOpenHelper admini;
    SQLiteDatabase BdD;
    Cursor fila;
    private ToggleButton b;


    SoundPool spE, spC;
    int sonidoError, sonidoCorrecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q3);

        FondoQ3 = findViewById(R.id.fondoQ3);


        txtPunt3 = findViewById(R.id.txtN3);
        btnSalir3 =findViewById(R.id.btnExit3);
        btnSiguiente3 = findViewById(R.id.btnNext3);
        textV3 = findViewById(R.id.txtPreg3);
        txtp = findViewById(R.id.tpunt3);

        b = findViewById(R.id.tb3);
        if(!Play_Stop){
            b.setChecked(false);
        }


        spE = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spC = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoError = spE.load(this, R.raw.incorrecto, 1);
        sonidoCorrecto = spC.load(this, R.raw.correcto, 1);


        btnSalir3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListQuestion.setPos((int)Math.random()*5 + 4);
                Salir();
                finish();
            }
        });

        btnSiguiente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lanzar();
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("Punt3");
        txtPunt3.setText(s);
        pos3 = bundle.getInt("pos3");

        admini = new PreguntasSQLiteOpenHelper(this, "preguntasBBDD", null, 2);
        BdD = admini.getWritableDatabase();
        fila = BdD.rawQuery("select * from preguntasBBDD where id ="+ pos3, null);
        fila.moveToFirst();
        preguntas = new ArrayList<>();
        preguntas.add(fila.getString(2));
        preguntas.add(fila.getString(3));
        preguntas.add(fila.getString(4));
        preguntas.add(fila.getString(5));
        textV3.setText(fila.getString(1));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewchat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        RellenarPreferencia();
        CustomAdapter customAdapter = new CustomAdapter(this, preguntas,fila.getInt(7), txtPunt3, spE, spC,
                sonidoError, sonidoCorrecto) {
            @Override
            public int getItemCount() {

                return preguntas.size();
            }
        };
        recyclerView.setAdapter(customAdapter);


    }

    public void Salir (){
        Intent i = new Intent (this,MainActivity.class);
        ListQuestion.Puntuacion.setPunt(0);
        i.putExtra("pos0", pos3);

        startActivity(i);

    }
    public void Lanzar (){
        pos3++;
        ListQuestion.setPos(pos3);
        Intent i = new Intent (this, Q4.class);
        i.putExtra("pos4" ,pos3);
        i.putExtra("Punt4",String.valueOf(ListQuestion.Puntuacion.getPunt()));

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

                FondoQ3.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoQ3.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoQ3.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
                break;
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtp.setTextColor(Color.BLACK);
                txtPunt3.setTextColor(Color.BLACK);
                textV3.setTextColor(Color.BLACK);
                btnSalir3.setTextColor(Color.BLACK);
                btnSiguiente3.setTextColor(Color.BLACK);

                break;
            case "Naranja":
                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPunt3.setTextColor(Color.parseColor("#FFDF8A44"));
                textV3.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSalir3.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSiguiente3.setTextColor(Color.parseColor("#FFDF8A44"));


                break;
            case "Violeta":

                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                txtPunt3.setTextColor(Color.parseColor("#FF971A9D"));
                textV3.setTextColor(Color.parseColor("#FF971A9D"));
                btnSalir3.setTextColor(Color.parseColor("#FF971A9D"));
                btnSiguiente3.setTextColor(Color.parseColor("#FF971A9D"));


                break;
            default:
                break;

        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":

                txtp.setTextSize(14);
                txtPunt3.setTextSize(14);
                textV3.setTextSize(18);
                btnSalir3.setTextSize(14);
                btnSiguiente3.setTextSize(14);


                break;
            case "Medio":
                txtp.setTextSize(16);
                txtPunt3.setTextSize(16);
                textV3.setTextSize(21);
                btnSalir3.setTextSize(16);
                btnSiguiente3.setTextSize(16);


                break;
            case "Grande":
                txtp.setTextSize(18);
                txtPunt3.setTextSize(18);
                textV3.setTextSize(25);
                btnSalir3.setTextSize(18);
                btnSiguiente3.setTextSize(18);


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
