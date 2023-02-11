package com.example.laultimaesperanza.entidades;

public class EstadoJugador {

    public enum Estado {
        PARADO,
        EMPIEZA,
        MOVIMIENTO
    }

    private Jugador jugador;
    private Estado estado;


    public EstadoJugador(Jugador jugador) {
        this.jugador = jugador;
        this.estado = Estado.PARADO;
    }

    public void actualizar() {
        switch (estado) {
            case PARADO:
                estado = (jugador.velX != 0 && jugador.velY != 0) ? Estado.EMPIEZA :estado;
                break;
            case EMPIEZA:
                estado = (jugador.velX != 0 && jugador.velY != 0) ? Estado.MOVIMIENTO :estado;
                break;
            case MOVIMIENTO:
                estado = (jugador.velX == 0 && jugador.velY == 0) ? Estado.PARADO : estado;

                break;
            default:

                break;
        }
    }

    public Estado getEstado() {
        return estado;
    }
}
