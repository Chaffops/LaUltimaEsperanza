package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.laultimaesperanza.juego.Juego;

public class PantallaJuego extends AppCompatActivity {

    Juego juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window ventana = getWindow();
        ventana.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle recibido=getIntent().getExtras();

        juego=new Juego(this,this ,recibido.getInt("ronda"), recibido.getInt("daño"),recibido.getFloat("velocidad"), recibido.getInt("vida"),recibido.getInt("dinero"),recibido.getInt("puntos"));

        setContentView(juego);
    }

    public void irGameOver( int ronda, int daño, float velocidad, int vida ,int dinero,int puntos){

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
        }catch (IllegalStateException ex){

        }

    }

    @Override
    protected void onPause() {
        juego.parar();
        super.onPause();
    }
}