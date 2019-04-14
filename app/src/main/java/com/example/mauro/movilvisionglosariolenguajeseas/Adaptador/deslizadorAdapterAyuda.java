package com.example.mauro.movilvisionglosariolenguajeseas.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.view.ClassifierActivity;

/**
 * Created by Mauro on 9/4/2019.
 */

public class deslizadorAdapterAyuda extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public deslizadorAdapterAyuda(Context context) {
        this.context = context;
    }

    //Array
    public int[] deslizar_imagen = {R.drawable.ayuda,R.drawable.ayudainicio, R.drawable.ayudalistadovoc,R.drawable.ayudamultimedia};

    public String[] deslizar_nombres = {"Ayuda Usuario","Vocabulario","","",};

    @Override
    public int getCount() {
        return deslizar_nombres.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.itemdeslizador_layout, container, false);

        ImageView deslizarImagen = (ImageView) view.findViewById(R.id.deslizarImagen);
        TextView txtLetra = (TextView) view.findViewById(R.id.txtletra);
        deslizarImagen.setImageResource(deslizar_imagen[position]);
        txtLetra.setText(deslizar_nombres[position]);
        Button btnpracticar = (Button) view.findViewById(R.id.btnPrac);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}