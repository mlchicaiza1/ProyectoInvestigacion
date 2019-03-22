package com.example.mauro.movilvisionglosariolenguajeseas.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mauro.movilvisionglosariolenguajeseas.Adaptador.deslizadorAdapter;
import com.example.mauro.movilvisionglosariolenguajeseas.Interfaces.IComunicaFragment;
import com.example.mauro.movilvisionglosariolenguajeseas.Main2Activity;
import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.view.ClassifierActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link deslizadorABCFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link deslizadorABCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class deslizadorABCFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Activity activity;
    IComunicaFragment interfaceComunicaFragment;
    private OnFragmentInteractionListener mListener;

    private ViewPager vpDeslizar;
    private LinearLayout lyPuntos;
    private deslizadorAdapter deslizadorAdapte;
    private TextView[] puntos;
    private Button btnAtras, btnAdelante,btnpracticar;
    private int mCurrentPage;


    public deslizadorABCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment deslizadorABCFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static deslizadorABCFragment newInstance(String param1, String param2) {
        deslizadorABCFragment fragment = new deslizadorABCFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    int posicion=30;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_deslizador_abc, container, false);

        vpDeslizar = (ViewPager) vista.findViewById(R.id.deslizarPagina);
        lyPuntos = (LinearLayout) vista.findViewById(R.id.puntos);
        btnAtras = (Button) vista.findViewById(R.id.btnAtras);
        btnAdelante = (Button) vista.findViewById(R.id.btnSiguiente);
        btnpracticar =(Button) vista.findViewById(R.id.btnPrac);
        deslizadorAdapte = new deslizadorAdapter(getContext());
        vpDeslizar.setAdapter(deslizadorAdapte);

        anadirIndicadorPnts(0);

       //
        //
        vpDeslizar.addOnPageChangeListener(viewListener);

        try {
            String texto=getArguments().getString("palabra");
            posicion=getArguments().getInt("datosPos");

            if (posicion!=30){
                vpDeslizar.setCurrentItem(posicion);
            }


            Toast.makeText(getContext(),"Seleccion: "+texto+posicion,Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }




        btnAdelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current=getItem(1);
                vpDeslizar.setCurrentItem(mCurrentPage + 1 );

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current=getItem2(0);
                vpDeslizar.setCurrentItem(mCurrentPage - 1 );

            }
        });




        return vista;
    }




    public void anadirIndicadorPnts(int position){

        puntos = new TextView[36];
        lyPuntos.removeAllViews();

        for(int i = 0; i < puntos.length;i++ ){
            puntos[i] = new TextView(getContext());
            puntos[i].setText(Html.fromHtml("&#8226;"));
            puntos[i].setTextSize(35);
            puntos[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            lyPuntos.addView(puntos[i]);

        }

        if (puntos.length > 0){
            puntos[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            anadirIndicadorPnts(position);
            mCurrentPage = position;

            if (position == 0){
                btnAdelante.setEnabled(true);
                btnAtras.setEnabled(false);
                btnAtras.setVisibility(View.INVISIBLE);

                btnAdelante.setText("Siguiente");
                btnAtras.setText("");
            } else if(position == (puntos.length)){
                btnAdelante.setEnabled(false);
                btnAtras.setEnabled(true);
                btnAtras.setVisibility(View.VISIBLE);

                btnAdelante.setText("Siguiente");
                btnAtras.setText("Atrás");
            } else{

                btnAdelante.setEnabled(true);
                btnAtras.setEnabled(true);
                btnAtras.setVisibility(View.VISIBLE);

                btnAdelante.setText("Siguiente");
                btnAtras.setText("Atrás");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void pantallaInicio(){
        startActivity(new Intent(getContext(), Main2Activity.class));

    }
    private int getItem(int i) {
        return vpDeslizar.getCurrentItem() + i;
    }

    private int getItem2(int i) {
        return vpDeslizar.getCurrentItem() - i;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            this.activity=(Activity) context;
            interfaceComunicaFragment=(IComunicaFragment) this.activity;
        }

        if (context instanceof deslizadorABCFragment.OnFragmentInteractionListener) {
            mListener = (deslizadorABCFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
