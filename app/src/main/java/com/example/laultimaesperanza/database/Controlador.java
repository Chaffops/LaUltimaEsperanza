package com.example.laultimaesperanza.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class Controlador extends SQLiteOpenHelper {

    private static final int VERSION = 3;
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
                "idioma TEXT NOT NULL)"
        );
        /*sqLiteDatabase.execSQL("CREATE TABLE " + TABLA2 + "(" +
                "velocidad INTEGER NOT NULL," +
                "vida INTEGER NOT NULL," +
                "ronda INTEGER NOT NULL," +
                "dinero INTEGER NOT NULL," +
                "puntuacion INTEGER NOT NULL)"
        );*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA1);
        //sqLiteDatabase.execSQL("DROP TABLE " + TABLA2 );
        onCreate(sqLiteDatabase);

    }

    public long insertarAjustes(String nombre, int volumen, String idioma) {
        long num = 0;
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("delete from "+TABLA1);
            ContentValues valores = new ContentValues();

            valores.put("nombre", nombre);
            valores.put("volumen", volumen);
            valores.put("idioma", idioma);
            num = db.insert(TABLA1, null, valores);

        } catch (Exception ex) {
            Toast.makeText(context,"El registro no ha funcionado",Toast.LENGTH_LONG).show();
        }
        return num;
    }

    public Object[] recibirAjustes(){
        Object[] x=new Object[3];

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor= db.rawQuery("select * from ajustes",null);
        if (cursor.moveToFirst()){
            x[0]=cursor.getString(0);
            x[1]=cursor.getInt(1);
            x[2]=cursor.getString(2);
        }else{
            return null;
        }


        return x;
    }

}
