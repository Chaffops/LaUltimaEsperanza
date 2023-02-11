package com.example.laultimaesperanza.juego;

import com.example.laultimaesperanza.entidades.ObjetoJugable;

public class Disposicion {

    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double gameCenterX;
    private double displayCenterY;
    private double gameCenterY;
    private ObjetoJugable objetoCentral;


    public Disposicion( int wPixel, int hPixel, ObjetoJugable objetoCentral) {
        this.objetoCentral = objetoCentral;

        displayCenterX= wPixel/2.0;
        displayCenterY= hPixel/2.0;

    }

    public void actualizar(){

        gameCenterX = objetoCentral.getPosicionX();
        gameCenterY = objetoCentral.getPosicionY();


        gameToDisplayCoordinateOffsetX=displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY=displayCenterY - gameCenterY;

    }


    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinateOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinateOffsetY;
    }
}
