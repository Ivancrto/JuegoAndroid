package com.example.practica1.Clases;

import com.example.practica1.BBDD.PreguntasSQLiteOpenHelper;

public class Preguntas {

    private static String pregunta;
    private static String [] opciones;
    private static String respuesta;
    private static int posRespuest;

    public Preguntas(String p, String o1, String o2, String o3, String o4, String r, int n) {
        this.pregunta = p;
        opciones = new String[]{o1, o2, o3, o4};
        this.respuesta = r;
        this.posRespuest = n;
    }

    public static String[] getOpciones() {
        return opciones;
    }

    public static String getPregunta() {

        return pregunta;
    }

    public static String getOpcion1() {
        return opciones[0];
    }


    public static String getOpcion2() {
        return opciones[1];
    }


    public String getOpcion3() {
        return opciones[2];
    }


    public String getOpcion4() {
        return opciones[3];
    }


    public String getRespuesta() {
        return respuesta;
    }

    public int getPosRespuest(){ return posRespuest;
    }




}
