package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.laultimaesperanza.database.Controlador;

public class GameOver extends AppCompatActivity {

    TextView surv;
    TextView puntos;
    TextView dinero;
    TextView ronda;

    Button bntVolverGuardar;

    Controlador control;
    Bundle recibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        control = new Controlador(GameOver.this);

        recibido = getIntent().getExtras();

        surv = findViewById(R.id.textMuerteOSobrevi);
        puntos = findViewById(R.id.numPuntos);
        dinero = findViewById(R.id.numDinero);
        ronda = findViewById(R.id.numRonda);
        bntVolverGuardar = findViewById(R.id.btnVolverOGuardar);

        if (recibido.getInt("vida") == 0) {
            surv.setText("Muerto");
            bntVolverGuardar.setText("Guardar Score");
        } else {
            surv.setText("Sobrevivido");
            bntVolverGuardar.setText("Volver");
        }

        puntos.setText(String.valueOf(recibido.getInt("puntos")));
        dinero.setText(String.valueOf(recibido.getInt("dinero")));
        ronda.setText(String.valueOf(recibido.getInt("ronda")));


    }

    public void irPirncipal(View view) {


        if (surv.getText().equals("Muerto")) {
            control.gameOver();

        } else {
            control.insertarInfoJuego(recibido.getFloat("velocidad"), recibido.getInt("vida"), recibido.getInt("daño"), recibido.getInt("ronda"), recibido.getInt("dinero"), recibido.getInt("puntuacion"));
        }
        Intent iPrici = new Intent(this, MainActivity.class);
        startActivity(iPrici);
        try {
            onDestroy();
        } catch (IllegalStateException ex) {

        }
    }

}
