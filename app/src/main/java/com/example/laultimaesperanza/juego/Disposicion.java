package com.example.laultimaesperanza.juego;

import android.graphics.Rect;

import com.example.laultimaesperanza.entidades.ObjetoJugable;

public class Disposicion {

    public final Rect DISPOSICION_RECT;
    private int wPixel,hPixel;
    private double centrarDisposicionX;
    private double centrarDisposicionY;
    private double disposicionCentralX;
    private double centrarX;
    private double disposicionCentralY;
    private double centrarY;
    private ObjetoJugable objetoCentral;


    public Disposicion( int wPixel, int hPixel, ObjetoJugable objetoCentral) {
        this.objetoCentral = objetoCentral;
        this.wPixel=wPixel;
        this.hPixel=hPixel;

        DISPOSICION_RECT=new Rect(0,0,wPixel,hPixel);


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

    public Rect getDisposicionJuego() {
        return new Rect((int) (centrarX-wPixel/2), (int) (centrarY-hPixel/2), (int) (centrarX+wPixel/2), (int) (centrarY+hPixel/2));
    }
}
