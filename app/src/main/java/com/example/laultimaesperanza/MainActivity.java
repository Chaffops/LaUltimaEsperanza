package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
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

    private int volumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = new Controlador(MainActivity.this);
        SQLiteDatabase db = control.getWritableDatabase();


    }

    public void empezarJuego(View vista) {

        Bundle datos = new Bundle();
        /*datos.putInt("velocidad",);
        int velocidad,int vidaInicial ,int ronda,int dinero, int puntuacion*/
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
            long aber=control.insertarAjustes((String) x[0], (int) x[1], (String) x[2]);


            Toast.makeText(MainActivity.this, "Ajustes guardados", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Tiene que introducir un nombre para guardar los ajustes", Toast.LENGTH_LONG).show();
        }
    }


    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }
}