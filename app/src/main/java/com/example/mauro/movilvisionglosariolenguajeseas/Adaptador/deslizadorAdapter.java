package com.example.mauro.movilvisionglosariolenguajeseas.Adaptador;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mauro.movilvisionglosariolenguajeseas.R;

/**
 * Created by Pao on 25/10/2018.
 */

public class deslizadorAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public deslizadorAdapter(Context context){
        this.context = context;
    }
    //Array
    public int[] deslizar_imagen ={
            R.drawable.sena_a,
            R.drawable.sena_b,
            R.drawable.sena_c,
            R.drawable.sena_d,
            R.drawable.sena_e,
            R.drawable.sena_f,
            R.drawable.sena_g,
            R.drawable.sena_h,
            R.drawable.sena_i,
            R.drawable.sena_j,
            R.drawable.sena_k,
            R.drawable.sena_l
    };

    public String[] deslizar_nombres = {
            "A","B","C","D","E","F","G","H","I","J","K","L"
    };
    @Override
    public int getCount() {
        return deslizar_nombres.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.itemdeslizador_layout, container,false);

        ImageView deslizarImagen = (ImageView) view.findViewById(R.id.deslizarImagen);
        TextView txtLetra = (TextView) view.findViewById(R.id.txtletra);
        deslizarImagen.setImageResource(deslizar_imagen[position]);
        txtLetra.setText(deslizar_nombres[position]);

        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
