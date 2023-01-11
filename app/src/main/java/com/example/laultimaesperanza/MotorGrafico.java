package com.example.laultimaesperanza;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MotorGrafico extends Thread {
    public static final double MAX_APS = 60.0;
    private static final double MEDIA_APS = 1000/MAX_APS;
    private final Juego juego;
    private boolean funciona;
    private SurfaceHolder surfaceHolder;
    private double mediaAPS;
    private double mediaFPS;

    public MotorGrafico(Juego juego, SurfaceHolder s) {
        this.surfaceHolder = s;
        this.juego = juego;
    }

    public void empieza() {
        funciona = true;
        start();

    }

    @Override
    public void run() {
        super.run();

        int numActuali = 0;
        int numDibujos = 0;

        long inicioTiempo;
        long diferenciaTiempo;
        long tiempoParada;

        Canvas canvas = null;
        inicioTiempo = System.currentTimeMillis();
        while (funciona) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    juego.update();
                    numActuali++;
                    juego.draw(canvas);
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        numDibujos++;
                    } catch (Exception e) {

                    }
                }
            }

            diferenciaTiempo = System.currentTimeMillis() - inicioTiempo;
            tiempoParada = (long) (numActuali * MEDIA_APS - diferenciaTiempo);
            if (tiempoParada > 0) {
                try {
                    sleep(tiempoParada);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (tiempoParada < 0 && numActuali < MAX_APS - 1) {
                juego.update();
                numActuali++;
                diferenciaTiempo = System.currentTimeMillis() - inicioTiempo;
                tiempoParada = (long) (numActuali * MEDIA_APS - diferenciaTiempo);
            }

            diferenciaTiempo = System.currentTimeMillis() - inicioTiempo;
            if (diferenciaTiempo >= 1000) {
                mediaAPS = numActuali / (0.000001 * diferenciaTiempo);
                mediaFPS = numDibujos / (0.000001 * diferenciaTiempo);
                numActuali = 0;
                numDibujos = 0;
                inicioTiempo = System.currentTimeMillis();


            }

        }


    }
}
