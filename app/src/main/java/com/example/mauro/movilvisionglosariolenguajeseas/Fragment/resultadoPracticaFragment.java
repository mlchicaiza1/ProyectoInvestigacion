package com.example.mauro.movilvisionglosariolenguajeseas.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.view.PantallaCarga;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link resultadoPracticaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link resultadoPracticaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class resultadoPracticaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageView imgRespuesta,imgRespuestaSena;
    TextView txtRespuesta;
    Button btnVolverSen;


    public resultadoPracticaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment resultadoPracticaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static resultadoPracticaFragment newInstance(String param1, String param2) {
        resultadoPracticaFragment fragment = new resultadoPracticaFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista =inflater.inflate(R.layout.fragment_resultado_practica, container, false);

        imgRespuesta=(ImageView) vista.findViewById(R.id.imageView2);
        txtRespuesta=(TextView) vista.findViewById(R.id.txtResp);
        btnVolverSen=(Button)  vista.findViewById(R.id.btnVolverSen);
        imgRespuestaSena = (ImageView) vista.findViewById(R.id.imgResuladoSena);
        String texto = getArguments().getString("textFromActivityB");
        int posicion=getArguments().getInt("datosPosicion");

        String[] parts = texto.split("_");
        String part1 = parts[0];
        String part2=parts[1];
        String part3=parts[2];



        if(part2.equalsIgnoreCase("false") ){

            imgRespuesta.setImageResource(R.drawable.error);

            txtRespuesta.setText(part1+part3);

            int resId1 = getResources().getIdentifier(String.valueOf("@drawable/sena"+part1.toLowerCase()), "drawable", getContext().getPackageName());
            imgRespuestaSena.setImageResource(resId1);


        }else {
            imgRespuesta.setImageResource(R.drawable.like);
            txtRespuesta.setText(part1);

            int resId1 = getResources().getIdentifier(String.valueOf("@drawable/sena"+part1.toLowerCase()), "drawable", getContext().getPackageName());
            imgRespuestaSena.setImageResource(resId1);


        }

        btnVolverSen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(part1.length()<3){
                    Bundle args = new Bundle();
                    // Colocamos el String
                    args.putString("palabra", part1);
                    args.putInt("datosPos",posicion);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    deslizadorABCFragment desFrag=new deslizadorABCFragment();
                    desFrag.setArguments(args);
                    transaction.replace(R.id.content_main,desFrag).commit();
                    onDestroy();
                }

                if(part1.length()>3){
                    Bundle args = new Bundle();
                    // Colocamos el String
                    args.putString("palabra", part1);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    fragment_videos videosfrag=new fragment_videos();
                    videosfrag.setArguments(args);
                    transaction.replace(R.id.content_main, videosfrag).commit();
                    onDestroy();
                }


            }
        });




      //  new Handler().postDelayed(new Runnable() {
        //    @Override
          //  public void run() {

            //}
        //},3000);


        return vista;
    }

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
