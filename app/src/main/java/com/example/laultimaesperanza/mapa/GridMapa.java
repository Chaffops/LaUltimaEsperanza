package com.example.laultimaesperanza.mapa;

public class GridMapa {

    public static final int ANCHO_IMAGEN = 64;
    public static final int ALTO_IMAGEN = 64;

    public static final int FILAS = 60;
    public static final int COLUMNAS = 60;

    private static final int SUELO1 = 5, SUELO2 = 6, SUELO3 = 7, SUELO4 = 8, VALLA = 9, ARBOL1 = 10, ARBOL2 = 11, ARBOL3 = 12;


    private int[][] mapa;

    public GridMapa() {
        mapa = new int[FILAS][COLUMNAS];
        iniciarMapa();

    }

    private void iniciarMapa() {
        int arbol = -1;
        for (int x = 0; x < FILAS; x++) {
            for (int y = 0; y < COLUMNAS; y++) {
                if (x == 0 || y == 0 || x == 59 || y == 59) {
                    mapa[x][y] = VALLA;
                } else {
                    if (arbol == ARBOL2) {
                        mapa[x][y] = ARBOL2;
                        arbol = ARBOL3;
                        continue;
                    } else if (arbol == ARBOL3) {
                        mapa[x][y] = ARBOL3;
                        arbol = -1;
                        continue;
                    }
                    int a = (int) (Math.random() * 20);
                    if (a == ARBOL1) {
                        mapa[x][y] = ARBOL1;
                        arbol = ARBOL2;
                        continue;
                    } else if (a < 5 || a > 10) {
                        mapa[x][y] = SUELO4;
                    } else {
                        mapa[x][y] = a;
                    }

                }
            }
        }

    }


    public int[][] getMapa() {
        return mapa;
    }
}
