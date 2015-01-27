package com.orhanobut.android.beesample;

import android.app.Activity;
import android.os.Bundle;

import com.orhanobut.bee.Bee;
import com.orhanobut.bee.BeeLog;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bee.inject(this, AppBeeConfig.class);

        BeeLog.d("MainActivity", "onCreate");
        BeeLog.d("MainActivity", "user logged in");
        BeeLog.d("MainActivity", "user logged out");
        BeeLog.d("MainActivity", "onDestroyed");

    }

}
