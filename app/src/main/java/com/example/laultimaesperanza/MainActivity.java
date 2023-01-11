package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.laultimaesperanza.dialogos.DialogoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void empezarJuego(View vista){

        Intent iJuego=new Intent(this,PantallaJuego.class);
        startActivity(iJuego);

    }

    public void ajustar(View v){
        DialogoFragment dialogo=new DialogoFragment();
        dialogo.show(getSupportFragmentManager(),"DialogoFragment");

    }

}