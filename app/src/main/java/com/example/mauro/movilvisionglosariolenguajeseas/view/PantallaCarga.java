package com.example.mauro.movilvisionglosariolenguajeseas.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mauro.movilvisionglosariolenguajeseas.Main2Activity;
import com.example.mauro.movilvisionglosariolenguajeseas.R;

public class PantallaCarga extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PantallaCarga.this,Main2Activity.class);
                new AsyncTask_load().execute();
                startActivity(intent);
                finish();
            }
        },1500);


    }

    public class AsyncTask_load extends AsyncTask<Void,Integer,Void> {
        int progress;
        @Override
        protected void onPreExecute() {
            progress=0;
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            while(progress<100)
            {
                progress++;
                publishProgress(progress);
                SystemClock.sleep(30);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
