package com.example.user.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class main_vaccine extends AppCompatActivity {

    //getdata
    private int resId;
    private String breed;
    private String descriptionA1;
    private String descriptionA2;
    private String description1;
    private String description2;

    //setdata
    private ImageView imageView;
    private TextView tvBreed;
    private TextView tvDescriptionA1;
    private TextView tvDescriptionA2;
    private TextView tvDescription1;
    private TextView tvDescription2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        initInstances();

        //Actionbar
        ActionBar actionBar=getSupportActionBar();
        //Set title
        actionBar.setTitle("vaccine");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


    }
    private void initInstances() {

        //getdata
        resId = getIntent().getIntExtra("resId",0);
        breed = getIntent().getStringExtra("breed");

        descriptionA1 = getString(R.string.nails_vaccine1);

//        descriptionA2 = getIntent().getStringExtra("descA2");
        description1 = getString(R.string.nails_vac1);
        //description2 = getIntent().getStringExtra("desc2");


        //setdata
        imageView = (ImageView) findViewById(R.id.image);
        tvBreed = (TextView) findViewById(R.id.title);
        tvDescriptionA1 = (TextView) findViewById(R.id.descA1);
//        tvDescriptionA2=(TextView)findViewById(R.id.descA2);
        tvDescription1=(TextView)findViewById(R.id.desc1);
//        tvDescription2=(TextView)findViewById(R.id.desc2);

        imageView.setImageResource(resId);
        tvBreed.setText(breed);
        tvDescriptionA1.setText(descriptionA1);
//        tvDescriptionA2.setText(descriptionA2);
        tvDescription1.setText(description1);
//        tvDescription2.setText("    "+description2);
    }

    //handle on BackPressed(go to previous activity)
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
