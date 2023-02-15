package com.example.laultimaesperanza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.laultimaesperanza.database.Controlador;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
            surv.setText(R.string.labelMuerte);
            bntVolverGuardar.setText(R.string.labelGuardarS);
        } else {
            surv.setText(R.string.labelSobrevivido);
            bntVolverGuardar.setText(R.string.labelVolver);
        }

        puntos.setText(String.valueOf(recibido.getInt("puntos")));
        dinero.setText(String.valueOf(recibido.getInt("dinero")));
        ronda.setText(String.valueOf(recibido.getInt("ronda")));


    }

    public void irPirncipal(View view) {


        if (surv.getText().equals("Muerto")) {
            control.gameOver();
        } else {
            control.insertarInfoJuego(recibido.getFloat("velocidad"), recibido.getInt("vida"), recibido.getInt("daño"), recibido.getInt("ronda"), recibido.getInt("dinero"), recibido.getInt("puntos"));
        }
        Intent iPrici = new Intent(this, MainActivity.class);
        startActivity(iPrici);

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
