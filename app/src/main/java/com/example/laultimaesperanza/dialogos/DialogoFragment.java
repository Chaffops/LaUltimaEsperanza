package com.example.laultimaesperanza.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.laultimaesperanza.MainActivity;
import com.example.laultimaesperanza.R;
import com.example.laultimaesperanza.database.Controlador;

public class DialogoFragment extends DialogFragment {

    Activity activity;

    ImageButton btnVolver;

    View v;

    EditText nombre;

    SeekBar volumen;

    Spinner idioma;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoDeAjustes();


    }

    private AlertDialog crearDialogoDeAjustes() {

        AlertDialog.Builder constructor = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.fragment_dialogo, null);
        constructor.setView(v);

        nombre = v.findViewById(R.id.textoNombre);
        volumen = v.findViewById(R.id.barraVolumen);
        idioma = v.findViewById(R.id.selectorIdioma);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.idiomas, android.R.layout.simple_spinner_item);
        idioma.setAdapter(adapter);

        Controlador control = new Controlador(getContext());
        Object[] z = control.recibirAjustes();

        if (z != null) {
            nombre.setText((String) z[0]);

            volumen.setProgress((int) z[1]);

            if (z[2].equals("Español")){idioma.setSelection(0);}
            else if (z[2].equals("Ingles")){idioma.setSelection(1);}
        }
        btnVolver = v.findViewById(R.id.botonVolverAjustes);

        eventoVolver();

        //posicionar arriba el DialogFragment
        AlertDialog dialogo = constructor.create();
        Window window = dialogo.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.TOP;
        window.setAttributes(layoutParams);

        return dialogo;
    }

    private void eventoVolver() {
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
    }

    public Object[] getInfo() {
        Object[] x = new Object[3];
        if (!nombre.getText().toString().equals("")) {

            x[0] = nombre.getText().toString();

            x[1] = volumen.getProgress();

            x[2] = idioma.getSelectedItem();

            return x;
        } else {
            return null;
        }

    }
}