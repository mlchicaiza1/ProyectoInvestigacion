package com.example.mauro.movilvisionglosariolenguajeseas.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mauro.movilvisionglosariolenguajeseas.Adaptador.deslizadorAdapter;
import com.example.mauro.movilvisionglosariolenguajeseas.Adaptador.deslizadorAdapterAyuda;
import com.example.mauro.movilvisionglosariolenguajeseas.Interfaces.IComunicaFragment;
import com.example.mauro.movilvisionglosariolenguajeseas.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ayudaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ayudaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ayudaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Activity activity;
    IComunicaFragment interfaceComunicaFragment;


    private ViewPager vpDeslizar;
    private LinearLayout lyPuntos;
    private deslizadorAdapterAyuda deslizadorAdapteAyuda;
    private TextView[] puntos;
    private Button btnAtras, btnAdelante,btnpracticar;
    private int mCurrentPage;

    private OnFragmentInteractionListener mListener;

    public ayudaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ayudaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ayudaFragment newInstance(String param1, String param2) {
        ayudaFragment fragment = new ayudaFragment();
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
        deslizadorAdapteAyuda = new deslizadorAdapterAyuda(getContext());
        vpDeslizar.setAdapter(deslizadorAdapteAyuda);

        anadirIndicadorPnts(0);

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

    private int getItem(int i) {
        return vpDeslizar.getCurrentItem() + i;
    }

    private int getItem2(int i) {
        return vpDeslizar.getCurrentItem() - i;
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
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
