package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.AdminSQLiteOpenHelper;
import com.example.practica1.Clases.ListQuestion;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class QFinal extends AppCompatActivity {

    private TextView txtFinal,txtp,txtpF;
    private ConstraintLayout FondoFinal;
    private Button btnFinal;
    private ToggleButton b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qfinal);

        FondoFinal = findViewById(R.id.fondoFinal);

        b = findViewById(R.id.tbFinal);
        if(!Play_Stop){
            b.setChecked(false);
        }


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestionBBDD", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("name", ListQuestion.User.getName());
        registro.put("nick", ListQuestion.User.getNick());
        registro.put("password", ListQuestion.User.getPassword());
        registro.put("puntos", ListQuestion.User.getPuntosU()+ListQuestion.Puntuacion.getPunt());
         BdD.update("productos", registro,   "nick=" + "'"+ListQuestion.User.getNick()+"'" ,null);
         BdD.close();
        ListQuestion.User.setPuntosU(ListQuestion.User.getPuntosU()+ListQuestion.Puntuacion.getPunt());

        txtFinal = findViewById(R.id.txtNFinal);
        txtp = findViewById(R.id.tpuntos);
        txtpF = findViewById(R.id.txtPuntF);

        btnFinal = findViewById(R.id.btnFinal);
        RellenarPreferencia();

        Bundle bundle = getIntent().getExtras();
        String s = bundle.get("Punt6").toString();
        txtFinal.setText(s);



        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salir();
                finish();
            }
        });


    }
    public void Salir (){
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

                FondoFinal.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":

                FondoFinal.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":

                FondoFinal.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtp.setTextColor(Color.BLACK);
                txtpF.setTextColor(Color.BLACK);
                txtFinal.setTextColor(Color.BLACK);
                btnFinal.setTextColor(Color.BLACK);


                break;
            case "Naranja":
                txtp.setTextColor(Color.parseColor("#FFDF8A44"));
                txtpF.setTextColor(Color.parseColor("#FFDF8A44"));
                txtFinal.setTextColor(Color.parseColor("#FFDF8A44"));
                btnFinal.setTextColor(Color.parseColor("#FFDF8A44"));


                break;
            case "Violeta":

                txtp.setTextColor(Color.parseColor("#FF971A9D"));
                txtpF.setTextColor(Color.parseColor("#FF971A9D"));
                txtFinal.setTextColor(Color.parseColor("#FF971A9D"));
                btnFinal.setTextColor(Color.parseColor("#FF971A9D"));


                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":

                txtp.setTextSize(14);
                txtFinal.setTextSize(14);
                txtpF.setTextSize(26);
                btnFinal.setTextSize(14);

                break;

            case "Medio":

                txtp.setTextSize(16);
                txtFinal.setTextSize(16);
                txtpF.setTextSize(30);
                btnFinal.setTextSize(16);

                break;

            case "Grande":

                txtp.setTextSize(18);
                txtFinal.setTextSize(18);
                txtpF.setTextSize(34);
                btnFinal.setTextSize(18);

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
