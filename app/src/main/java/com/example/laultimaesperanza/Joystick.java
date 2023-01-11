package com.example.laultimaesperanza;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {

    private Paint colorCirculoDentro;
    private Paint colorCirculoFuera;
    private int radioCirculoFuera;
    private int radioCirculoDentro;
    private int posCirculoFueraX;
    private int posCirculoFueraY;
    private int posCirculoDentroX;
    private int posCirculoDentroY;
    private double distanciaCentroPresion;
    private boolean esPresionado;
    private double direccionPresionX;
    private double direccionPresionY;


    public Joystick(int posCentralX, int posCentralY, int radioCirculoFuera, int radioCirculoDentro) {
        // para que el joystick este siempre en la misma posicion da igual el dispositivo
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;

        posCirculoFueraX = width / 7;
        posCirculoFueraY = height / 4 * 3;
        posCirculoDentroX = width / 7;
        posCirculoDentroY = height / 4 * 3;

        this.radioCirculoFuera = radioCirculoFuera;
        this.radioCirculoDentro = radioCirculoDentro;

        colorCirculoFuera = new Paint();
        colorCirculoFuera.setColor(Color.GRAY);
        colorCirculoFuera.setAlpha(50);
        colorCirculoFuera.setStyle(Paint.Style.FILL_AND_STROKE);

        colorCirculoDentro = new Paint();
        colorCirculoDentro.setColor(Color.DKGRAY);
        colorCirculoDentro.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    public void dibujar(Canvas c) {
        c.drawCircle(posCirculoFueraX, posCirculoFueraY, radioCirculoFuera, colorCirculoFuera);
        c.drawCircle(posCirculoDentroX, posCirculoDentroY, radioCirculoDentro, colorCirculoDentro);

    }

    public void actualizar() {
        actualizarPosicionCirculoDentro();

    }

    private void actualizarPosicionCirculoDentro() {
        posCirculoDentroX = (int) (posCirculoFueraX + direccionPresionX * radioCirculoFuera);
        posCirculoDentroY = (int) (posCirculoFueraY + direccionPresionY * radioCirculoFuera);

    }

    public boolean HayPulsacion(double posPulsacionX, double posPulsacionY) {
        distanciaCentroPresion = Math.sqrt(Math.pow(posCirculoFueraX - posPulsacionX, 2) + Math.pow(posCirculoFueraY - posPulsacionY, 2));

        return distanciaCentroPresion < radioCirculoFuera;
    }


    public void setDireccionDePresion(double posPulsacionX, double posPulsacionY) {
        //diferencia entre Posicion del Circulo de Fuera y la Posicion Pulsacion
        double diferenciaX = posPulsacionX - posCirculoFueraX;
        double diferenciaY = posPulsacionY - posCirculoFueraY;

        double diferenciaDistancia = Math.sqrt(Math.pow(0 - diferenciaX, 2) + Math.pow(0 - diferenciaY, 2));

        if (diferenciaDistancia < radioCirculoFuera) {// mientras la accion sigue dentro de los circulos
            direccionPresionX = diferenciaX / radioCirculoFuera;
            direccionPresionY = diferenciaY / radioCirculoFuera;

        } else { //cuando te pasas de los circulos que siga funcionando
            direccionPresionX = diferenciaX / diferenciaDistancia;
            direccionPresionY = diferenciaY / diferenciaDistancia;
        }
    }


    public void resetDireccion() {
        direccionPresionX = 0.0;
        direccionPresionY = 0.0;
    }

    public double getDireccionPresionX() {
        return direccionPresionX;
    }

    public double getDireccionPresionY() {
        return direccionPresionY;
    }


    public void setEsPresionado(boolean isPressed) {
        this.esPresionado = isPressed;
    }

    public boolean getEsPresionado() {
        return esPresionado;
    }
}
