package com.example.laultimaesperanza.graficos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.laultimaesperanza.R;

public class DibujosImagenes {

    private Bitmap bitmap;

    public DibujosImagenes(Context context){
        BitmapFactory.Options bitmapOpciones =new BitmapFactory.Options();
        bitmapOpciones.inScaled=false;
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.plantilla, bitmapOpciones);
    }

    //interesante para la exposicion.

    public Dibujos[] getTodosDibujos(){
            Dibujos[] dibujitos=new Dibujos[13];
            for(int x=0,y=0; x<dibujitos.length;x++){

                y=((x%5)==0 && x>0)?y+1:y;

                dibujitos[x]=new Dibujos(this,new Rect(x*64,y*64,(x+1)*64,(y+1)*64));
            }
        return dibujitos;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
