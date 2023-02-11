package com.example.laultimaesperanza.juego;

import com.example.laultimaesperanza.entidades.ObjetoJugable;

public class Disposicion {

    private double centrarDisposicionX;
    private double centrarDisposicionY;
    private double disposicionCentralX;
    private double centrarX;
    private double disposicionCentralY;
    private double centrarY;
    private ObjetoJugable objetoCentral;


    public Disposicion( int wPixel, int hPixel, ObjetoJugable objetoCentral) {
        this.objetoCentral = objetoCentral;

        disposicionCentralX = wPixel/2.0;
        disposicionCentralY = hPixel/2.0;

    }

    public void actualizar(){

        centrarX = objetoCentral.getPosicionX();
        centrarY = objetoCentral.getPosicionY();


        centrarDisposicionX = disposicionCentralX - centrarX;
        centrarDisposicionY = disposicionCentralY - centrarY;

    }


    public double disposicionJuegoX(double x) {
        return x + centrarDisposicionX;
    }

    public double disposicionJuegoY(double y) {
        return y + centrarDisposicionY;
    }
}
