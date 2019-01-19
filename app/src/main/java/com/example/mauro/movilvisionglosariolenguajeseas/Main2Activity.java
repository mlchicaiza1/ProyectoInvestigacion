package com.example.mauro.movilvisionglosariolenguajeseas;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.mauro.movilvisionglosariolenguajeseas.Fragment.deslizadorABCFragment;
import com.example.mauro.movilvisionglosariolenguajeseas.Fragment.fragment_videos;
import com.example.mauro.movilvisionglosariolenguajeseas.Fragment.vocabularioFragment;
import com.example.mauro.movilvisionglosariolenguajeseas.Interfaces.IComunicaFragment;
import com.example.mauro.movilvisionglosariolenguajeseas.model.vocabularioClass;


public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        vocabularioFragment.OnFragmentInteractionListener,deslizadorABCFragment.OnFragmentInteractionListener,
        fragment_videos.OnFragmentInteractionListener, IComunicaFragment {
    ImageButton imbtnPlay;
    Fragment fragment1 = null;
    boolean fragmentSelect1 = false;
    FragmentManager fragmentManager1 = getSupportFragmentManager();
    fragment_videos fragmentVideos;
    ImageButton imbtnAbc,imbtnAyuda, imbtnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imbtnPlay = (ImageButton) findViewById(R.id.imbtnInicio);
        imbtnAbc = (ImageButton) findViewById(R.id.imbtnAbc);
        imbtnAyuda = (ImageButton) findViewById(R.id.imbtnAyuda);
        imbtnSalir = (ImageButton) findViewById(R.id.imbtnSalir);
        imbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1 = new vocabularioFragment();
                fragmentSelect1 = true;
                fragmentManager1.beginTransaction().replace(R.id.content_main, fragment1).commit();

            }
        });

        imbtnAbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1 = new deslizadorABCFragment();
                fragmentSelect1 = true;
                fragmentManager1.beginTransaction().replace(R.id.content_main, fragment1).commit();
            }
        });

        imbtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
       /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set the status bar background to transparent
        Window w =getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar vocabularioClass clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view vocabularioClass clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int id = item.getItemId();

        Fragment fragment=null;

        boolean fragmentSelect=false;


        switch (item.getItemId()) {


            case R.id.nav_Abecedario:
                fragment = new deslizadorABCFragment();
                fragmentSelect = true;
                break;
            case R.id.nav_Vocabulario:
                fragment = new vocabularioFragment();
                fragmentSelect = true;
                break;


            case R.id.nav_Ayuda:

                break;
        }

        if (fragment!= null){

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();



        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void enviarVocabulario(vocabularioClass vocabulario) {

        //fragmentVideos=(fragment_videos) this.getSupportFragmentManager().findFragmentById(R.id.)

        fragmentVideos=new fragment_videos();
        Bundle bundleEnvio=new Bundle();
        bundleEnvio.putSerializable("objeto",vocabulario);
        fragmentVideos.setArguments(bundleEnvio);

        //cargar el fragment en el activity
        getSupportFragmentManager().beginTransaction().
                replace(R.id.content_main,fragmentVideos).addToBackStack(null).commit();

    }
}
