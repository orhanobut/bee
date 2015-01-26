package com.orhanobut.android.beesample;

import android.app.Activity;
import android.os.Bundle;

import com.orhanobut.bee.Bee;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bee.inject(this, AppBeeConfig.class);

    }

}
