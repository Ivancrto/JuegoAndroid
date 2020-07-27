package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.practica1.Clases.ListQuestion;
import com.example.practica1.CustomA.AdapterMensaje;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;

public class salachat extends AppCompatActivity {

    ImageView fotoPerfil;
    TextView nombre;
    RecyclerView recyclerView;
    EditText textoMensaje;
    Button botonEnviar;

    AdapterMensaje adaptador;
    //Objeto para guardar mensajes
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    static int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salachat);
        fotoPerfil = findViewById(R.id.imageViewchat);
        nombre = findViewById(R.id.Nombre);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewchat);
        textoMensaje = findViewById(R.id.textoMensaje);
        botonEnviar = findViewById(R.id.botonEnviarchat);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("salaC");//Sala de chat (nombre)

        adaptador = new AdapterMensaje(this);
        LinearLayoutManager liL = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(liL);

        recyclerView.setAdapter(adaptador);

        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat");



        botonEnviar.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                 String currentDateandTime = sdf.format(new Date());
                 Mensaje mensaje = new Mensaje(ListQuestion.User.getNick(), textoMensaje.getText().toString(), currentDateandTime, "0");
                 databaseReference.push().setValue(mensaje);
                 textoMensaje.setText("");
             }
        }
        );


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mensaje m = dataSnapshot.getValue(Mensaje.class);
                adaptador.añadirMensaje(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adaptador.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setSCrollbar();
            }
        });




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

   public void setSCrollbar(){
        recyclerView.scrollToPosition(adaptador.getItemCount()-1);
    }


    /*public void enviar(View v){
        databaseReference.push().setValue(new Mensaje(nombre.getText().toString(), textoMensaje.getText().toString(), "00:00", "", "0"  ));

    }*/


}