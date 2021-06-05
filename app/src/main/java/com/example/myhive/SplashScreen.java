package com.example.myhive;

import android.app.Activity;
import android.os.Bundle;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLEY_LENGHT = 5000;//5000ms
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spashscreen);
    }
}
