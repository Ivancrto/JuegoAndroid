package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.PreguntasSQLiteOpenHelper;
import com.example.practica1.Clases.ListQuestion;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class Q4 extends AppCompatActivity {

    int pos4;
    TextView txtV4, txtPunt4,txtp;
    Spinner sp4;
    Button btnSalir4, btnSiguiente4, comprueba;
    private ConstraintLayout FondoQ4;

    ListQuestion lq = new ListQuestion();
    PreguntasSQLiteOpenHelper admini;
    SQLiteDatabase BdD;
    Cursor fila;
    private ToggleButton b;


    SoundPool spE, spC;
    int sonidoError, sonidoCorrecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q4);

        FondoQ4 = findViewById(R.id.fondoQ4);

        Bundle bundle4 = getIntent().getExtras();
        String s = bundle4.getString("Punt4");
        pos4 = bundle4.getInt("pos4");
        sp4 = findViewById(R.id.spinner);

        b = findViewById(R.id.tb4);
        if(!Play_Stop){
            b.setChecked(false);
        }

        spE = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spC = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoError = spE.load(this, R.raw.incorrecto, 1);
        sonidoCorrecto = spC.load(this, R.raw.correcto, 1);


        admini = new PreguntasSQLiteOpenHelper(this, "preguntasBBDD", null, 2);
        BdD = admini.getWritableDatabase();
        fila = BdD.rawQuery("select * from preguntasBBDD where id ="+ pos4, null);
        fila.moveToFirst();




        String [] opciones = {"",fila.getString(2), fila.getString(3), fila.getString(4),
                fila.getString(5)};
        ArrayAdapter<String> opAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        sp4.setAdapter(opAdapter);
        sp4.setBackgroundColor(Color.WHITE);

        txtp = findViewById(R.id.tpunt4);
        txtV4 = findViewById(R.id.txtPreg4);
        txtV4.setText(lq.getList().get(pos4).getPregunta());
        txtPunt4 = findViewById(R.id.txtN4);
        comprueba = findViewById(R.id.btnComprueba);
        txtPunt4.setText(String.valueOf(ListQuestion.Puntuacion.getPunt()));
        btnSiguiente4 = findViewById(R.id.btnNext4);
        btnSalir4 = findViewById(R.id.btnExit4);
        btnSiguiente4.setVisibility(View.INVISIBLE);
        RellenarPreferencia();

    }


    public void activateNum4(View v){

        if(sp4.getSelectedItem().toString().equals(fila.getString(6))){
            ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
            sp4.setBackgroundColor(Color.parseColor("#37A456"));
            spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
        }else{
            ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
            sp4.setBackgroundColor(Color.parseColor("#BF404F"));
            spE.play(sonidoError, 1, 1, 1, 0 ,0);
        }
        comprueba.setVisibility(View.INVISIBLE);
        txtPunt4.setText(String.valueOf(ListQuestion.Puntuacion.getPunt()));
        btnSiguiente4.setVisibility(View.VISIBLE);


    }

    public void Salir4 (View v){
        Intent i = new Intent (this,MainActivity.class);
        ListQuestion.Puntuacion.setPunt(0);
        ListQuestion.setPos((int)Math.random()*5 + 1);
        i.putExtra("pos0", pos4);
        startActivity(i);
        finish();
    }
    public void Lanzar4 (View v){
        pos4++;
        ListQuestion.setPos(pos4);
        txtPunt4.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));
        Intent i = new Intent (this, Q5.class);
        i.putExtra("Punt5",txtPunt4.getText().toString());
        i.putExtra("pos5" ,pos4);
        startActivity(i);
        finish();
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

                FondoQ4.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoQ4.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoQ4.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtp.setTextColor(Color.BLACK);
                txtPunt4.setTextColor(Color.BLACK);
                txtV4.setTextColor(Color.BLACK);
                btnSalir4.setTextColor(Color.BLACK);
                btnSiguiente4.setTextColor(Color.BLACK);
                comprueba.setTextColor(Color.BLACK);

                break;
            case "Naranja":
                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPunt4.setTextColor(Color.parseColor("#FFDF8A44"));
                txtV4.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSalir4.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSiguiente4.setTextColor(Color.parseColor("#FFDF8A44"));
                comprueba.setTextColor(Color.parseColor("#FFDF8A44"));

                break;
            case "Violeta":

                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                txtPunt4.setTextColor(Color.parseColor("#FF971A9D"));
                txtV4.setTextColor(Color.parseColor("#FF971A9D"));
                btnSalir4.setTextColor(Color.parseColor("#FF971A9D"));
                btnSiguiente4.setTextColor(Color.parseColor("#FF971A9D"));
                comprueba.setTextColor(Color.parseColor("#FF971A9D"));

                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":

                txtp.setTextSize(14);
                txtPunt4.setTextSize(14);
                txtV4.setTextSize(18);
                comprueba.setTextSize(14);
                btnSalir4.setTextSize(14);
                btnSiguiente4.setTextSize(14);
                break;

            case "Medio":

                txtp.setTextSize(16);
                txtPunt4.setTextSize(16);
                txtV4.setTextSize(21);
                comprueba.setTextSize(16);
                btnSalir4.setTextSize(16);
                btnSiguiente4.setTextSize(16);
                break;

            case "Grande":

                txtp.setTextSize(18);
                txtPunt4.setTextSize(18);
                txtV4.setTextSize(25);
                comprueba.setTextSize(18);
                btnSalir4.setTextSize(18);
                btnSiguiente4.setTextSize(18);
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
