package com.example.mauro.movilvisionglosariolenguajeseas.Fragment;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mauro.movilvisionglosariolenguajeseas.Adaptador.vocabularioAdapter;
import com.example.mauro.movilvisionglosariolenguajeseas.Interfaces.IComunicaFragment;
import com.example.mauro.movilvisionglosariolenguajeseas.R;
import com.example.mauro.movilvisionglosariolenguajeseas.model.AdminSQLiteOpenHelper;
import com.example.mauro.movilvisionglosariolenguajeseas.model.vocabularioClass;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link vocabularioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link vocabularioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class vocabularioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;
    ArrayList<vocabularioClass> itemsVocabulario;
    RecyclerView recyclerView;
    AdminSQLiteOpenHelper conn;
    EditText txtBuscarPalabra;
    Activity activity;
    IComunicaFragment interfaceComunicaFragment;


    public vocabularioFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static vocabularioFragment newInstance(String param1, String param2) {
        vocabularioFragment fragment = new vocabularioFragment();
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

        conn= new AdminSQLiteOpenHelper(getContext(),"vocabulario",null,1);
        itemsVocabulario=new ArrayList<>();

        //setup recyclerview with the vocabularioAdapter
        View vista=inflater.inflate(R.layout.fragment_list_cards, container, false);
       recyclerView = vista.findViewById(R.id.rv_list);

        //List<vocabularioClass> mlist =new ArrayList<>();
        //mlist.add(new vocabularioClass(R.drawable.p1,"Cities",R.drawable.pf,2500));
        //mlist.add(new vocabularioClass(R.drawable.p2,"Cities",R.drawable.pf1,2500));


        consultarListaVocabulario();
        final vocabularioAdapter vocabularioAdapter =new vocabularioAdapter(getContext(),itemsVocabulario);
        recyclerView.setAdapter(vocabularioAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        txtBuscarPalabra=(EditText) vista.findViewById(R.id.txtFiltrado);

        txtBuscarPalabra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                    vocabularioAdapter.getFilter().filter(s.toString());
                vocabularioAdapter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"Seleccion: "+
                                itemsVocabulario.get(recyclerView.
                                        getChildAdapterPosition(view)).getPalabra(),Toast.LENGTH_SHORT).show();

                        interfaceComunicaFragment.enviarVocabulario(itemsVocabulario.get(recyclerView.getChildAdapterPosition(view)));
                        onDestroy();
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        vocabularioAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Seleccion: "+
                        itemsVocabulario.get(recyclerView.
                                getChildAdapterPosition(view)).getPalabra(),Toast.LENGTH_SHORT).show();

                interfaceComunicaFragment.enviarVocabulario(itemsVocabulario.get(recyclerView.getChildAdapterPosition(view)));
                onDestroy();
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
            itemsVocabulario.add(item1);
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

        if (context instanceof Activity){
            this.activity=(Activity) context;
            interfaceComunicaFragment=(IComunicaFragment) this.activity;
        }

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
