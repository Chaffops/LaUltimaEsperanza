package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laultimaesperanza.database.Controlador;
import com.example.laultimaesperanza.dialogos.DialogoFragment;
import com.example.laultimaesperanza.pantallasYvistas.FragmentPrincipal;
import com.example.laultimaesperanza.pantallasYvistas.FragmentScore;
import com.example.laultimaesperanza.pantallasYvistas.FragmentTienda;

import java.util.Locale;

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
            dinero = (int) info[4];
            puntos = (int) info[5];
        }
        Object[] x = control.recibirAjustes();
        if (x != null) {
            Locale locale = null;
            if (((String) x[2]).equals("Español")) {
                locale = new Locale("es");
            } else if (((String) x[2]).equals("Ingles")) {
                locale = new Locale("en");
            }
            Resources resources = this.getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.setLocale(locale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
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
            dinero = 0;
            puntos = 0;

        } else {
            velocidad = (float) info[0];
            vida = (int) info[1];
            daño = (int) info[2];
            ronda = (int) info[3] + 1;
            dinero = (int) info[4];
            puntos = (int) info[5];
        }

        Bundle datos = new Bundle();
        datos.putFloat("velocidad", velocidad);
        datos.putInt("vida", vida);
        datos.putInt("daño", daño);
        datos.putInt("ronda", ronda);
        datos.putInt("dinero", dinero);
        datos.putInt("puntos", puntos);

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
        Object[] y = control.recibirAjustes();

        if (x != null) {
            control.insertarAjustes((String) x[0], (int) x[1], (String) x[2]);

            if (!x[2].equals(y[2])) {
                Locale locale = null;
                if (((String) x[2]).equals("Español")) {
                    locale = new Locale("es");
                } else if (((String) x[2]).equals("Ingles")) {
                    locale = new Locale("en");
                }

                Resources resources = this.getResources();
                Configuration configuration = resources.getConfiguration();
                configuration.setLocale(locale);
                resources.updateConfiguration(configuration, resources.getDisplayMetrics());

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
            Toast.makeText(MainActivity.this, R.string.toastGuardarAjustes, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, R.string.toastNeedNombre, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}