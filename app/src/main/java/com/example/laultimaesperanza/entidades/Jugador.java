package com.example.laultimaesperanza.entidades;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.laultimaesperanza.graficos.Animacion;
import com.example.laultimaesperanza.graficos.Dibujos;
import com.example.laultimaesperanza.juego.Disposicion;
import com.example.laultimaesperanza.juego.Joystick;
import com.example.laultimaesperanza.juego.MotorGrafico;
import com.example.laultimaesperanza.R;


public class Jugador extends Entidad {
    public static final double VELJUGADOR = 400.0;
    public static final double VELMAX = VELJUGADOR / MotorGrafico.MAX_APS;
    public static final int MAX_PUNTOS_VIDA = 10;

    private static int puntosVida;
    private final Vida vida;

    private Joystick joystick;
    private Animacion animacion;

    private EstadoJugador estadoJugador;

    public Jugador(Context context, Joystick j, double x, double y, double r, Animacion animacion) {
        super(context, ContextCompat.getColor(context, R.color.jugador), x, y, r);
        this.joystick = j;
        this.vida = new Vida(context, this);
        this.puntosVida = MAX_PUNTOS_VIDA;
        this.animacion = animacion;
        this.estadoJugador=new EstadoJugador(this);
    }

    public void actualizar() {

        velX = joystick.getDireccionPresionX() * VELMAX;
        velY = joystick.getDireccionPresionY() * VELMAX;
        posX += velX;
        posY += velY;

        if (velX != 0 || velY != 0) {

            double distance = Math.sqrt(Math.pow(0 - velX, 2) + Math.pow(0 - velY, 2));

            dirX = velX / distance;
            dirY = velY / distance;

        }
        estadoJugador.actualizar();

    }

    @Override
    public void dibujar(Canvas lienzo, Disposicion disposicion) {

        animacion.dibujar(lienzo,disposicion,this);
        vida.dibujar(lienzo, disposicion);
    }

    public static int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        if (puntosVida >= 0) {
            this.puntosVida = puntosVida;
        }

    }

    public EstadoJugador getEstadoJugador() {
        return estadoJugador;
    }

    public void setEstadoJugador(EstadoJugador estadoJugador) {
        this.estadoJugador = estadoJugador;
    }
}
