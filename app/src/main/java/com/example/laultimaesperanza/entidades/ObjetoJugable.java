package com.example.laultimaesperanza.entidades;

import android.graphics.Canvas;

public abstract class ObjetoJugable {
    protected double posX;
    protected double posY;
    protected double velX;
    protected double velY;
    protected double dirX=1;
    protected double dirY;


    public ObjetoJugable(double positionX, double positionY) {
        this.posX = positionX;
        this.posY = positionY;


    }


    public abstract void dibujar(Canvas canvas);

    public abstract void actualizar();

    protected double getPosicionX() {
        return posX;
    }

    protected double getPosicionY() {
        return posY;
    }

    protected static double getDistanciaEntreObjetos(ObjetoJugable obj1, ObjetoJugable obj2) {

        return Math.sqrt(
                Math.pow(obj2.getPosicionX() - obj1.getPosicionX(), 2) +
                        Math.pow(obj2.getPosicionY() - obj1.getPosicionY(), 2)
        );
    }


    protected double getDireccionX() {
        return dirX;
    }

    protected double getDireccionY() {
        return dirY;
    }
}

