package com.example.user.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

import static com.example.user.myapplication.main_camera.REQUEST_GALLERY;

public class result_maincamera extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_maincamera);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("ผลลัพธ์");
        Intent intent = getIntent();

        //get data
        //String message = intent.getStringExtra(main_camera.EXTRA_MESSAGE); //message main
        String bitmapPath = intent.getStringExtra(main_camera.EXTRA_IMAGE);//image main
        String confident=intent.getStringExtra(main_camera.CONFIDENT);
        String thainame=intent.getStringExtra(main_camera.THAINAME);

        String nail_a=intent.getStringExtra(main_camera.NAIL_A);
//        String nail_b=intent.getStringExtra(main_camera.NAIL_B);
//        String care=intent.getStringExtra(main_camera.CARE);
//        String save=intent.getStringExtra(main_camera.SAVE);

        //maching xml
        //TextView textView = findViewById(R.id.textViewResult);
        TextView confidentTV = findViewById(R.id.confident);
        TextView thainTv=findViewById(R.id.thainame);
        TextView nail_aTv=findViewById(R.id.nail_a);
//        TextView nail_bTv=findViewById(R.id.nail_b);
//        TextView careTv=findViewById(R.id.care);
//        TextView saveTv=findViewById(R.id.save);

        //set Text
        //textView.setText(message);
        confidentTV.setText(confident);

        thainTv.setText(thainame);
        nail_aTv.setText("    "+nail_a);
//        nail_bTv.setText(nail_b);
//        careTv.setText("    "+care);
//        saveTv.setText("    "+save);


        loadImageFromStorage(bitmapPath);


    }
    private void loadImageFromStorage(String path)
    {
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

            ImageView img = findViewById(R.id.imageViewResult);
            img.setImageBitmap(b);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    //function get Gallery
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
//            Uri uri = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                ImageView img = findViewById(R.id.imageViewResult);
//                img.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
