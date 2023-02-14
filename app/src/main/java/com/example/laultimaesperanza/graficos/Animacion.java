package com.example.laultimaesperanza.graficos;

import android.graphics.Canvas;

import com.example.laultimaesperanza.entidades.Jugador;
import com.example.laultimaesperanza.entidades.Zombi;
import com.example.laultimaesperanza.juego.Disposicion;

public class Animacion {

    private Dibujos[] dibujos;
    private int tiempoParaCambio=0;
    private final int MAX_TIEMPO_CAMBIO=5;
    private int tiempoParaCambioZombi=0;


    public Animacion(Dibujos[] todosDibujos) {

        this.dibujos = todosDibujos;

    }

    private int tipoMovimientoJugador =1;
    private int tipoMovimientoZombi =3;



    public void dibujar(Canvas lienzo, Disposicion disposicion, Jugador jugador) {
        switch (jugador.getEstadoJugador().getEstado()){
            case PARADO:
                dibujarFrame(lienzo,disposicion,jugador, dibujos[0]);
                break;
            case EMPIEZA:
                tiempoParaCambio=5;
                dibujarFrame(lienzo,disposicion,jugador, dibujos[tipoMovimientoJugador]);

                break;
            case MOVIMIENTO:
                tiempoParaCambio--;
                if(tiempoParaCambio==0) {
                    tiempoParaCambio = MAX_TIEMPO_CAMBIO;
                    cambio();
                }
                dibujarFrame(lienzo,disposicion,jugador, dibujos[tipoMovimientoJugador]);

                break;
            default:
                break;
        }

    }

    public void dibujar(Canvas lienzo, Disposicion disposicion, Zombi zombi) {

        if(tiempoParaCambioZombi==0){
            tiempoParaCambioZombi=MAX_TIEMPO_CAMBIO;
            cambioZombi();
        }else {
            tiempoParaCambioZombi--;
        }
        dibujarFrame(lienzo,disposicion,zombi, dibujos[tipoMovimientoZombi]);

    }



    private void cambio() {
        tipoMovimientoJugador =(tipoMovimientoJugador ==1)?2:1;
    }

    private void cambioZombi() {
        tipoMovimientoZombi =(tipoMovimientoZombi ==3)?4:3;
    }


    public void dibujarFrame(Canvas lienzo, Disposicion disposicion, Jugador jugador, Dibujos dibujos) {
        dibujos.dibujar(lienzo,
                (int) disposicion.disposicionJuegoX(jugador.getPosicionX()) - (dibujos.getW() / 2),
                (int) disposicion.disposicionJuegoY(jugador.getPosicionY()) - (dibujos.getH()) / 2);
    }
    public void dibujarFrame(Canvas lienzo, Disposicion disposicion, Zombi zombi, Dibujos dibujos) {
        dibujos.dibujar(lienzo,
                (int) disposicion.disposicionJuegoX(zombi.getPosicionX()) - (dibujos.getW() / 2),
                (int) disposicion.disposicionJuegoY(zombi.getPosicionY()) - (dibujos.getH()) / 2);
    }
}
