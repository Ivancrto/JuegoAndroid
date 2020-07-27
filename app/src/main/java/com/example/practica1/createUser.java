package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.practica1.BBDD.AdminSQLiteOpenHelper;
import com.example.practica1.Clases.ListQuestion;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class createUser extends AppCompatActivity {

    private EditText nombreET, nickET, contraseñaET;
    private Button btnFondo,btnColor,btnTamaño;
    private static Preferencia preferencia;
    private ToggleButton b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        nombreET = findViewById(R.id.txt_nombre);
        nickET = findViewById(R.id.txt_nick);
        contraseñaET = findViewById(R.id.txt_contraseña);

        b = findViewById(R.id.tbCreate);
        if(!Play_Stop){
            b.setChecked(false);
        }

        btnFondo = findViewById(R.id.btnFondoAj);
        btnColor = findViewById(R.id.btnColorTexto);
        btnTamaño = findViewById(R.id.btnTamañoTexto);



        btnFondo.setText("Verde");
        btnColor.setText("Negro");
        btnTamaño.setText("Pequeño");

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PulsaColor(v);
            }
        });

        btnTamaño.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PulsaTamaño(v);
            }
        });

    }

    public void Guardar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestionBBDD", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();

        if(nombreET.getText().toString().isEmpty() ||  nickET.getText().toString().isEmpty() ||contraseñaET.getText().toString().isEmpty()){
            Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT);
        }else if(!Buscar()) {
            registro.put("name", nombreET.getText().toString());
            registro.put("nick", nickET.getText().toString());
            registro.put("password", contraseñaET.getText().toString());
            registro.put("puntos", 0);
            ListQuestion.User.setName(nombreET.getText().toString());
            ListQuestion.User.setNick(nickET.getText().toString());
            ListQuestion.User.setPassword(contraseñaET.getText().toString());
            ListQuestion.User.setPuntosU(0);
            BdD.insert("productos", null, registro);
            BdD.close();
            GuardarPreferencias();
            RellenarPreferencia();
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            Intent i = new Intent (this, MainActivity.class);
            i.putExtra("nickJugador", nickET.getText().toString());
            startActivity(i);
        }
    }

    public Boolean Buscar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestionBBDD", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        Cursor fila = BdD.rawQuery("select name from productos where trim(nick)= "+ "'"+nickET.getText().toString()+"'", null);

        if(fila.moveToFirst()){
            Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            BdD.close();
            return true;
        }else{
            BdD.close();
            return false;
        }

    }

    public void GuardarPreferencias(){
        String Fondo= btnFondo.getText().toString();
        String Color= btnColor.getText().toString();;
        String Tamaño= btnTamaño.getText().toString();;

        SharedPreferences preferenciasFondo = getSharedPreferences("Fondo", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor_fondo = preferenciasFondo.edit();
        obj_editor_fondo.putString(nickET.getText().toString(),Fondo);
        obj_editor_fondo.commit();

        SharedPreferences preferenciasColor = getSharedPreferences("Color", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor_color = preferenciasColor.edit();
        obj_editor_color.putString(nickET.getText().toString(),Color);
        obj_editor_color.commit();

        SharedPreferences preferenciasTamaño = getSharedPreferences("Tamaño", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor_tamaño = preferenciasTamaño.edit();
        obj_editor_tamaño.putString(nickET.getText().toString(),Tamaño);
        obj_editor_tamaño.commit();
    }

    public void RellenarPreferencia(){
        String nick = nickET.getText().toString();

        SharedPreferences preferenciasFondo = getSharedPreferences("Fondo", Context.MODE_PRIVATE);
        SharedPreferences preferenciasColor = getSharedPreferences("Color", Context.MODE_PRIVATE);
        SharedPreferences preferenciasTamaño = getSharedPreferences("Tamaño", Context.MODE_PRIVATE);

        String fondo = preferenciasFondo.getString(nick,"");
        String color = preferenciasColor.getString(nick,"");
        String tamaño = preferenciasTamaño.getString(nick,"");


        if(fondo.length()==0 && color.length()==0 && tamaño.length()==0) {
            Toast.makeText(this,"sdfsadf",Toast.LENGTH_SHORT).show();
        }else{
            preferencia = new Preferencia(fondo,color,tamaño);
        }
    }

    public void PulsaFondo(View v){

        switch(btnFondo.getText().toString()){

            case "Verde":
                        btnFondo.setText("Rojo");
                        break;
            case "Rojo":
                        btnFondo.setText("Azul");
                        break;
            case "Azul":
                        btnFondo.setText("Verde");
                        break;
            default:
                break;
        }
    }

    public void PulsaColor(View v){

        switch(btnColor.getText().toString()){

            case "Negro":
                btnColor.setText("Naranja");
                break;
            case "Naranja":
                btnColor.setText("Violeta");
                break;
            case "Violeta":
                btnColor.setText("Negro");
                break;
            default:
                break;

        }

    }

    public void PulsaTamaño(View v){

        switch(btnTamaño.getText().toString()){

            case "Pequeño":
                btnTamaño.setText("Medio");
                break;
            case "Medio":
                btnTamaño.setText("Grande");
                break;
            case "Grande":
                btnTamaño.setText("Pequeño");
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
