package com.example.practica1;

public class Preferencia {

    private static String fondo;
    private static String colorTexto;
    private static String tamañoTexto;


    public Preferencia(String f, String color, String tamaño) {
        this.fondo = f;
        this.colorTexto = color;
        this.tamañoTexto = tamaño;
    }

    public Preferencia() {

    }

    public static String getFondo() {
        return fondo;
    }

    public  void setFondo(String f) {
        this.fondo = fondo;
    }

    public static String getColorTexto() {
        return colorTexto;
    }

    public  void setColorTexto(String color) {
        this.colorTexto = color;
    }

    public static String getTamañoTexto() {
        return tamañoTexto;
    }

    public void setTamañoTexto(String tamaño) {
        this.tamañoTexto = tamaño;
    }
}
