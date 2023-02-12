package com.example.laultimaesperanza.graficos;

import android.graphics.Canvas;

import com.example.laultimaesperanza.entidades.EstadoJugador;
import com.example.laultimaesperanza.entidades.Jugador;
import com.example.laultimaesperanza.juego.Disposicion;

public class Animacion {

    private Dibujos[] dibujos;
    private int tiempoParaCambio;
    private final int MAX_TIEMPO_CAMBIO=10;


    public Animacion(Dibujos[] todosDibujos) {

        this.dibujos = todosDibujos;

    }

    private int tipoMovimiento =1;


    public void dibujar(Canvas lienzo, Disposicion disposicion, Jugador jugador) {
        switch (jugador.getEstadoJugador().getEstado()){
            case PARADO:
                dibujarFrame(lienzo,disposicion,jugador, dibujos[0]);
                break;
            case EMPIEZA:
                tiempoParaCambio=5;
                dibujarFrame(lienzo,disposicion,jugador, dibujos[tipoMovimiento]);

                break;
            case MOVIMIENTO:
                tiempoParaCambio--;
                if(tiempoParaCambio==0) {
                    tiempoParaCambio = MAX_TIEMPO_CAMBIO;
                    cambio();
                }
                dibujarFrame(lienzo,disposicion,jugador, dibujos[tipoMovimiento]);

                break;
            default:
                break;
        }

    }

    private void cambio() {
        tipoMovimiento=(tipoMovimiento==1)?2:1;
    }

    public void dibujarFrame(Canvas lienzo, Disposicion disposicion, Jugador jugador, Dibujos dibujos) {
        dibujos.dibujar(lienzo,
                (int) disposicion.disposicionJuegoX(jugador.getPosicionX()) - (dibujos.getW() / 2),
                (int) disposicion.disposicionJuegoY(jugador.getPosicionY()) - (dibujos.getH()) / 2);
    }
}
