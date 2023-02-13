package com.example.laultimaesperanza.entidades;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.laultimaesperanza.graficos.Animacion;
import com.example.laultimaesperanza.juego.Disposicion;
import com.example.laultimaesperanza.juego.Juego;
import com.example.laultimaesperanza.juego.MotorGrafico;
import com.example.laultimaesperanza.R;


public class Zombi extends Entidad {

    public static double VELZOMBI = 240.0;
    public static double VELMAX = VELZOMBI / MotorGrafico.MAX_APS;
    private static double aparicionesMinuto= 10*Juego.nivel;
    private static double aparicionesSegundo = aparicionesMinuto / 60.0;
    private static double actualizacionPorAparicion = MotorGrafico.MAX_APS / aparicionesSegundo;

    private static double siguenteAparicion = actualizacionPorAparicion;

    private int conteo;

    private final Jugador jugador;
    private Animacion animacion;

    public Zombi(Context context, Jugador j, double x, double y, double r, Animacion animacion) {
        super(context, ContextCompat.getColor(context, R.color.zombi), x, y, r);
        this.jugador = j;
        this.animacion=animacion;
        conteo = 10;
    }


    public Zombi(Context context, Jugador j, Animacion animacion) {
        super(context, ContextCompat.getColor(context, R.color.zombi), Math.random() * 2000, Math.random() * 2000, 30);
        this.jugador = j;
        this.animacion=animacion;
        conteo = 10;

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

    @Override
    public void dibujar(Canvas lienzo, Disposicion disposicion) {
        animacion.dibujar(lienzo,disposicion,this);


    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }
}
