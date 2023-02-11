package com.example.laultimaesperanza.graficos;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Dibujos {

    private DibujosImagenes dibujosImagenes;
    private Rect rect;

    public Dibujos(DibujosImagenes dibujosImagenes,Rect rect) {
        this.dibujosImagenes=dibujosImagenes;
        this.rect=rect;
    }

    public void dibujar(Canvas lienzo,int x ,int y) {
        lienzo.drawBitmap(dibujosImagenes.getBitmap(),rect,new Rect(x,y,x+getW(),y+getH()),null);

    }

    public int getW() {
        return rect.width();
    }

    public int getH() {
        return rect.height();
    }
}
