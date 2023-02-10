package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.laultimaesperanza.dialogos.DialogoFragment;
import com.example.laultimaesperanza.pantallasYvistas.FragmentPrincipal;
import com.example.laultimaesperanza.pantallasYvistas.FragmentScore;
import com.example.laultimaesperanza.pantallasYvistas.FragmentTienda;

public class MainActivity extends AppCompatActivity {

    FragmentManager fManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void empezarJuego(View vista) {

        Intent iJuego = new Intent(this, PantallaJuego.class);
        startActivity(iJuego);

    }

    public void ajustar(View v) {
        DialogoFragment dialogo = new DialogoFragment();
        dialogo.show(getSupportFragmentManager(), "DialogoFragment");

    }

    public void irPrincipal(View vista) {

        fManager.beginTransaction()
                .replace(R.id.ContenedorFragments, FragmentPrincipal.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("")
                .commit();

    }


    public void irTienda(View vista) {
        fManager.beginTransaction()
                .replace(R.id.ContenedorFragments, FragmentTienda.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("")
                .commit();


    }

    public void irScoreBoard(View vista) {
        fManager.beginTransaction()
                .replace(R.id.ContenedorFragments, FragmentScore.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("")
                .commit();


    }


}