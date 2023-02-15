package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.laultimaesperanza.database.Controlador;
import com.example.laultimaesperanza.juego.Juego;

public class PantallaJuego extends AppCompatActivity {

    Juego juego;

    SoundPool sp;

    int audio;

    int volumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window ventana = getWindow();
        ventana.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle recibido = getIntent().getExtras();

        juego = new Juego(this, this, recibido.getInt("ronda"), recibido.getInt("daño"), recibido.getFloat("velocidad"), recibido.getInt("vida"), recibido.getInt("dinero"), recibido.getInt("puntos"));

        sp = new SoundPool.Builder().setMaxStreams(1).setAudioAttributes(new AudioAttributes.Builder().build()).build();
        audio = sp.load(this, R.raw.damage, 1);
        Controlador control = new Controlador(this);
        Object[] ajustes = control.recibirAjustes();
        if (ajustes != null) {
            volumen = (int) ajustes[1];
        }else{
            volumen=0;
        }


        setContentView(juego);
    }

    public void irGameOver(int ronda, int daño, float velocidad, int vida, int dinero, int puntos) {

        Bundle datos = new Bundle();
        datos.putFloat("velocidad", velocidad);
        datos.putInt("vida", vida);
        datos.putInt("daño", daño);
        datos.putInt("ronda", ronda);
        datos.putInt("dinero", dinero);
        datos.putInt("puntos", puntos);

        Intent iOver = new Intent(this, GameOver.class);
        iOver.putExtras(datos);
        startActivity(iOver);
        try {
            onDestroy();
        } catch (IllegalStateException ex) {

        }

    }

    @Override
    protected void onPause() {
        juego.parar();
        super.onPause();
    }

    public void sonidoDaño() {
        sp.autoPause();
        sp.play(audio, (float) volumen / 10, (float) volumen / 10, 0, 0, 1);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}



















