package com.example.mauro.movilvisionglosariolenguajeseas.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.model.BoxPosition;
import com.example.mauro.movilvisionglosariolenguajeseas.model.Recognition;


import java.util.LinkedList;
import java.util.List;

/**
 * A simple View providing a render callback to other classes.
 */
public class OverlayView extends View {
    private final List<DrawCallback> callbacks = new LinkedList<DrawCallback>();

    private List<Classifier.Recognition> results;
    public String palabra;
    public Drawable imagen;
    public Drawable imagen1;
    public OverlayView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        imagen=context.getDrawable(R.drawable.like);
        imagen1=context.getDrawable(R.drawable.error);
    }

    /**
     * Interface defining the callback for client classes.
     */
    public interface DrawCallback {
        public void drawCallback(final Canvas canvas);
    }


    public void addCallback(final DrawCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public synchronized void draw(final Canvas canvas) {
        for (final DrawCallback callback : callbacks) {
            callback.drawCallback(canvas);
        }


        int ancho=imagen.getIntrinsicWidth();
        int alto=imagen.getIntrinsicHeight();


        imagen.setBounds(200,200,ancho+200,alto+200);
        imagen1.setBounds(200,200,ancho+200,alto+200);

        if (results!=null){
            for (int i=0; i<results.size(); i++){
                String title=results.get(i).getTitle();


                String[] parts = title.split(" ");
                String part1 = parts[0];

               if (parts.length==2){

               }else {
                   if (part1.equalsIgnoreCase(palabra)){
                       imagen.draw(canvas);
                   }else{
                       imagen1.draw(canvas);
                   }
               }


            }
        }



    }

    public void setResults(final List<Classifier.Recognition> results){
        this.results=results;
        postInvalidate();
    }
    public void getResults(String palabra){
        this.palabra=palabra;
        postInvalidate();

    }
}