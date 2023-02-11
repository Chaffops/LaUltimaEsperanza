package com.example.laultimaesperanza.entidades;

import static com.example.laultimaesperanza.entidades.Jugador.getPuntosVida;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.laultimaesperanza.R;
import com.example.laultimaesperanza.juego.Disposicion;

public class Vida {

    private Jugador jugador;
    private int w, h, m;
    private Paint bordeColor, vidaColor;


    public Vida(Context context, Jugador player) {
        this.jugador = player;
        this.w = 100;
        this.h = 20;
        this.m = 2;

        this.bordeColor = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.barraVidaBorde);
        bordeColor.setColor(borderColor);

        this.vidaColor = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.barraVidaVida);
        vidaColor.setColor(healthColor);
    }

    public void dibujar(Canvas canvas, Disposicion disposicion) {
        float x = (float) jugador.getPosicionX();
        float y = (float) jugador.getPosicionY();
        float distanciaAlJugador = 40;
        float healthPointPercentage = (float) getPuntosVida() / Jugador.MAX_PUNTOS_VIDA;

        float bordeIzq, bordeArriba, bordeDrc, bordeAbajo;

        bordeIzq = x - w / 2;
        bordeDrc = x + w / 2;
        bordeAbajo = y - distanciaAlJugador;
        bordeArriba = bordeAbajo - h;

        canvas.drawRect((float) disposicion.disposicionJuegoX(bordeIzq),
                (float) disposicion.disposicionJuegoY(bordeArriba),
                (float) disposicion.disposicionJuegoX(bordeDrc),
                (float) disposicion.disposicionJuegoY(bordeAbajo), bordeColor);

        float vidaIzq, vidaArriba, vidaDrc, vidaAbajo, vidaW, vidaH;

        vidaW = w - 2 * m;
        vidaH = h - 2 * m;
        vidaIzq = bordeIzq + m;
        vidaDrc = vidaIzq + vidaW * healthPointPercentage;
        vidaAbajo = bordeAbajo - m;
        vidaArriba = bordeAbajo - vidaH;

        canvas.drawRect(
                (float) disposicion.disposicionJuegoX(vidaIzq),
                (float) disposicion.disposicionJuegoY(vidaArriba),
                (float) disposicion.disposicionJuegoX(vidaDrc),
                (float) disposicion.disposicionJuegoY(vidaAbajo),
                vidaColor);

    }

}
