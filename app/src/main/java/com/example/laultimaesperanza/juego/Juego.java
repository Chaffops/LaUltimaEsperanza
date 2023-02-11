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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Juego extends SurfaceView implements SurfaceHolder.Callback {

    private Jugador jugador;
    private Joystick joystick;
    MotorGrafico mGrafico;

    private List<Zombi> Zombis = new ArrayList<Zombi>();
    private List<Bala> Balas = new ArrayList<Bala>();
    private int joystickPointerID = 0;
    private int numeroBalasIniciales =0;

    PantallaJuego pt;
    private Disposicion disposicion;

    public Juego(Context context,PantallaJuego pt) {
        super(context);
        this.pt=pt;

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        mGrafico = new MotorGrafico(this, surfaceHolder);


        joystick = new Joystick(250, 500, 70, 40);
        jugador = new Jugador(getContext(), joystick, 2 * 500, 500, 30);


        DisplayMetrics displayMetrics=new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        disposicion=new Disposicion(displayMetrics.widthPixels,displayMetrics.heightPixels,jugador);

        setFocusable(true);
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

        joystick.dibujar(canvas);
        jugador.dibujar(canvas ,disposicion);
        for (Zombi zombi : Zombis) {
            zombi.dibujar(canvas,disposicion);
        }
        for (Bala bala : Balas) {
            bala.dibujar(canvas,disposicion);
        }
    }


    public void update() {


        if(Jugador.getPuntosVida() <=0){
//aqui va el codigo para cuando pierdes.
            pt.irPirncipal();

            return;
        }

        joystick.actualizar();
        jugador.actualizar();

        if (Zombi.listoAparecer()) {
            Zombis.add(new Zombi(getContext(), jugador));
        }
        while (numeroBalasIniciales > 0){
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
            Entidad zombi = itZombi.next();
            if (Entidad.hayColision(zombi, jugador)) {

                itZombi.remove();
                jugador.setPuntosVida(jugador.getPuntosVida()-1);
                continue;
            }
            Iterator<Bala> itBala = Balas.iterator();
            while (itBala.hasNext()) {
                Entidad bala = itBala.next();
                if (Entidad.hayColision(bala, zombi)) {
                    itBala.remove();
                    itZombi.remove();
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
