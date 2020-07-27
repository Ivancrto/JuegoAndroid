package com.example.practica1.Clases;

import java.util.ArrayList;

public class ListQuestion {



    private static ArrayList<Preguntas> lp = new ArrayList<>();
    private static int  pos = 0;

    public ListQuestion(){

        Preguntas p1 = new Preguntas("¿Cuántos componentes forman Queen?", "2", "3", "4", "5",
                "4", 2);
        Preguntas p2 = new Preguntas("¿Que canción no pertenece a  The Beatles?", "Let it be", "Hey Jude", "Yesterday", "Come what may",
                "Come what may", 3);
        Preguntas p3 = new Preguntas("¿Cual es la capital de Francia?", "Madrid", "Paris", "Amsterdam", "Bruselas",
                "Paris", 1);
        Preguntas p4 = new Preguntas("¿En que año nació la reina Isabel II?", "1922", "1926", "1954", "1912",
                "1926", 1);
        Preguntas p5 = new Preguntas("¿Cuantas patas tiene una araña?", "4", "6", "8", "10",
                "8", 2);
        Preguntas p6= new Preguntas("¿De qué año es la Pepa, la primera Constitución española?", "1806", "1794", "1812", "1859", "1812", 2);

        Preguntas p7= new Preguntas("¿Quien escribió La Odisea?", "Homero", "Charles Dicken", "Oscar Wide", "S. King", "Homero", 1);

        Preguntas p8= new Preguntas("¿Cual es el río más largo?", "Nilo", "Amazonas", "Segura", "Tajo", "Amazonas", 2);

        Preguntas p9= new Preguntas("¿En que continente esta Ecuador?", "Europa", "America", "Africa", "Oceania", "America", 1);

        Preguntas p10= new Preguntas("¿Cuántos huesos tiene el ser humano?", "260", "206", "303", "401", "206", 1);


        lp.add(p1);
        lp.add(p2);
        lp.add(p3);
        lp.add(p4);
        lp.add(p5);
        lp.add(p6);
        lp.add(p7);
        lp.add(p8);
        lp.add(p9);
        lp.add(p10);


    }

    public static ArrayList<Preguntas> getList() {
        return lp;
    }
    public static int getPos(){
        if(pos>9){
            return 0;
        }
        return pos;}

        public static void setPos(int p){
        pos = p;
        }




    public class Preguntas {

        private String pregunta;
        private String [] opciones;
        private String respuesta;
        private int posRespuest;

        public Preguntas(String p, String o1, String o2, String o3, String o4, String r, int n) {
            this.pregunta = p;
            opciones = new String[]{o1, o2, o3, o4};
            this.respuesta = r;
            this.posRespuest = n;
        }

        public String[] getOpciones() {
            return opciones;
        }

        public String getPregunta() {

            return pregunta;
        }

        public String getOpcion1() {
            return opciones[0];
        }


        public String getOpcion2() {
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

        public int getPosRespuest() { return posRespuest;
        }
    }
    public static class User{
        private static int puntosU = 0;
        private static String name;
        private static String nick;
        private static String password;


        public static int getPuntosU() {
            return puntosU;
        }

        public static void setPuntosU(int puntosU) {
            User.puntosU = puntosU;
        }

        public static String getName() {
            return name;
        }

        public static void setName(String name) {
            User.name = name;
        }

        public static String getNick() {
            return nick;
        }

        public static void setNick(String nick) {
            User.nick = nick;
        }

        public static String getPassword() {
            return password;
        }

        public static void setPassword(String password) {
            User.password = password;
        }
    }

    public static class Puntuacion {
        private static int punt = 0;
        public static void setPunt(int newPunt) {
            punt = newPunt;
        }
            public static int getPunt() {
                return punt;
            }
    }
}
