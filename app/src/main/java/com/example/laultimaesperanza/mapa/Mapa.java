package com.example.laultimaesperanza.mapa;

import static com.example.laultimaesperanza.mapa.GridMapa.ALTO_IMAGEN;
import static com.example.laultimaesperanza.mapa.GridMapa.ANCHO_IMAGEN;
import static com.example.laultimaesperanza.mapa.GridMapa.COLUMNAS;
import static com.example.laultimaesperanza.mapa.GridMapa.FILAS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.laultimaesperanza.graficos.DibujosImagenes;
import com.example.laultimaesperanza.juego.Disposicion;

public class Mapa {

    private final GridMapa gridMapa;
    private Imagen[][] mapaImagenes;
    private DibujosImagenes dibujosImagenes;
    private Bitmap mapaBitmap;

    public Mapa(DibujosImagenes dibujosImagenes){
        gridMapa=new GridMapa();
        this.dibujosImagenes=dibujosImagenes;

        crear();

    }

    private void crear() {
        int [][] grid=gridMapa.getMapa();
        mapaImagenes=new Imagen[FILAS][COLUMNAS];
        for (int x = 0; x < FILAS; x++) {
            for (int y = 0; y < COLUMNAS; y++) {
                mapaImagenes[x][y]=Imagen.getImagen(
                        grid[x][y],
                        dibujosImagenes,
                        getRectPorIndex(x,y));

            }
        }
        Bitmap.Config config= Bitmap.Config.ARGB_8888;
        mapaBitmap = Bitmap.createBitmap(FILAS*ANCHO_IMAGEN,COLUMNAS*ALTO_IMAGEN,config);

        Canvas lienzoMapa=new Canvas(mapaBitmap);

        for (int x = 0; x < FILAS; x++) {
            for (int y = 0; y < COLUMNAS; y++) {
                mapaImagenes[x][y].dibujar(lienzoMapa);

            }
        }

    }

    private Rect getRectPorIndex(int x, int y) {

        return new Rect(x * ANCHO_IMAGEN, y * ALTO_IMAGEN, (x + 1) * ANCHO_IMAGEN, (y + 1) * ALTO_IMAGEN);
    }

    public void dibujar(Canvas canvas, Disposicion disposicion) {

        canvas.drawBitmap(mapaBitmap,disposicion.getDisposicionJuego(),disposicion.DISPOSICION_RECT,null);

    }
}
