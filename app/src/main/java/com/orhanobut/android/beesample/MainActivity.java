package com.orhanobut.android.beesample;

import android.app.Activity;
import android.os.Bundle;

import com.orhanobut.android.bee.BeeLog;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BeeLog.d(TAG, "Test 4234");
        new AppBeeConfig().inject(this);
        BeeLog.d(TAG, "Test 4234");

        BeeLog.d(TAG, "Test 4234");
        BeeLog.d(TAG, "Test 4234");
        BeeLog.d(TAG, "Test 4234");
        BeeLog.d(TAG, "Test 4234");
        BeeLog.d(TAG, "Test 4234");
        BeeLog.d(TAG, "Test 4234");
        BeeLog.d(TAG, "Test 4234");
        BeeLog.d(TAG, "Test 4234");

    }

}
