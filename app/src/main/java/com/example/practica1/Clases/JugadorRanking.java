package com.example.practica1.Clases;

public class JugadorRanking {
    private String nickJ;
    private int puntos;

    public JugadorRanking(String n, int p){
        this.nickJ = n;
        this.puntos = p;
    }

    public JugadorRanking(){

    }

    public String getNickJ() {
        return nickJ;
    }

    public void setNickJ(String nickJ) {
        this.nickJ = nickJ;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
