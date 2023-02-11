package com.example.laultimaesperanza.mapa;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.laultimaesperanza.graficos.DibujosImagenes;

public abstract class Imagen {

    protected final Rect posicionMapa;

    public Imagen(Rect posicionMapa) {
        this.posicionMapa = posicionMapa;

    }

    private static final int SUELO1 = 5, SUELO2 = 6, SUELO3 = 7, SUELO4 = 8, VALLA = 9, ARBOL1 = 10, ARBOL2 = 11, ARBOL3 = 12;


    public static Imagen getImagen(int imagen, DibujosImagenes dibujosImagenes, Rect posicion) {

        switch (imagen) {
            case SUELO1:
                return new DibujoSuelo(dibujosImagenes, posicion, SUELO1);
            case SUELO2:
                return new DibujoSuelo(dibujosImagenes, posicion, SUELO2);
            case SUELO3:
                return new DibujoSuelo(dibujosImagenes, posicion, SUELO3);
            case VALLA:
                return new DibujoSuelo(dibujosImagenes, posicion, VALLA);
            case ARBOL1:
                return new DibujoSuelo(dibujosImagenes, posicion, ARBOL1);
            case ARBOL2:
                return new DibujoSuelo(dibujosImagenes, posicion, ARBOL2);
            case ARBOL3:
                return new DibujoSuelo(dibujosImagenes, posicion, ARBOL3);
            case SUELO4:
            default:
                return new DibujoSuelo(dibujosImagenes, posicion, SUELO4);

        }
    }

    public abstract void dibujar(Canvas lienzo);

}
