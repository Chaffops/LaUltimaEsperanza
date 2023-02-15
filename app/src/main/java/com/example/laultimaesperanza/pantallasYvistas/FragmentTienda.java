package com.example.laultimaesperanza.pantallasYvistas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    int numVel;

    int dinero;
    int dineroGastado = 0;
    int precioCorrer = 60, precioCurar = 10, precioDaño = 40;

    Object[] cambios;

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


        Controlador control = new Controlador(getContext());
        Object[] info = control.recibirInfoJuego();

        if (info != null) {
            cambios = info.clone();
            dinero = (int) info[4];
            numVel = ((float) info[0] == 0.0) ? 1 : ((float) info[0] == 0.25) ? 2 : ((float) info[0] == 0.50) ? 3 : ((float) info[0] == 0.75) ? 4 : ((float) info[0] == 1.0) ? 5 : 6;
        }


        if (info != null) {
            labelDinero.setText(String.valueOf(info[4]));
            labelVelocidad.setText(String.valueOf(precioCorrer) + "$ - " + numVel + "/5");
            labelCurar.setText(String.valueOf(precioCurar) + "$ - " + String.valueOf(info[1]) + "/10");
            labelDaño.setText(String.valueOf(precioDaño) + "$ - " + String.valueOf(info[2]) + "/5");
        } else {
            labelDinero.setText("0");
            labelVelocidad.setText(String.valueOf(precioCorrer) + "$ - 1/5");
            labelCurar.setText(String.valueOf(precioCurar) + "$ - 10/10");
            labelDaño.setText(String.valueOf(precioDaño) + "$ - 1/5");
        }


        Button btnComprar = view.findViewById(R.id.idComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info[0] != cambios[0] || info[1] != cambios[1] || info[2] != cambios[2] || info[3] != cambios[3] || info[4] != cambios[4] || info[5] != cambios[5]) {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(getActivity());
                    dialogo.setMessage("Confirnmar la compra de " + ((int) info[4] - getDinero()) + "$")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    control.insertarInfoJuego((Float) cambios[0], (Integer) cambios[1], (Integer) cambios[2], (Integer) cambios[3], getDinero(), (Integer) cambios[5]);
                                    FragmentManager fManager = getActivity().getSupportFragmentManager();
                                    fManager.beginTransaction()
                                            .replace(R.id.ContenedorFragments, FragmentTienda.class, null)
                                            .setReorderingAllowed(true)
                                            .addToBackStack("")
                                            .commit();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog seguro = dialogo.create();
                    seguro.setTitle("Confirmacion");
                    seguro.show();
                } else {
                    Toast.makeText(getContext(), R.string.toastNohayCambios, Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button btnCorrerMas = view.findViewById(R.id.btnCorrerMas);
        btnCorrerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dinero >= precioCorrer && !String.valueOf(cambios[0]).equals("1.0")) {
                    labelDinero.setTextColor(Color.RED);
                    setDinero(getDinero() - precioCorrer);
                    labelVelocidad.setTextColor(Color.GREEN);
                    cambios[0] = (float) cambios[0] + 0.25F;
                    int numVelM = ((float) cambios[0] == 0.0) ? 1 : ((float) cambios[0] == 0.25) ? 2 : ((float) cambios[0] == 0.50) ? 3 : ((float) cambios[0] == 0.75) ? 4 : 5;
                    labelVelocidad.setText(String.valueOf(precioCorrer) + "$ - " + numVelM + "/5");
                    labelDinero.setText(String.valueOf(getDinero()));
                } else {
                    Toast.makeText(getContext(), R.string.toastPasta, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnCorrerMenos = view.findViewById(R.id.btnCorrerMenos);
        btnCorrerMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info != null) {
                    if (!String.valueOf(info[0]).equals(String.valueOf(cambios[0]))) {
                        labelDinero.setTextColor(Color.RED);
                        setDinero(getDinero() + precioCorrer);
                        labelVelocidad.setTextColor(Color.GREEN);
                        cambios[0] = (float) cambios[0] - 0.25F;
                        int numVelM = ((float) cambios[0] == 0.0) ? 1 : ((float) cambios[0] == 0.25) ? 2 : ((float) cambios[0] == 0.50) ? 3 : ((float) cambios[0] == 0.75) ? 4 : ((float) cambios[0] == 1.0) ? 5 : 6;
                        labelVelocidad.setText(String.valueOf(precioCorrer) + "$ - " + numVelM + "/5");
                        labelDinero.setText(String.valueOf(getDinero()));
                        if (String.valueOf(info[0]).equals(String.valueOf(cambios[0]))) {
                            labelVelocidad.setTextColor(Color.WHITE);
                            if ((int) cambios[4] == getDinero()) {
                                labelDinero.setTextColor(Color.WHITE);
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), R.string.toastQuitarTienda, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button btnCurarMas = view.findViewById(R.id.btnCurarMas);
        btnCurarMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dinero >= precioCurar && (int) cambios[1] != 10) {
                    labelDinero.setTextColor(Color.RED);
                    setDinero(getDinero() - precioCurar);
                    labelCurar.setTextColor(Color.GREEN);
                    cambios[1] = (int) cambios[1] + 1;
                    labelCurar.setText(String.valueOf(precioCurar) + "$ - " + String.valueOf(cambios[1]) + "/10");
                    labelDinero.setText(String.valueOf(getDinero()));
                } else {
                    Toast.makeText(getContext(), R.string.toastPasta, Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnCurarMenos = view.findViewById(R.id.btnCurarMenos);
        btnCurarMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info != null) {
                    if (info[1] != cambios[1]) {
                        labelDinero.setTextColor(Color.RED);
                        setDinero(getDinero() + precioCurar);
                        labelCurar.setTextColor(Color.GREEN);
                        cambios[1] = (int) cambios[1] - 1;
                        labelCurar.setText(String.valueOf(precioCurar) + "$ - " + String.valueOf(cambios[1]) + "/10");
                        labelDinero.setText(String.valueOf(getDinero()));
                        if (info[1] == cambios[1]) {
                            labelCurar.setTextColor(Color.WHITE);
                            if ((int) cambios[4] == getDinero()) {
                                labelDinero.setTextColor(Color.WHITE);
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), R.string.toastQuitarTienda, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        Button btnDañoMas = view.findViewById(R.id.btnDañoMas);
        btnDañoMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dinero >= precioDaño && (int) cambios[2] != 5) {
                    labelDinero.setTextColor(Color.RED);
                    setDinero(getDinero() - precioDaño);
                    labelDaño.setTextColor(Color.GREEN);
                    cambios[2] = (int) cambios[2] + 1;
                    labelDaño.setText(String.valueOf(precioDaño) + "$ - " + String.valueOf(cambios[2]) + "/5");
                    labelDinero.setText(String.valueOf(getDinero()));
                } else {
                    Toast.makeText(getContext(), R.string.toastPasta, Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button btnDañoMenos = view.findViewById(R.id.btnDañoMenos);
        btnDañoMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info != null) {
                    if (info[2] != cambios[2]) {
                        labelDinero.setTextColor(Color.RED);
                        setDinero(getDinero() + precioDaño);
                        labelDaño.setTextColor(Color.GREEN);
                        cambios[2] = (int) cambios[2] - 1;
                        labelDaño.setText(String.valueOf(precioDaño) + "$ - " + String.valueOf(cambios[2]) + "/5");
                        labelDinero.setText(String.valueOf(getDinero()));
                        if (info[2] == cambios[2]) {
                            labelDaño.setTextColor(Color.WHITE);
                            if ((int) cambios[4] == getDinero()) {
                                labelDinero.setTextColor(Color.WHITE);
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), R.string.toastQuitarTienda, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }


    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}