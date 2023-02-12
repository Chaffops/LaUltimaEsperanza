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

        juego=new Juego(this,this ,1);

        setContentView(juego);
    }

    public void irPirncipal(){

        Intent iPrici = new Intent(this, MainActivity.class);
        startActivity(iPrici);
    }

    @Override
    protected void onPause() {
        juego.parar();
        super.onPause();
    }
}