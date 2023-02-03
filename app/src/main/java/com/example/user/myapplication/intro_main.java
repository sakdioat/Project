package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;


public class intro_main extends Activity {

     int SPLASH_TIME_OUT = 1800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntro = new Intent(intro_main.this,MainActivity.class);
                startActivity(homeIntro);
                Animatoo.animateShrink(intro_main.this);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
