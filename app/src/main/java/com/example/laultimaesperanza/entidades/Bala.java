package com.example.laultimaesperanza.entidades;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.laultimaesperanza.juego.MotorGrafico;
import com.example.laultimaesperanza.R;


public class Bala extends Entidad {
    public static final double VELBALA = 1000.0;
    public static final double VELMAX = VELBALA / MotorGrafico.MAX_APS;


    private final Jugador soldado;

    public Bala(Context context, Jugador j) {
        super(context,
                ContextCompat.getColor(context, R.color.bala),
                j.getPosicionX(),
                j.getPosicionY(),
                10
        );
        this.soldado = j;

        velX = soldado.getDireccionX() * VELMAX;
        velY = soldado.getDireccionY() * VELMAX;

    }

    @Override
    public void actualizar() {
        posX += velX;
        posY += velY;

    }
}
