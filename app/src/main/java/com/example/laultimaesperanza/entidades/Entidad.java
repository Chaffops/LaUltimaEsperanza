package com.example.laultimaesperanza.entidades;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Entidad extends ObjetoJugable {

    protected double radio;
    protected Paint color;

    public Entidad(Context context, int c, double x, double y, double r) {
        super(x, y);
        this.radio = r;

        color = new Paint();
        color.setColor(c);

    }

    public static boolean hayColision(Entidad obj1, Entidad obj2) {
        double distancia = getDistanciaEntreObjetos(obj1, obj2);
        double distanciaColision = obj1.getRadio() + obj2.getRadio();
        if (distancia < distanciaColision) {
            return true;
        }else {
            return false;
        }

    }

    private double getRadio() {
        return radio;
    }

    public void dibujar(Canvas lienzo) {
        lienzo.drawCircle((float) posX, (float) posY, (float) radio, color);
    }
}
