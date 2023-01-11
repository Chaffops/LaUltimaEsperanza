package com.example.laultimaesperanza.entidades;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.laultimaesperanza.MotorGrafico;
import com.example.laultimaesperanza.R;


public class Zombi extends Entidad {

    public static final double VELZOMBI = Jugador.VELJUGADOR * 0.6;
    public static final double VELMAX = VELZOMBI / MotorGrafico.MAX_APS;
    private static double aparicionesMinuto = 20;
    private static double aparicionesSegundo = aparicionesMinuto / 60.0;
    private static double actualizacionPorAparicion = MotorGrafico.MAX_APS / aparicionesSegundo;

    private static double siguenteAparicion = actualizacionPorAparicion;


    private final Jugador jugador;

    public Zombi(Context context, Jugador j, double x, double y, double r) {
        super(context, ContextCompat.getColor(context, R.color.zombi), x, y, r);
        this.jugador = j;

    }

    public Zombi(Context context, Jugador j) {
        super(context,ContextCompat.getColor(context, R.color.zombi),Math.random() * 1000,Math.random() * 1000,30);
        this.jugador = j;
    }


    public static boolean listoAparecer() {
        if (siguenteAparicion <= 0) {
            siguenteAparicion += actualizacionPorAparicion;
            return true;
        } else {
            siguenteAparicion--;
            return false;

        }
    }

    @Override
    public void actualizar() {

        double distanciaJugadorX = jugador.getPosicionX() - posX;
        double distanciaJugadorY = jugador.getPosicionY() - posY;


        double distanciaJugador = ObjetoJugable.getDistanciaEntreObjetos(this, jugador);


        double directionX = distanciaJugadorX / distanciaJugador;
        double directionY = distanciaJugadorY / distanciaJugador;

        if (distanciaJugador > 0) {

            velX = directionX * VELMAX;
            velY = directionY * VELMAX;
        } else {
            velX = 0;
            velY = 0;


        }
        posX += velX;
        posY += velY;


    }
}
