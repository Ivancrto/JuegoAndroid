package com.example.practica1.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.practica1.Clases.ListQuestion;

public class PreguntasSQLiteOpenHelper extends SQLiteOpenHelper {

    public PreguntasSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase miBBDD) {
        miBBDD.execSQL(("create table preguntasBBDD(id int primary key, pregunta text, opcion1 text, opcion2 text, opcion3 text, " +
                "opcion4 text, respuestaT text, respuestaN int )"));
        for(int i=0; i<=ListQuestion.getList().size()-1; i++){
            ContentValues v1= new ContentValues();
            v1.put("id", i);
            v1.put("pregunta", ListQuestion.getList().get(i).getPregunta());
            v1.put("opcion1", ListQuestion.getList().get(i).getOpcion1());
            v1.put("opcion2", ListQuestion.getList().get(i).getOpcion2());
            v1.put("opcion3", ListQuestion.getList().get(i).getOpcion3());
            v1.put("opcion4", ListQuestion.getList().get(i).getOpcion4());
            v1.put("respuestaT", ListQuestion.getList().get(i).getRespuesta());
            v1.put("respuestaN", ListQuestion.getList().get(i).getPosRespuest());
            miBBDD.insert("preguntasBBDD", null, v1);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
