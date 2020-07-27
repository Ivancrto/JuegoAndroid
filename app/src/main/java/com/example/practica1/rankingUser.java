package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.AdminSQLiteOpenHelper;
import com.example.practica1.Clases.JugadorRanking;
import com.example.practica1.CustomA.CustomerAdapterRanking;

import java.util.ArrayList;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class rankingUser extends AppCompatActivity {

    private TextView txtR;
    private Button btnSalirR;
    private ConstraintLayout FondoRanking;
    private ToggleButton b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_user);

        txtR = findViewById(R.id.txtRanking);
        btnSalirR = findViewById(R.id.btnExit4);
        FondoRanking = findViewById(R.id.fondoRanking);

        b = findViewById(R.id.tbRanking);
        if(!Play_Stop){
            b.setChecked(false);
        }

        RellenarPreferencia();
        String selectQuery = "SELECT  nick, puntos FROM productos ORDER BY puntos DESC";
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestionBBDD", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();
        Cursor cursor = BdD.rawQuery(selectQuery, null);
        ArrayList<JugadorRanking> jRL = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                JugadorRanking jR = new JugadorRanking();
                jR.setPuntos(cursor.getInt(1));
                jR.setNickJ(cursor.getString(0));

                jRL.add(jR);
            } while (cursor.moveToNext());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewchat);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        CustomerAdapterRanking customAdpater = new CustomerAdapterRanking(this,jRL);
        recyclerView.setAdapter(customAdpater);
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

                FondoRanking.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoRanking.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoRanking.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtR.setTextColor(Color.BLACK);
                btnSalirR.setTextColor(Color.BLACK);

                break;
            case "Naranja":
                txtR.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSalirR.setTextColor(Color.parseColor("#FFDF8A44"));

                break;
            case "Violeta":

                txtR.setTextColor(Color.parseColor("#FF971A9D"));
                btnSalirR.setTextColor(Color.parseColor("#FF971A9D"));

                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":

                txtR.setTextSize(20);
                btnSalirR.setTextSize(14);

                break;

            case "Medio":

                txtR.setTextSize(24);
                btnSalirR.setTextSize(16);

                break;

            case "Grande":

                txtR.setTextSize(28);
                btnSalirR.setTextSize(18);

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
