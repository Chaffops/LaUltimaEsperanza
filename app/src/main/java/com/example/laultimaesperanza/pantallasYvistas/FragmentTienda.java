package com.example.laultimaesperanza.pantallasYvistas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.laultimaesperanza.R;
import com.example.laultimaesperanza.database.Controlador;

public class FragmentTienda extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView labelDinero;
    TextView labelVelocidad;
    TextView labelCurar;
    TextView labelDaño;

    public FragmentTienda() {
    }

    public static FragmentTienda newInstance(String param1, String param2) {
        FragmentTienda fragment = new FragmentTienda();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tienda, container, false);
        labelDinero = view.findViewById(R.id.labelLoqueTengo);
        labelVelocidad = view.findViewById(R.id.labelVelocidad);
        labelCurar = view.findViewById(R.id.labelCurar);
        labelDaño = view.findViewById(R.id.labelDaño);

        int dineroGastado = 0;
        int precioCorrer = 60, precioCurar = 10, precioDaño = 40;

        Controlador control = new Controlador(getContext());
        Object[] info = control.recibirInfoJuego();


        if (info != null) {
            labelDinero.setText(String.valueOf(info[4]));
            int numVel = ((float) info[0] == 0.0) ? 1 : ((float) info[0] == 0.25) ? 2 : ((float) info[0] == 0.50) ? 3 : ((float) info[0] == 0.75) ? 4 : ((float) info[0] == 1.0) ? 5 : 6;
            labelVelocidad.setText(String.valueOf(precioCorrer) + "$ - " + numVel + "/5");
            labelCurar.setText(String.valueOf(precioCurar) + "$ - " + String.valueOf(info[1]) + "/10");
            labelDaño.setText(String.valueOf(precioDaño) + "$ - " + String.valueOf(info[2]) + "/5");
        } else {
            labelDinero.setText("0");
            labelVelocidad.setText("0");
            labelCurar.setText("10");
            labelDaño.setText("1");
        }


        Button btnComprar = view.findViewById(R.id.idComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());
                dialogo.setMessage("¿Seguro que desea realizar la compra?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog seguro = dialogo.create();
                seguro.setTitle("Alerta");
                seguro.show();

            }
        });


        Button btnCorrerMas = view.findViewById(R.id.btnCorrerMas);
        btnCorrerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                labelDinero.setTextColor(Color.RED);
            }
        });

        Button btnCorrerMenos = view.findViewById(R.id.btnCorrerMenos);
        btnCorrerMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            }
        });

        Button btnCurarMas = view.findViewById(R.id.btnCurarMas);
        btnCurarMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                labelDinero.setTextColor(Color.RED);

            }
        });
        Button btnCurarMenos = view.findViewById(R.id.btnCurarMenos);
        btnCurarMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            }
        });

        Button btnDañoMas = view.findViewById(R.id.btnDañoMas);
        btnDañoMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                labelDinero.setTextColor(Color.RED);

            }
        });
        Button btnDañoMenos = view.findViewById(R.id.btnDañoMenos);
        btnDañoMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            }
        });

        return view;
    }

}