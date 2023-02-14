package com.example.laultimaesperanza.juego;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;

import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.laultimaesperanza.PantallaJuego;
import com.example.laultimaesperanza.entidades.Bala;
import com.example.laultimaesperanza.entidades.Entidad;
import com.example.laultimaesperanza.entidades.Jugador;
import com.example.laultimaesperanza.entidades.Zombi;
import com.example.laultimaesperanza.graficos.Animacion;
import com.example.laultimaesperanza.graficos.DibujosImagenes;
import com.example.laultimaesperanza.mapa.Mapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Juego extends SurfaceView implements SurfaceHolder.Callback {

    private final DibujosImagenes dibujosImagenes;
    private final Mapa mapa;
    private Jugador jugador;
    private Joystick joystick;
    MotorGrafico mGrafico;

    private List<Zombi> Zombis = new ArrayList<Zombi>();
    private List<Bala> Balas = new ArrayList<Bala>();
    private int joystickPointerID = 0;
    private int numeroBalasIniciales = 0;

    public static int ronda;

    Animacion animacion;

    PantallaJuego pt;
    private Disposicion disposicion;

    public Juego(Context context, PantallaJuego pt, int ronda, int daño, float velocidad, int vida ) {
        super(context);
        this.pt = pt;

        Juego.ronda = ronda;

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mGrafico = new MotorGrafico(this, surfaceHolder);

        dibujosImagenes = new DibujosImagenes(context);

        joystick = new Joystick(250, 500, 120, 80);

        animacion = new Animacion(dibujosImagenes.getTodosDibujos());
        jugador = new Jugador(getContext(), joystick, 2 * 500, 500, 30, animacion, daño,velocidad, vida);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        disposicion = new Disposicion(displayMetrics.widthPixels, displayMetrics.heightPixels, jugador);

        mapa = new Mapa(dibujosImagenes);

        setFocusable(true);

        Thread tiempo = new Thread(() -> {
            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pt.irPirncipal();

        });
        tiempo.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.getEsPresionado()) {

                    numeroBalasIniciales++;

                } else if (joystick.HayPulsacion((double) event.getX(), (double) event.getY())) {
                    joystickPointerID = event.getPointerId(event.getActionIndex());
                    joystick.setEsPresionado(true);
                } else {
                    numeroBalasIniciales++;

                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getEsPresionado()) {
                    joystick.setDireccionDePresion((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:

                if (joystickPointerID == event.getPointerId(event.getActionIndex())) {
                    joystick.setEsPresionado(false);
                    joystick.resetDireccion();

                }


                return true;

        }


        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mGrafico.empieza();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mapa.dibujar(canvas, disposicion);

        joystick.dibujar(canvas);
        jugador.dibujar(canvas, disposicion);
        for (Zombi zombi : Zombis) {
            zombi.dibujar(canvas, disposicion);
        }
        for (Bala bala : Balas) {
            bala.dibujar(canvas, disposicion);
        }
    }


    public void update() {


        if (Jugador.getPuntosVida() <= 0) {
//aqui va el codigo para cuando pierdes.
            pt.irPirncipal();

            return;
        }

        joystick.actualizar();
        jugador.actualizar();

        if (Zombi.listoAparecer()) {
            Zombis.add(new Zombi(getContext(), jugador, animacion, ronda));
        }
        while (numeroBalasIniciales > 0) {
            Balas.add(new Bala(getContext(), jugador));
            numeroBalasIniciales--;
        }

        for (Zombi zombi : Zombis) {
            zombi.actualizar();
        }
        for (Bala bala : Balas) {
            bala.actualizar();
        }

        Iterator<Zombi> itZombi = Zombis.iterator();
        while (itZombi.hasNext()) {
            Zombi zombi = itZombi.next();
            if (Entidad.hayColision(zombi, jugador)) {

                itZombi.remove();
                jugador.setPuntosVida(jugador.getPuntosVida() - 1);
                continue;
            }
            Iterator<Bala> itBala = Balas.iterator();
            while (itBala.hasNext()) {
                Entidad bala = itBala.next();
                if (Entidad.hayColision(bala, zombi)) {
                    itBala.remove();
                    if (zombi.getConteo() <= 0) {
                        itZombi.remove();
                    } else {
                        zombi.setConteo(zombi.getConteo() - jugador.getDaño());
                        if (zombi.getConteo() <= 0) {
                            itZombi.remove();
                        }
                    }
                    break;
                }

            }
        }
        disposicion.actualizar();
    }

    public void parar() {
        mGrafico.para();
    }
}
