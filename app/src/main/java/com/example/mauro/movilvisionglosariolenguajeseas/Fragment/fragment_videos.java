package com.example.mauro.movilvisionglosariolenguajeseas.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mauro.movilvisionglosariolenguajeseas.Main2Activity;
import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.model.vocabularioClass;
import com.example.mauro.movilvisionglosariolenguajeseas.view.ClassifierActivity;

import java.io.IOException;
import java.io.InputStream;

import pl.droidsonroids.gif.GifAnimationMetaData;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_videos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_videos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_videos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ImageButton btnPractica,btnAyuda,btnInicio,btnVolver;
    VideoView vdVideoPractica;
    TextView txtpalabraSen;
    GifImageView gifImagen1;


    public fragment_videos() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_videos newInstance(String param1, String param2) {
        fragment_videos fragment = new fragment_videos();
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
        // Inflate the layout for this fragment


        View vista =inflater.inflate(R.layout.fragment_fragment_videos, container, false);


        final android.support.v4.app.Fragment fragment1 = null;
        boolean fragmentSelect1 = false;
        FragmentManager fragmentManager1 = getFragmentManager();

        //vdVideoPractica = (VideoView)vista.findViewById(R.id.vdVideo);
        btnAyuda = (ImageButton) vista.findViewById(R.id.imbtnAyuda);
        btnPractica = (ImageButton) vista.findViewById(R.id.imbtnPracticar);
        btnInicio = (ImageButton)vista.findViewById(R.id.imbtnInicio);
        btnVolver=(ImageButton) vista.findViewById(R.id.imbtnVolver);
        txtpalabraSen = (TextView)vista.findViewById(R.id.txtPalabra);
        gifImagen1 = (GifImageView)vista.findViewById(R.id.gifImagen);
        gifImagen1.setImageResource(R.drawable.color);
        Bundle objetoVocabulario=getArguments();
        vocabularioClass vocabulario=null;
        if (objetoVocabulario !=null){
            vocabulario=(vocabularioClass) objetoVocabulario.getSerializable("objeto");
            txtpalabraSen.setText(vocabulario.getPalabra());
        }


        /*vdVideoPractica.setMediaController(new MediaController((Activity)getContext()));
        vdVideoPractica.setVideoPath("@Drawable/delante.gif");
        vdVideoPractica.requestFocus();
        vdVideoPractica.start();
*/

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(),Main2Activity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vocabularioFragment vocabularioFrag=new vocabularioFragment();
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main,vocabularioFrag).addToBackStack(null).commit();
            }
        });


        btnPractica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ClassifierActivity.class);
                Bundle bundle =new Bundle();
                bundle.putString("dato",txtpalabraSen.getText().toString());
                intent.putExtras(bundle);
                ((GifDrawable)gifImagen1.getDrawable()).stop();
                getActivity().startActivity(intent);
            }
        });
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
