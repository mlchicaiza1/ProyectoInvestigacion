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
            R.drawable.sena_ch,
            R.drawable.sena_d,
            R.drawable.sena_e,
            R.drawable.sena_f,
            R.drawable.sena_g,
            R.drawable.sena_h,
            R.drawable.sena_i,
            R.drawable.sena_j,
            R.drawable.sena_k,
            R.drawable.sena_l,
            R.drawable.sena_ll,
            R.drawable.sena_m,
            R.drawable.sena_n,
            R.drawable.sena_n1,
            R.drawable.sena_o,
            R.drawable.sena_p,
            R.drawable.sena_q,
            R.drawable.sena_r,
            R.drawable.sena_rr,
            R.drawable.sena_s,
            R.drawable.sena_t,
            R.drawable.sena_u,
            R.drawable.sena_v,
            R.drawable.sena_w,
            R.drawable.sena_x,
            R.drawable.sena_y,
            R.drawable.sena_z,
    };

    public String[] deslizar_nombres = {
            "Aa","Bb","Cc","Ch","Dd","Ee","Ff","Gg","Hh","Ii","J","Kk","Ll","LL","M","N","Ñ","Oo","P","Q","R","RR","S","T","U","Vv","Ww","Xx","Yy","Z"
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
        Button btnpracticar=(Button) view.findViewById(R.id.btnPrac) ;


        if(deslizar_nombres[position].length()>1 && deslizar_nombres[position]!="RR" && deslizar_nombres[position]!="LL" && deslizar_nombres[position]!="Ch"){
            btnpracticar.setVisibility(View.VISIBLE);
        }

        btnpracticar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ClassifierActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("dato",txtLetra.getText().toString());
                bundle.putInt("datoPos",position);
                intent.putExtras(bundle);

                //((GifDrawable)gifImagen1.getDrawable()).stop();
                context.startActivity(intent);
            }
        });

        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
