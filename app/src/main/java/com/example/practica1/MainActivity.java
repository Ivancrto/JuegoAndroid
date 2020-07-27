package com.example.practica1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.practica1.Clases.ListQuestion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.practica1.principalLogin.Play_Stop;
import static com.example.practica1.principalLogin.music;


public class MainActivity extends AppCompatActivity {


    private Button btnPlay,btnRanking,btnSalir,btnChat;
    private TextView txtPaso,txtQuiz,txtPunt1;
    private ConstraintLayout FondoPrincipal;
    int pos = 1;
    int puntos = ListQuestion.User.getPuntosU();
    ImageButton imagenB;
    ImageView imageVM;
    private ToggleButton b;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.tbQuiz);
        if(!Play_Stop){
            b.setChecked(false);
        }

        FondoPrincipal = findViewById(R.id.FondoPrincipal);

        ListQuestion.Puntuacion.setPunt(0);
        pos = ListQuestion.getPos();
        btnPlay = findViewById(R.id.btnJugar);
        btnRanking = findViewById(R.id.btnJugar2);
        txtPaso = findViewById(R.id.txtN);
        btnChat =findViewById(R.id.btnChat);
        txtPaso.setText(String.valueOf(ListQuestion.User.getPuntosU()));

        txtQuiz = findViewById(R.id.txtQuiz);
        txtPunt1 =findViewById(R.id.txtPunt1);
        btnSalir =findViewById(R.id.btnSalir);


        imageVM = findViewById(R.id.imageViewM);
        Bitmap bitmap = null;

        try{
            FileInputStream fileInputStream =
                    new FileInputStream(getApplicationContext().getFilesDir().getPath()+ "/"+ ListQuestion.User.getNick()+".jpg");
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException io){
            io.printStackTrace();
        }
        if(bitmap!=null){
            imageVM.setImageBitmap(bitmap);
        }




        // Cambia el fondo, color de texto y tamaño de texto a la preferencia del usuario
        RellenarPreferencia();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lanzar();
                finish();
            }
        });



    }


    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    static final int REQUEST_TAKE_PHOTO = 1;
    public void tomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.practica1",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }



    public void Lanzar (){
        Intent i = new Intent (this,Q1.class);

        i.putExtra("Punt",txtPaso.getText().toString());
        i.putExtra("pos", pos);

        startActivity(i);
    }

    public void verRanking(View v){
        Intent i = new Intent (this,rankingUser.class);
        startActivity(i);
    }
    public void salir(View v){
        Intent i = new Intent (this, principalLogin.class);
        startActivity(i);
    }

    public void chat(View v){
        Intent i = new Intent (this, salachat.class);
        startActivity(i);
    }
    public void Ajuste(View v){
        Intent i = new Intent (this, ajustesCamara.class);
        startActivity(i);
    }

    public void Reproductor(View v){
        Intent i = new Intent (this, Reproductor.class);
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
                FondoPrincipal.setBackgroundColor(Color.parseColor("#FF92C65B"));
                break;
            case "Rojo":
                FondoPrincipal.setBackgroundColor(Color.parseColor("#FFE4767C"));
                break;
            case "Azul":
                FondoPrincipal.setBackgroundColor(Color.parseColor("#FF2491D1"));
                break;
            default:
                break;
        }
    }
    public void RellenarColorTexto(String color){
        switch(color){

            case "Negro":
                txtQuiz.setTextColor(Color.BLACK);
                txtPunt1.setTextColor(Color.BLACK);
                txtPaso.setTextColor(Color.BLACK);
                btnPlay.setTextColor(Color.BLACK);
                btnRanking.setTextColor(Color.BLACK);
                btnSalir.setTextColor(Color.BLACK);
                btnChat.setTextColor(Color.BLACK);
                break;
            case "Naranja":
                txtQuiz.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPunt1.setTextColor(Color.parseColor("#FFDF8A44"));
                txtPaso.setTextColor(Color.parseColor("#FFDF8A44"));
                btnPlay.setTextColor(Color.parseColor("#FFDF8A44"));
                btnRanking.setTextColor(Color.parseColor("#FFDF8A44"));
                btnSalir.setTextColor(Color.parseColor("#FFDF8A44"));
                btnChat.setTextColor(Color.parseColor("#FFDF8A44"));
                break;
            case "Violeta":
                txtQuiz.setTextColor(Color.parseColor("#FF971A9D"));
                txtPunt1.setTextColor(Color.parseColor("#FF971A9D"));
                txtPaso.setTextColor(Color.parseColor("#FF971A9D"));
                btnPlay.setTextColor(Color.parseColor("#FF971A9D"));
                btnRanking.setTextColor(Color.parseColor("#FF971A9D"));
                btnSalir.setTextColor(Color.parseColor("#FF971A9D"));
                btnChat.setTextColor(Color.parseColor("#FF971A9D"));

                break;
            default:
                break;
        }
    }
    public void RellenarTamañoTexto(String tamaño){
        switch(tamaño){

            case "Pequeño":
                txtQuiz.setTextSize(20);
                txtPunt1.setTextSize(14);
                txtPaso.setTextSize(14);
                btnPlay.setTextSize(14);
                btnRanking.setTextSize(14);
                btnSalir.setTextSize(14);
                btnChat.setTextSize(14);
                break;
            case "Medio":
                txtQuiz.setTextSize(24);
                txtPunt1.setTextSize(17);
                txtPaso.setTextSize(17);
                btnPlay.setTextSize(17);
                btnRanking.setTextSize(17);
                btnSalir.setTextSize(17);
                btnChat.setTextSize(17);

                break;
            case "Grande":
                txtQuiz.setTextSize(28);
                txtPunt1.setTextSize(20);
                txtPaso.setTextSize(20);
                btnPlay.setTextSize(20);
                btnRanking.setTextSize(20);
                btnSalir.setTextSize(20);
                btnChat.setTextSize(20);
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
