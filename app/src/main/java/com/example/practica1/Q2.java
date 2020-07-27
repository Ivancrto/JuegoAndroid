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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.PreguntasSQLiteOpenHelper;
import com.example.practica1.Clases.ListQuestion;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class Q2 extends AppCompatActivity {

    private RadioButton rb1, rb2, rb3, rb4;
    private Button bSiguiente2, bSalir2;
    private TextView txtv2, puntu2,txtp;
    private ConstraintLayout FondoQ2;
    private int pos2;
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
        setContentView(R.layout.activity_q2);

        FondoQ2 = findViewById(R.id.fondoQ2);

        Bundle bundle2 = getIntent().getExtras();

        String s = bundle2.getString("Punt2");
        pos2 = bundle2.getInt("pos2");
        pos2++;
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        b = findViewById(R.id.tb2);
        if(!Play_Stop){
            b.setChecked(false);
        }

        txtv2 = findViewById(R.id.txtPreg2);
        puntu2 = findViewById(R.id.txtN2);
        txtp = findViewById(R.id.tpunt2);
        bSiguiente2 = findViewById(R.id.btnNext2);
        bSalir2 =findViewById(R.id.btnExit4);
        bSiguiente2.setVisibility(View.INVISIBLE);
        puntu2.setText(String.valueOf(ListQuestion.Puntuacion.getPunt()));

        admini = new PreguntasSQLiteOpenHelper(this, "preguntasBBDD", null, 2);
        BdD = admini.getWritableDatabase();
        fila = BdD.rawQuery("select * from preguntasBBDD where id ="+ pos2, null);
        fila.moveToFirst();
        /*
        rb1.setText(lq.getList().get(pos2).getOpcion1());
        rb2.setText(lq.getList().get(pos2).getOpcion2());
        rb3.setText(lq.getList().get(pos2).getOpcion3());
        rb4.setText(lq.getList().get(pos2).getOpcion4());
        txtv2.setText(lq.getList().get(pos2).getPregunta());*/
        rb1.setText(fila.getString(2));
        rb2.setText(fila.getString(3));
        rb3.setText(fila.getString(4));
        rb4.setText(fila.getString(5));
        txtv2.setText(fila.getString(1));
        puntu2.setText(Integer.toString(ListQuestion.Puntuacion.getPunt()));


        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        spE = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        spC = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);

        sonidoError = spE.load(this, R.raw.incorrecto, 1);
        sonidoCorrecto = spC.load(this, R.raw.correcto, 1);

        RellenarPreferencia();

    }



    public void activateNum2(View v)
    {
        switch(v.getId())
        {
            case R.id.rb1:
                rb1.setEnabled(true);
                if(lq.getList().get(pos2).getOpcion1()==lq.getList().get(pos2).getRespuesta()){
                    rb1.setTextColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    rb1.setTextColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0 ,0);
                }
                break;
            case R.id.rb2:
                rb2.setEnabled(true);
                if(lq.getList().get(pos2).getOpcion2()==lq.getList().get(pos2).getRespuesta()){
                    rb2.setTextColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    rb2.setTextColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0 ,0);
                }
                break;
            case R.id.rb3:
                rb3.setEnabled(true);
                if(lq.getList().get(pos2).getOpcion3()==lq.getList().get(pos2).getRespuesta()){
                    rb3.setTextColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    rb3.setTextColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0 ,0);
                }
                break;
            case R.id.rb4:
                rb4.setEnabled(true);
                if(lq.getList().get(pos2).getOpcion4()==lq.getList().get(pos2).getRespuesta()){
                    rb4.setTextColor(Color.parseColor("#37A456"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()+3);
                    spC.play(sonidoCorrecto, 1, 1, 1, 0 ,0);
                }else{
                    rb4.setTextColor(Color.parseColor("#BF404F"));
                    ListQuestion.Puntuacion.setPunt(ListQuestion.Puntuacion.getPunt()-2);
                    spE.play(sonidoError, 1, 1, 1, 0 ,0);
                }
                break;

        }
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        pos2++;
        puntu2.setText(String.valueOf(ListQuestion.Puntuacion.getPunt()));
        bSiguiente2.setVisibility(View.VISIBLE);



    }





    public void Lanzar2(View v){

        ListQuestion.setPos(pos2);
        Intent i = new Intent (this, Q3.class);
        i.putExtra("Punt3",String.valueOf(ListQuestion.Puntuacion.getPunt()));
        i.putExtra("pos3", pos2);
        startActivity(i);
        finish();
    }

    public void Salir2(View v){
        Intent i = new Intent (this,MainActivity.class);
        ListQuestion.Puntuacion.setPunt(0);
        ListQuestion.setPos((int)Math.random()*5 + 3);
        i.putExtra("pos0", pos2);
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

                FondoQ2.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoQ2.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoQ2.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
                break;
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtp.setTextColor(Color.BLACK);
                puntu2.setTextColor(Color.BLACK);
                txtv2.setTextColor(Color.BLACK);
                bSalir2.setTextColor(Color.BLACK);
                bSiguiente2.setTextColor(Color.BLACK);
                rb1.setTextColor(Color.BLACK);
                rb2.setTextColor(Color.BLACK);
                rb3.setTextColor(Color.BLACK);
                rb4.setTextColor(Color.BLACK);
                break;
            case "Naranja":
                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                puntu2.setTextColor(Color.parseColor("#FFDF8A44"));
                txtv2.setTextColor(Color.parseColor("#FFDF8A44"));
                bSalir2.setTextColor(Color.parseColor("#FFDF8A44"));
                bSiguiente2.setTextColor(Color.parseColor("#FFDF8A44"));
                rb1.setTextColor(Color.parseColor("#FFDF8A44"));
                rb2.setTextColor(Color.parseColor("#FFDF8A44"));
                rb3.setTextColor(Color.parseColor("#FFDF8A44"));
                rb4.setTextColor(Color.parseColor("#FFDF8A44"));

                break;
            case "Violeta":

                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                puntu2.setTextColor(Color.parseColor("#FF971A9D"));
                txtv2.setTextColor(Color.parseColor("#FF971A9D"));
                bSalir2.setTextColor(Color.parseColor("#FF971A9D"));
                bSiguiente2.setTextColor(Color.parseColor("#FF971A9D"));
                rb1.setTextColor(Color.parseColor("#FF971A9D"));
                rb2.setTextColor(Color.parseColor("#FF971A9D"));
                rb3.setTextColor(Color.parseColor("#FF971A9D"));
                rb4.setTextColor(Color.parseColor("#FF971A9D"));

                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":

                txtp.setTextSize(14);
                puntu2.setTextSize(14);
                txtv2.setTextSize(18);
                bSalir2.setTextSize(14);
                bSiguiente2.setTextSize(14);
                rb1.setTextSize(14);
                rb2.setTextSize(14);
                rb3.setTextSize(14);
                rb4.setTextSize(14);

                break;
            case "Medio":
                txtp.setTextSize(16);
                puntu2.setTextSize(16);
                txtv2.setTextSize(21);
                bSalir2.setTextSize(16);
                bSiguiente2.setTextSize(16);
                rb1.setTextSize(16);
                rb2.setTextSize(16);
                rb3.setTextSize(16);
                rb4.setTextSize(16);

                break;
            case "Grande":
                txtp.setTextSize(18);
                puntu2.setTextSize(18);
                txtv2.setTextSize(25);
                bSalir2.setTextSize(18);
                bSiguiente2.setTextSize(18);
                rb1.setTextSize(18);
                rb2.setTextSize(18);
                rb3.setTextSize(18);
                rb4.setTextSize(18);

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
