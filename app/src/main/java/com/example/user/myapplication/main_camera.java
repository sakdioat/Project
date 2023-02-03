package com.example.user.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Gesture;
import com.otaliastudios.cameraview.GestureAction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class main_camera extends Activity {
    public static final int REQUEST_GALLERY = 1;
    public static final String EXTRA_MESSAGE = "com.mindorks.com.example.user.myapplication.MESSAGE";
    public static final String EXTRA_IMAGE = "com.mindorks.com.example.user.myapplication.IMAGE";
    public static final String CONFIDENT ="com.mindorks.com.example.user.myapplication.CONFIDENT" ;

    //data get to resualt
    public static final String THAINAME ="com.mindorks.com.example.user.myapplication.THAINAME" ;
    public static final String NAIL_A ="com.mindorks.com.example.user.myapplication.GROW_A" ;
    public static final String NAIL_B ="com.mindorks.com.example.user.myapplication.GROW_B" ;
    public static final String CARE ="com.mindorks.com.example.user.myapplication.CARE" ;
    public static final String SAVE ="com.mindorks.com.example.user.myapplication.SAVE" ;


    private static final int INPUT_SIZE = 224;
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128.0f;
    private static final String INPUT_NAME = "input";
    private static final String OUTPUT_NAME = "final_result";
    private static final String MODEL_FILE = "file:///android_asset/retrained_graph.pb";
    private static final String LABEL_FILE = "file:///android_asset/retrained_labels.txt";

    private Classifier classifier;
    private Executor executor = Executors.newSingleThreadExecutor();
    private ImageButton imageButton;
    private Button searchPhoto;
    private CameraView cameraView;
    private ImageButton btnToggleCamera;

    //get DataGrow
    private String thainame;
    private String nail_a;
    private String nail_b;
    private String care;
    private String save;

    //all function setContent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);

        //camera preview
        cameraView=findViewById(R.id.cameraView);
        cameraView.mapGesture(Gesture.TAP, GestureAction.FOCUS_WITH_MARKER);
        //button functionCamera
        imageButton = findViewById(R.id.ImageButton);
//        searchPhoto = findViewById(R.id.searchPhoto);
        btnToggleCamera = findViewById(R.id.btnToggleCamera);



        //sent to resualt
        final Intent intent = new Intent(this,result_maincamera.class);


        //do function
        //   //
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] picture) {
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inPreferredConfig=Bitmap.Config.RGB_565;
                Bitmap bitmap=BitmapFactory.decodeByteArray(picture,0,picture.length,options);
                bitmap = rotateImage(bitmap, 90);  //setimg90
                Bitmap bitmapPass=bitmap;//setimg90
                String bitmapPath=saveToInternalStorage(bitmapPass);//setimg90
                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false); //INPUT_SIZE = 299



                //recognizeImage get data classifie
                final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap); //Classifier
                Float getconfident=results.get(0).getConfidence();
                Float setconfident=getconfident*100f;
                String confidentce=String.format("%.2f%%",setconfident);
                String sum=confidentce;

                String Id=results.get(0).getId();
                if(Id!=null){
                    if(Objects.equals(Id, "0")){
                        thainame=getString(R.string.namecat1);
                        nail_a=getString(R.string.cat_1);
//                            nail_b=getString(R.string.nails_b1);

                    }
                    else if(Objects.equals(Id, "1")){
                        thainame=getString(R.string.namecat2);
                        nail_a=getString(R.string.cat_2);
//                            nail_b=getString(R.string.nails_b2);
                    }

                }

                if(results.size()>0){
                    if(results.get(0).getConfidence()>0.65f){

                        intent.putExtra(CONFIDENT,sum.replace("[","").replace("]",""));
                        //get data
                        intent.putExtra(THAINAME,thainame.replace("[","").replace("]",""));
                        intent.putExtra(NAIL_A,nail_a.replace("[","").replace("]",""));
//                        intent.putExtra(NAIL_B,nail_b.replace("[","").replace("]",""));
                        intent.putExtra(EXTRA_IMAGE,bitmapPath);
                        startActivity(intent);
                    }
                    else { //not1
                                AlertDialog.Builder builder=new AlertDialog.Builder(main_camera.this);
                                builder.setMessage(" ไม่สามารถประมวลผลได้ ");
                                builder.setPositiveButton("ok",new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialogInterface,int id){
                                        Toast.makeText(getApplicationContext(),
                                                "กรุณาถ่ายใหม่",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.show();
                    }
                }
                else { //not2
                            AlertDialog.Builder builder=new AlertDialog.Builder(main_camera.this);
                            builder.setMessage(" ไม่สามารถประมวลได้ ");
                            builder.setPositiveButton("ok",new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialogInterface,int id){
                                    Toast.makeText(getApplicationContext(),
                                            "กรุณาถ่ายใหม่",Toast.LENGTH_SHORT).show();
                                }
                            });
                            builder.show();
                }

            }
        });

        //Button capture
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.capturePicture();
            }
        });

        btnToggleCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.toggleFacing();
            }
        });

        //get photo from gallery

//        searchPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent
//                        , "Select Picture"), REQUEST_GALLERY);
//            }
//        });

        initTensorFlowAndLoadModel();

    } //end function




    //camera preview
    @Override
        protected void onResume() {
        super.onResume();
        cameraView.start(); }
    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause(); }

    //to classifier
    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                classifier.close();
            }
        }); }

    //to tensorflow
    private void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_FILE,         //private static final String MODEL_FILE = "file:///android_asset/strip_output_graph.pb";
                            LABEL_FILE,         //private static final String LABEL_FILE = "file:///android_asset/output_labels.txt";
                            INPUT_SIZE,         //private static final int INPUT_SIZE = 299;
                            IMAGE_MEAN,         // private static final int IMAGE_MEAN = 128;
                            IMAGE_STD,          //private static final float IMAGE_STD = 128;
                            INPUT_NAME,         //private static final String INPUT_NAME = "Mul";
                            OUTPUT_NAME);       //private static final String OUTPUT_NAME = "final_result";
                    makeButtonVisible();
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }

    private void makeButtonVisible() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageButton.setVisibility(View.VISIBLE);
            }
        });

    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0,  source.getWidth(), source.getHeight(), matrix, true);
    }

    //
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File myPath = new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 20, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }


}
