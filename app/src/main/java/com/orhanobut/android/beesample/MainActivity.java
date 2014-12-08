package com.orhanobut.android.beesample;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AppBeeConfig().inject(this);
    }

}
