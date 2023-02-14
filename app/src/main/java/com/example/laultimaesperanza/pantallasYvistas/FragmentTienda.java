package com.example.laultimaesperanza.pantallasYvistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laultimaesperanza.R;
import com.example.laultimaesperanza.database.Controlador;

public class FragmentTienda extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

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

        TextView labelDinero = getView().findViewById(R.id.labelLoqueTengo);
        TextView labelVelocidad = getView().findViewById(R.id.labelVelocidad);
        TextView labelCurar = getView().findViewById(R.id.labelCurar);
        TextView labelDaño = getView().findViewById(R.id.labelDaño);

        Controlador control = new Controlador(getContext());
        Object[] info = control.recibirInfoJuego();

        if (info != null) {
            labelDinero.setText((CharSequence) info[4]);
            labelVelocidad.setText((CharSequence) info[0]);
            labelCurar.setText((CharSequence) info[1]);
            labelDaño.setText((CharSequence) info[2]);
        } else {
            labelDinero.setText("0");
            labelVelocidad.setText("0");
            labelCurar.setText("0");
            labelDaño.setText("0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tienda, container, false);
    }
}