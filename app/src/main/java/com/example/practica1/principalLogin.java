package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.AdminSQLiteOpenHelper;
import com.example.practica1.Clases.ListQuestion;
import com.example.practica1.Clases.Song;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class principalLogin extends AppCompatActivity {

    private EditText nickET, passwordET;
    private static Preferencia preferencia;
    public static GameMusic music;
    public static boolean Play_Stop = true;
    public static boolean Begin = true;
    private ToggleButton b;

    static ArrayList <Song> songs;
    static int positionSong= 0;
    private Bitmap bitmapSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.quizlogoapp);




        nickET = findViewById(R.id.txt_nickL);
        passwordET = findViewById(R.id.txt_passwordL);
        b = findViewById(R.id.tbLogin);
        if(!Play_Stop){
            b.setChecked(false);
        }


        songs = new ArrayList<>();

        bitmapSong = BitmapFactory.decodeResource(this.getResources(), R.drawable.when_you_left);
        songs.add(new Song("when_you_left.mp3","When you Left","Miscél",bitmapSong));

        bitmapSong = BitmapFactory.decodeResource(this.getResources(), R.drawable.dark_of_you);
        songs.add(new Song("The_Dark_of_You.mp3","The Dark of You","Breaking Benjamin",bitmapSong));

        bitmapSong = BitmapFactory.decodeResource(this.getResources(), R.drawable.diving_bell);
        songs.add(new Song("Diving_Bell.mp3","Diving Bell","Starset",bitmapSong));

        bitmapSong = BitmapFactory.decodeResource(this.getResources(), R.drawable.satellite);
        songs.add(new Song("Satellite.mp3","Satellite","Rise Against",bitmapSong));

        bitmapSong = BitmapFactory.decodeResource(this.getResources(), R.drawable.dancin);
        songs.add(new Song("Dancin.mp3","Dancin","Aaron Smith (KRONO Remix)",bitmapSong));

        bitmapSong = BitmapFactory.decodeResource(this.getResources(), R.drawable.diggy_diggy_hole);
        songs.add(new Song("Diggy_diggy_hole.mp3","Diggy Diggy Hole","Wind Rose",bitmapSong));

        bitmapSong = BitmapFactory.decodeResource(this.getResources(), R.drawable.warsongs_proyect_yi);
        songs.add(new Song("Warsongs_proyect_yi.mp3","Warsongs: Proyect Yi","Vicetone",bitmapSong));




        // Iniciación de la música de fondo
        if(Play_Stop && Begin) {
            try {
                Begin = false;
                AssetManager am = this.getAssets();
                AssetFileDescriptor afd = am.openFd(songs.get(positionSong).getSongLink());
                music = new GameMusic(afd);
                music.setLooping(true);
            } catch (IOException e) {

            }
            music.play();
        }
    }

    public void iniciar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestionBBDD", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        Cursor fila = BdD.rawQuery("select password, puntos, nick, name from productos where trim(nick)= "+ "'"+nickET.getText().toString()+"'", null);
        String p = passwordET.getText().toString();
        if(fila.moveToFirst()){
            if(fila.getString(0).equals(p)){
                BdD.close();
                ListQuestion.User.setName(fila.getString(3));
                ListQuestion.User.setNick(fila.getString(2));
                ListQuestion.User.setPassword(fila.getString(0));
                ListQuestion.User.setPuntosU(fila.getInt(1));

                RellenarPreferencia();

                Intent i = new Intent (this,MainActivity.class);
                i.putExtra("nickJugador", nickET.getText().toString());
                startActivity(i);
            }else{
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Usuario y contraseña incorrecta", Toast.LENGTH_SHORT).show();
            BdD.close();


        }

    }

    public void crear(View v){
        Intent i = new Intent (this,createUser.class);
        startActivity(i);
    }

    public void RellenarPreferencia(){
        String nick = nickET.getText().toString();

        SharedPreferences preferenciasFondo = getSharedPreferences("Fondo", Context.MODE_PRIVATE);
        SharedPreferences preferenciasColor = getSharedPreferences("Color", Context.MODE_PRIVATE);
        SharedPreferences preferenciasTamaño = getSharedPreferences("Tamaño", Context.MODE_PRIVATE);

        String fondo = preferenciasFondo.getString(nick,"");
        String color = preferenciasColor.getString(nick,"");
        String tamaño = preferenciasTamaño.getString(nick,"");


        if(fondo.length()==0 && tamaño.length()==0 && tamaño.length()==0) {
            Toast.makeText(this,"sdfsadf",Toast.LENGTH_SHORT).show();
        }else{
            preferencia = new Preferencia(fondo,color,tamaño);
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
