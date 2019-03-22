package com.example.mauro.movilvisionglosariolenguajeseas.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.mauro.movilvisionglosariolenguajeseas.Main2Activity;
import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.model.AdminSQLiteOpenHelper;
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
    ImageView imgPalabraSena,imgSena;
    AdminSQLiteOpenHelper conn;

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

    String texto=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista =inflater.inflate(R.layout.fragment_fragment_videos, container, false);

        final android.support.v4.app.Fragment fragment1 = null;
        boolean fragmentSelect1 = false;
        FragmentManager fragmentManager1 = getFragmentManager();

        btnAyuda = (ImageButton) vista.findViewById(R.id.imbtnAyuda);
        btnPractica = (ImageButton) vista.findViewById(R.id.imbtnPracticar);
        btnInicio = (ImageButton)vista.findViewById(R.id.imbtnInicio);
        btnVolver=(ImageButton) vista.findViewById(R.id.imbtnVolver);
        txtpalabraSen = (TextView)vista.findViewById(R.id.txtPalabra);
        gifImagen1 = (GifImageView)vista.findViewById(R.id.gifImagen);
        imgPalabraSena = (ImageView)vista.findViewById(R.id.imgPalabrasena);
        imgSena = (ImageView)vista.findViewById(R.id.imgSena);

        Bundle objetoVocabulario=getArguments();
        vocabularioClass vocabulario=null;

        texto = getArguments().getString("palabra");

        if (objetoVocabulario !=null && texto==null){
            vocabulario=(vocabularioClass) objetoVocabulario.getSerializable("objeto");
            txtpalabraSen.setText(vocabulario.getPalabra());
            int imagenPalabra = getResources().getIdentifier(String.valueOf(vocabulario.getFondoletra()), "drawable",getContext().getPackageName() );
            imgPalabraSena.setImageResource(imagenPalabra);
            //Imagen de la seña
            int imagenSena = getResources().getIdentifier(vocabulario.getImaSena1(), "drawable",getContext().getPackageName() );
            imgSena.setImageResource(imagenSena);
            //gif de la seña
            int imageResource = getResources().getIdentifier(vocabulario.getVideo1(), "drawable",getContext().getPackageName() );
            gifImagen1.setImageResource(imageResource);

            if (vocabulario.getPracticaSena().equalsIgnoreCase("1")){
                btnPractica.setVisibility(View.VISIBLE);
            }
        }


        if (texto != null){
            txtpalabraSen.setText(texto);
            //String uri1 = "@drawable/"+texto+"fondo";
            int resId1 = getResources().getIdentifier(String.valueOf("@drawable/"+texto.toLowerCase()+"fondo"), "drawable", getContext().getPackageName());
            imgPalabraSena.setImageResource(resId1);

            int resId2 = getResources().getIdentifier(String.valueOf("@drawable/sena"+texto.toLowerCase()), "drawable", getContext().getPackageName());
            imgSena.setImageResource(resId2);

            int resId3 = getResources().getIdentifier(String.valueOf("@drawable/gif"+texto.toLowerCase()), "drawable", getContext().getPackageName());
            gifImagen1.setImageResource(resId3);

                btnPractica.setVisibility(View.VISIBLE);

        }


        /*vdVideoPractica.setMediaController(new MediaController((Activity)getContext()));
        vdVideoPractica.setVideoPath("@Drawable/gifdelante.gifgif");
        vdVideoPractica.requestFocus();
        vdVideoPractica.start();
*/

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(),Main2Activity.class);
                startActivity(intent);
                onDestroy();

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
                onDestroy();
                //((GifDrawable)gifImagen1.getDrawable()).stop();
                getActivity().startActivity(intent);
            }

        });
        return vista;

    }

    private void consultarListaVocabulario() {
        SQLiteDatabase db=conn.getReadableDatabase();
        vocabularioClass item1=null;

        Cursor cursor=db.rawQuery("SELECT * FROM Palabras ORDER BY palabra ASC" ,null);
        while (cursor.moveToNext()){
            item1=new vocabularioClass();


            item1.setLetra(cursor.getString(1));
            item1.setPalabra(cursor.getString(2));

            String uri = "@drawable/"+cursor.getString(3);
            int resId = getResources().getIdentifier(uri, "drawable", getContext().getPackageName());
            item1.setFondoletra(resId);

            String uri1 = "@drawable/"+cursor.getString(4);
            int resId1 = getResources().getIdentifier(uri1, "drawable", getContext().getPackageName());
            item1.setImagen1(resId1);

            item1.setImaSena1(cursor.getString(7));
            item1.setVideo1(cursor.getString(8));

            //item1.setImagen1(String.valueOf(R.drawable.familia1));
        }

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
