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
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.PreguntasSQLiteOpenHelper;
import com.example.practica1.Clases.ListQuestion;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class Q1 extends AppCompatActivity {

    private TextView txtPunt1, textV1,txtp;
    private Button btnSalir,btnSiguiente, b1, b2, b3, b4;
    ListQuestion lq = new ListQuestion();
    private ConstraintLayout FondoQ1;

    int pos1;
    PreguntasSQLiteOpenHelper admini;
    SQLiteDatabase BdD;
    Cursor fila;
    private ToggleButton b;


    //Audio correcto o error
    SoundPool spE, spC;
    int sonidoError, sonidoCorrecto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);

        FondoQ1 = findViewById(R.id.fondoQ1);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        btnSiguiente = findViewById(R.id.btnNext1);
        btnSiguiente.setVisibility(View.INVISIBLE);

        b = findViewById(R.id.tb1);
        if(!Play_Stop){
            b.setChecked(false);
        }

        textV1 = findViewById(R.id.txtPreg1);
        txtPunt1 = findViewById(R.id.txtN1);
        txtp = findViewById(R.id.tpunt1);

        btnSalir = findViewById(R.id.btnExit1);
        btnSiguiente = findViewById(R.id.btnNext1);

        Bundle bundle1 = getIntent().getExtras();
        String s = bundle1.getString("Punt");
        txtPunt1.setText("0");
        pos1 = bundle1.getInt("pos");
        //LLamada a la bbdd
        admini = new PreguntasSQLiteOpenHelper(this, "preguntasBBDD", null, 2);
        BdD = admini.getWritableDatabase();
        fila = BdD.rawQuery("select * from preguntasBBDD where id =" +pos1, null);
        fila.moveToFirst();
        //Insertando el texto a los botones y Textview
        b1.setText(fila.getString(2));
        b2.setText(fila.getString(3));
        b3.setText(fila.getString(4));
        b4.setText(fila.getString(5));
        textV1.setText(fila.getString(1));

        spE = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spC = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sonidoError = spE.load(this, R.raw.incorrecto, 1);
        sonidoCorrecto = spC.load(this, R.raw.correcto, 1);
        RellenarPreferencia();


    }


    public void activateNum(View v)
    {
        switch(v.getId())
        {
            case R.id.btn1:
                b1.setEnabled(true);
                if(fila.getString(2).equals(fila.getString(6))){
                    b1.setBackgroundColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    b1.setBackgroundColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0, 0);
                }
                break;
            case R.id.btn2:
                b2.setEnabled(true);
                if(fila.getString(3).equals(fila.getString(6))){
                    b2.setBackgroundColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    b2.setBackgroundColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0, 0);

                }
                break;
            case R.id.btn3:
                b3.setEnabled(true);
                if(fila.getString(4).equals(fila.getString(6))){
                    b3.setBackgroundColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    b3.setBackgroundColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0, 0);

                }
                break;
            case R.id.btn4:
                b4.setEnabled(true);
                if(fila.getString(5).equals(fila.getString(6))){
                    b4.setBackgroundColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    b4.setBackgroundColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0, 0);

                }
                break;

        }
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        pos1++;
        txtPunt1.setText(String.valueOf(ListQuestion.Puntuacion.getPunt()));
        btnSiguiente.setVisibility(View.VISIBLE);



    }


    public void Lanzar1(View v){
        txtPunt1.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));
        ListQuestion.setPos(pos1);
        Intent i = new Intent (this, Q2.class);
        i.putExtra("Punt2",String.valueOf(ListQuestion.Puntuacion.getPunt()));
        i.putExtra("pos2", pos1);
        BdD.close();
        startActivity(i);
        finish();


    }

    public void Salir1(View v){
        Intent i = new Intent (this,MainActivity.class);
        ListQuestion.Puntuacion.setPunt(0);
        ListQuestion.setPos((int)Math.random()*5 + 3);
        i.putExtra("pos0", pos1);
        startActivity(i);
        finish();
    }

    //MODIFICAR

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

                FondoQ1.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoQ1.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoQ1.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
                break;
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":

                txtp.setTextColor(Color.BLACK);
                txtPunt1.setTextColor(Color.BLACK);
                textV1.setTextColor(Color.BLACK);
                btnSalir.setTextColor(Color.BLACK);
                btnSiguiente.setTextColor(Color.BLACK);
                b1.setTextColor(Color.BLACK);
                b2.setTextColor(Color.BLACK);
                b3.setTextColor(Color.BLACK);
                b4.setTextColor(Color.BLACK);
                break;
            case "Naranja":

                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPunt1.setTextColor(Color.parseColor("#FFDF8A44"));
                textV1.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSalir.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSiguiente.setTextColor(Color.parseColor("#FFDF8A44"));
                b1.setTextColor(Color.parseColor("#FFDF8A44"));
                b2.setTextColor(Color.parseColor("#FFDF8A44"));
                b3.setTextColor(Color.parseColor("#FFDF8A44"));
                b4.setTextColor(Color.parseColor("#FFDF8A44"));
                break;
            case "Violeta":

                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                txtPunt1.setTextColor(Color.parseColor("#FF971A9D"));
                textV1.setTextColor(Color.parseColor("#FF971A9D"));
                btnSalir.setTextColor(Color.parseColor("#FF971A9D"));
                btnSiguiente.setTextColor(Color.parseColor("#FF971A9D"));
                b1.setTextColor(Color.parseColor("#FF971A9D"));
                b2.setTextColor(Color.parseColor("#FF971A9D"));
                b3.setTextColor(Color.parseColor("#FF971A9D"));
                b4.setTextColor(Color.parseColor("#FF971A9D"));
                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":
                txtp.setTextSize(14);
                txtPunt1.setTextSize(14);
                textV1.setTextSize(18);
                btnSalir.setTextSize(14);
                btnSiguiente.setTextSize(14);
                b1.setTextSize(14);
                b2.setTextSize(14);
                b3.setTextSize(14);
                b4.setTextSize(14);

                break;
            case "Medio":
                txtp.setTextSize(16);
                txtPunt1.setTextSize(16);
                textV1.setTextSize(21);
                btnSalir.setTextSize(16);
                btnSiguiente.setTextSize(16);
                b1.setTextSize(16);
                b2.setTextSize(16);
                b3.setTextSize(16);
                b4.setTextSize(16);

                break;
            case "Grande":
                txtp.setTextSize(18);
                txtPunt1.setTextSize(18);
                textV1.setTextSize(25);
                btnSalir.setTextSize(18);
                btnSiguiente.setTextSize(18);
                b1.setTextSize(18);
                b2.setTextSize(18);
                b3.setTextSize(18);
                b4.setTextSize(18);

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
