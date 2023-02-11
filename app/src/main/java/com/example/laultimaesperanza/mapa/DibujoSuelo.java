package com.example.laultimaesperanza.mapa;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.laultimaesperanza.graficos.Dibujos;
import com.example.laultimaesperanza.graficos.DibujosImagenes;

public class DibujoSuelo extends Imagen {

    private final Dibujos imagen;
    int x=0,y=0;

    public DibujoSuelo(DibujosImagenes dibujosImagenes, Rect posicion ,int tipoSuelo) {
        super(posicion);

        switch (tipoSuelo) {
            case 5:
                x = 0;
                y = 1;
                break;
            case 6:
                x = 1;
                y = 1;
                break;
            case 7:
                x = 2;
                y = 1;
                break;
            case 8:
                x = 3;
                y = 1;
                break;
            case 10:
                x = 0;
                y = 2;
                break;
            case 11:
                x = 1;
                y = 2;
                break;
            case 12:
                x = 2;
                y = 2;
                break;
            case 9:
            default:
                x = 4;
                y = 1;
                break;
        }
        imagen= dibujosImagenes.getSueloImagen(x,y);
    }

    @Override
    public void dibujar(Canvas lienzo) {
        imagen.dibujar(lienzo, posicionMapa.left, posicionMapa.top);
    }
}
