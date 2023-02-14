package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.laultimaesperanza.database.Controlador;
import com.example.laultimaesperanza.dialogos.DialogoFragment;
import com.example.laultimaesperanza.pantallasYvistas.FragmentPrincipal;
import com.example.laultimaesperanza.pantallasYvistas.FragmentScore;
import com.example.laultimaesperanza.pantallasYvistas.FragmentTienda;

public class MainActivity extends AppCompatActivity {

    FragmentManager fManager = getSupportFragmentManager();

    Controlador control;

    DialogoFragment dialogo;

    private int dinero = 0;

    private int puntos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = new Controlador(MainActivity.this);

        Object[] info = control.recibirInfoJuego();
        if (info != null) {
            info[5] = dinero;
            info[6] = puntos;
        }
    }

    public void empezarJuego(View vista) {
        float velocidad;
        int vida, daño, ronda;
        Object[] info = control.recibirInfoJuego();
        if (info == null) {
            velocidad = 0;
            vida = 10;
            daño = 1;
            ronda = 1;
        } else {
            velocidad = (float) info[0];
            vida = (int) info[1];
            daño = (int) info[2];
            ronda = (int) info[3];
            dinero = (int) info[4];
            puntos = (int) info[5];
        }


        Bundle datos = new Bundle();
        datos.putFloat("velocidad", velocidad);
        datos.putInt("vida", vida);
        datos.putInt("daño", daño);
        datos.putInt("ronda", ronda);

        Intent iJuego = new Intent(this, PantallaJuego.class);
        iJuego.putExtras(datos);
        startActivity(iJuego);

    }

    public void ajustar(View v) {
        dialogo = new DialogoFragment();
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

    public void guardar(View view) {

        Object[] x = dialogo.getInfo();

        if (x != null) {
            control.insertarAjustes((String) x[0], (int) x[1], (String) x[2]);


            Toast.makeText(MainActivity.this, "Ajustes guardados", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Tiene que introducir un nombre para guardar los ajustes", Toast.LENGTH_LONG).show();
        }
    }

    /*public void comprar(View view) {

        Object[] x = dialogo.getInfo();

        if (x != null) {
            control.insertarAjustes((String) x[0], (int) x[1], (String) x[2]);


            Toast.makeText(MainActivity.this, "Ajustes guardados", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Tiene que introducir un nombre para guardar los ajustes", Toast.LENGTH_LONG).show();
        }
    }*/


}