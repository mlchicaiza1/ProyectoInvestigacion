package com.example.mauro.movilvisionglosariolenguajeseas.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.Size;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mauro.movilvisionglosariolenguajeseas.Fragment.vocabularioFragment;
import com.example.mauro.movilvisionglosariolenguajeseas.Main2Activity;
import com.example.mauro.movilvisionglosariolenguajeseas.TensorFlowImageClassifier;
import com.example.mauro.movilvisionglosariolenguajeseas.view.OverlayView.DrawCallback;
import com.example.mauro.movilvisionglosariolenguajeseas.model.Recognition;
import com.example.mauro.movilvisionglosariolenguajeseas.env.ImageUtils;
import com.example.mauro.movilvisionglosariolenguajeseas.env.BorderedText;

//import org.tensorflow.yolo.R;

import com.example.mauro.movilvisionglosariolenguajeseas.R;

import java.util.List;
import java.util.Vector;


import com.example.mauro.movilvisionglosariolenguajeseas.env.Logger;


public class ClassifierActivity extends CameraActivity implements OnImageAvailableListener {
    private static final Logger LOGGER = new Logger();

    private static final int INPUT_SIZE = 224;
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128.0f;
    //private static final String INPUT_NAME = "input";
    private static final String INPUT_NAME = "Placeholder";
    private static final String OUTPUT_NAME = "final_result";

    private static final String MODEL_FILE = "file:///android_asset/graphVocabParte1.pb";
    private static final String LABEL_FILE = "file:///android_asset/labelsVocabParte1.txt";

    private static final String MODEL_FILE1 = "file:///android_asset/graphVocabParte2.pb";
    private static final String LABEL_FILE1 = "file:///android_asset/labelsVocabParte2.txt";

    private static final String MODEL_FILEParte3 = "file:///android_asset/graphVocabParte3.pb";
    private static final String LABEL_FILEParte3 = "file:///android_asset/labelsVocabParte3.txt";


    //Abecedario
    private static final String MODEL_FILE2 = "file:///android_asset/graphAbcParte1.pb";
    private static final String LABEL_FILE2 = "file:///android_asset/labelsAbcParte1.txt";

    private static final String MODEL_FILE3 = "file:///android_asset/graphAbcParte2.pb";
    private static final String LABEL_FILE3 = "file:///android_asset/labelsAbcParte2.txt";
    //private static final String LABEL_FILE2 = "file:///android_asset/labels.txt";
    private static final boolean SAVE_PREVIEW_BITMAP = false;

    private static final boolean MAINTAIN_ASPECT = true;

    private static final Size DESIRED_PREVIEW_SIZE = new Size(640, 480);

    private Classifier classifier;

    private Integer sensorOrientation;

    private int previewWidth = 0;
    private int previewHeight = 0;

    private byte[][] yuvBytes;
    private int[] rgbBytes = null;
    private Bitmap rgbFrameBitmap = null;
    private Bitmap croppedBitmap = null;

    private Bitmap cropCopyBitmap;

    private boolean computing = false;

    private Matrix frameToCropTransform;
    private Matrix cropToFrameTransform;

    private ResultsView resultsView;

    private BorderedText borderedText;

    private long lastProcessingTimeMs;

    private Bundle bundle;
    private OverlayView overlayView1;
    private String palabra;
    private String letra;
    private Intent intent;

    private Chronometer chronometer;
    private TextView txtTempor;
    private int count=300;
    private int posicion=35;
    private LottieAnimationView animationView;
    @Override
    //protected int getLayoutId() {
       // return R.layout.camera_connection_fragment;
    //}
    protected int getLayoutId() {
        return R.layout.fragment_camera_connection;
    }
    @Override
    protected Size getDesiredPreviewFrameSize() {
        return DESIRED_PREVIEW_SIZE;
    }

    private static final float TEXT_SIZE_DIP = 10;

    @Override
    public void onPreviewSizeChosen(final Size size, final int rotation) {

        //obtner tamaño del borderedTex para la visualizacion de los datos del reconocimento
        final float textSizePx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());

        borderedText = new BorderedText(textSizePx);
        borderedText.setTypeface(Typeface.MONOSPACE);



        resultsView = (ResultsView) findViewById(R.id.results);
        overlayView1=(OverlayView)  findViewById(R.id.debug_overlay);
        previewWidth = size.getWidth();
        previewHeight = size.getHeight();


        String [] palabrasModel={"abajo",
                "color",
                "delante",
                "lunes",
                "noche",
                "rojo",
                "fondos"

        };

        String [] palabrasModelparte3={"familia",
                "casa",
                "sentarse",
                "mama",
                "niño",
                "pararse",
                "pequeño",
                "fondos"

        };

        bundle=this.getIntent().getExtras();
        boolean encontrar=false;
        boolean encontrarParte3=false;

        //Reconocimiento Vocabulario
        if (bundle !=null && bundle.getString("dato").length()>3){
            //overlayView.getResults(bundle.getString("dato"));
            Toast.makeText(this,"Seleccion: "+ bundle.getString("dato"),Toast.LENGTH_SHORT).show();
            overlayView1.getResults(bundle.getString("dato"));
            palabra=bundle.getString("dato");

            for (String elment:palabrasModel){
                if (elment.equalsIgnoreCase(palabra)){
                    encontrar=true;
                    break;
                }
            }

            for (String elment:palabrasModelparte3){
                if (elment.equalsIgnoreCase(palabra)){
                    encontrarParte3=true;
                    break;
                }
            }
            if (encontrar){
                classifier = TensorFlowImageClassifier.create(
                        getAssets(),
                        MODEL_FILE1,
                        LABEL_FILE1,
                        INPUT_SIZE,
                        IMAGE_MEAN,
                        IMAGE_STD,
                        INPUT_NAME,
                        OUTPUT_NAME);
            }else{
                classifier = TensorFlowImageClassifier.create(
                        getAssets(),
                        MODEL_FILE,
                        LABEL_FILE,
                        INPUT_SIZE,
                        IMAGE_MEAN,
                        IMAGE_STD,
                        INPUT_NAME,
                        OUTPUT_NAME);
            }

            //Modelo 3 para el reconocimiento
            if (encontrarParte3){
                palabra=palabra.replace("ñ","n");
                classifier = TensorFlowImageClassifier.create(
                        getAssets(),
                        MODEL_FILEParte3,
                        LABEL_FILEParte3,
                        INPUT_SIZE,
                        IMAGE_MEAN,
                        IMAGE_STD,
                        INPUT_NAME,
                        OUTPUT_NAME);
            }
        }




        String [] abc={"aa","bb","cc","dd","ff","gg","vv","yy","fondos"};

        boolean encontrarAbc=false;        //Reconocimiento Abecedario
        if (bundle !=null && bundle.getString("dato").length()<3){
            //overlayView.getResults(bundle.getString("dato"));
            posicion=bundle.getInt("datoPos");
            Toast.makeText(this,"Seleccion: "+ bundle.getString("dato") + posicion,Toast.LENGTH_SHORT).show();
            overlayView1.getResults(bundle.getString("dato"));
            palabra=bundle.getString("dato");

            for (String elment:abc) {
                if (elment.equalsIgnoreCase(palabra)) {
                    encontrarAbc = true;
                    break;
                }
            }

                if (encontrarAbc){

                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_FILE2,
                            LABEL_FILE2,
                            INPUT_SIZE,
                            IMAGE_MEAN,
                            IMAGE_STD,
                            INPUT_NAME,
                            OUTPUT_NAME);
                }else{

                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_FILE3,
                            LABEL_FILE3,
                            INPUT_SIZE,
                            IMAGE_MEAN,
                            IMAGE_STD,
                            INPUT_NAME,
                            OUTPUT_NAME);

                }




        }

        intent= new Intent (ClassifierActivity.this, Main2Activity.class);
        chronometer=(Chronometer) findViewById(R.id.txtCrono);
        chronometer.setBase(SystemClock.elapsedRealtime());
        txtTempor=(TextView)findViewById(R.id.txtTemp);
        animationView=(LottieAnimationView) findViewById(R.id.animation1);


        final Display display = getWindowManager().getDefaultDisplay();
        final int screenOrientation = display.getRotation();

        LOGGER.i("Sensor orientation: %d, Screen orientation: %d", rotation, screenOrientation);
        sensorOrientation = rotation + screenOrientation;

        LOGGER.i("Initializing at size %dx%d", previewWidth, previewHeight);
        rgbBytes = new int[previewWidth * previewHeight];
        rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Config.ARGB_8888);
        croppedBitmap = Bitmap.createBitmap(INPUT_SIZE, INPUT_SIZE, Config.ARGB_8888);

        frameToCropTransform =
                ImageUtils.getTransformationMatrix(
                        previewWidth, previewHeight,
                        INPUT_SIZE, INPUT_SIZE,
                        sensorOrientation, MAINTAIN_ASPECT);

        cropToFrameTransform = new Matrix();
        frameToCropTransform.invert(cropToFrameTransform);

        yuvBytes = new byte[3][];

        addCallback(
                new DrawCallback() {
                    @Override
                    public void drawCallback(final Canvas canvas) {
                        renderDebug(canvas);
                    }
                });

    }

    @Override
    public void onImageAvailable(final ImageReader reader) {
        Image image = null;

        try {
            image = reader.acquireLatestImage();

            if (image == null) {return;}

            if (computing) {
                image.close();
                return;
            }
            computing = true;

            Trace.beginSection("imageAvailable");

            final Image.Plane[] planes = image.getPlanes();
            fillBytes(planes, yuvBytes);

            final int yRowStride = planes[0].getRowStride();
            final int uvRowStride = planes[1].getRowStride();
            final int uvPixelStride = planes[1].getPixelStride();
            ImageUtils.convertYUV420ToARGB8888(
                    yuvBytes[0],
                    yuvBytes[1],
                    yuvBytes[2],
                    previewWidth,
                    previewHeight,
                    yRowStride,
                    uvRowStride,
                    uvPixelStride,
                    rgbBytes);

            image.close();
        } catch (final Exception e) {
            if (image != null) {
                image.close();
            }
            LOGGER.e(e, "Exception!");
            Trace.endSection();
            return;
        }

        rgbFrameBitmap.setPixels(rgbBytes, 0, previewWidth, 0, 0, previewWidth, previewHeight);
        final Canvas canvas = new Canvas(croppedBitmap);
        canvas.drawBitmap(rgbFrameBitmap, frameToCropTransform, null);

        // For examining the actual TF input.
        if (SAVE_PREVIEW_BITMAP) {
            ImageUtils.saveBitmap(croppedBitmap);
        }

        runInBackground(
                new Runnable() {
                    @Override
                    public void run() {
                        final long startTime = SystemClock.uptimeMillis();
                        final List<Classifier.Recognition> results = classifier.recognizeImage(croppedBitmap);
                        lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
                        cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
                        resultsView.setResults(results);
                        overlayView1.setResults(results);
                        chronometer.post(new Runnable() {
                            @Override
                            public void run() {
                                chronometer.start();

                                if (count<300){
                                    txtTempor.setVisibility(View.VISIBLE);
                                    animationView.loop(false);

                                }
                                new CountDownTimer(300_000, 1_000) {
                                    @Override
                                    public void onTick(long l) {
                                        txtTempor.setText(String.valueOf(count));

                                        if (count<0){
                                            count=0;
                                        }
                                        if (count>0){
                                            count--;
                                        }

                                    }


                                    @Override
                                    public void onFinish() {


                                    }
                                }.start();

                            }
                        });


                        String valor= (String) txtTempor.getText();

                        if (txtTempor.getVisibility()== View.VISIBLE){

                            if (results!=null){

                                for (int i=0; i<results.size(); i++){
                                    String palabraRecog=results.get(i).getTitle();
                                    float con=results.get(i).getConfidence();

                                    if (Integer.parseInt(valor)< 1 ){
                                       palabra=palabra.replace("n","ñ");
                                        bundle.putString("textFromActivityA",palabra+"_"+"false"+"_" +con);
                                        if (posicion != 35){
                                            bundle.putInt("datoPos",posicion);
                                        }
                                        intent.putExtras(bundle);

                                        startActivity(intent);


                                    }
                                    String dat="fondos";
                                    if (palabraRecog.equalsIgnoreCase(dat) ){


                                    }else {
                                        if(palabraRecog.equalsIgnoreCase(palabra) && con >0.222){

                                            palabra=palabra.replace("n","ñ");
                                            bundle.putString("textFromActivityA",palabra+"_"+"true"+"_" +con);
                                            if (posicion != 35){
                                                bundle.putInt("datoPos",posicion);
                                            }
                                            intent.putExtras(bundle);

                                            startActivity(intent);


                                        }else {

                                            //if (Integer.parseInt(valor)<28){

                                                if (con > 0.62){
                                                    bundle.putString("textFromActivityA",palabraRecog+"_"+"false"+"_" +con);

                                                    if (posicion != 35){
                                                        bundle.putInt("datoPos",posicion);
                                                    }
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                }

                                            //}

                                        }

                                    }


                                }

                            }
                        }

                        requestRender();

                        computing = false;
                    }
                });

        Trace.endSection();
    }



    @Override
    public void onSetDebug(boolean debug) {
        classifier.enableStatLogging(debug);
    }

    private void renderDebug(final Canvas canvas) {
        if (!isDebug()) {
            return;
        }
        final Bitmap copy = cropCopyBitmap;
        if (copy != null) {
            final Matrix matrix = new Matrix();
            final float scaleFactor = 2;
            matrix.postScale(scaleFactor, scaleFactor);
            matrix.postTranslate(
                    canvas.getWidth() - copy.getWidth() * scaleFactor,
                    canvas.getHeight() - copy.getHeight() * scaleFactor);
            canvas.drawBitmap(copy, matrix, new Paint());

            final Vector<String> lines = new Vector<String>();
            if (classifier != null) {
                String statString = classifier.getStatString();
                String[] statLines = statString.split("\n");
                for (String line : statLines) {
                    lines.add(line);
                }
            }

            lines.add("Frame: " + previewWidth + "x" + previewHeight);
            lines.add("Crop: " + copy.getWidth() + "x" + copy.getHeight());
            lines.add("View: " + canvas.getWidth() + "x" + canvas.getHeight());
            lines.add("Rotation: " + sensorOrientation);
            lines.add("Inference time: " + lastProcessingTimeMs + "ms");

            borderedText.drawLines(canvas, 10, canvas.getHeight() - 10, lines);
        }
    }


}
