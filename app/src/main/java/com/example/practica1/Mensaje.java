package com.example.practica1;

public class Mensaje {
    private String nombre;
    private String mensaje;
    private String hora;
    private String tipoM;


    public Mensaje() {
    }

    public Mensaje(String nombre, String mensaje, String hora, String tipoM) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.hora = hora;
        this.tipoM = tipoM;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoM() {
        return tipoM;
    }

    public void setTipoM(String tipoM) {
        this.tipoM = tipoM;
    }
}
