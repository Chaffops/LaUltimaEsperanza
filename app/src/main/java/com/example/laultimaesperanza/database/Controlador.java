package com.example.laultimaesperanza.database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.laultimaesperanza.MainActivity;

public class Controlador extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NOMBRE_AJUSTES = "juego.db";
    public static final String TABLA1 = "ajustes";
    public static final String TABLA2 = "estadisticas";


    Context context;

    public Controlador(@Nullable Context context) {
        super(context, NOMBRE_AJUSTES, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA1 + "(" +
                "nombre TEXT NOT NULL," +
                "volumen INTEGER NOT NULL," +
                "idioma TEXT NOT NULL," +
                "tema INTEGER NOT NULL)"
        );
        /*sqLiteDatabase.execSQL("CREATE TABLE " + TABLA2 + "(" +
                "nombre TEXT," +
                "volumen INTEGER NOT NULL," +
                "idioma TEXT NOT NULL," +
                "tema INTEGER NOT NULL)"
        );*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA1);
        //sqLiteDatabase.execSQL("DROP TABLE " + TABLA2 );
        onCreate(sqLiteDatabase);

    }

    public long insertarAjustes(String nombre, int volumen, String idioma, int tema) {
        long num = 0;
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues valores = new ContentValues();

            valores.put("nombre", nombre);
            valores.put("volumen", volumen);
            valores.put("idioma", idioma);
            valores.put("tema", tema);

            num = db.insert(TABLA1, null, valores);
        } catch (Exception ex) {
            Toast.makeText(context,"El registro no ha funcionado",Toast.LENGTH_LONG);
        }
        return num;
    }

}
