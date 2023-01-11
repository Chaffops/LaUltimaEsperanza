package com.example.laultimaesperanza.entidades;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.laultimaesperanza.Joystick;
import com.example.laultimaesperanza.MotorGrafico;
import com.example.laultimaesperanza.R;


public class Jugador extends Entidad {
    public static final double VELJUGADOR = 400.0;
    public static final double VELMAX = VELJUGADOR / MotorGrafico.MAX_APS;


    private Joystick joystick;


    public Jugador(Context context, Joystick j, double x, double y, double r) {
        super(context, ContextCompat.getColor(context, R.color.jugador), x, y, r);
        this.joystick = j;
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

    }

}
