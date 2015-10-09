package com.orhanobut.android.beesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;

import com.orhanobut.bee.Bee;
import com.orhanobut.bee.BeeLog;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    Bee.init(this)
        .setBeeSize(100)
        .setBeePosition(Gravity.CENTER)
        .setBeeMargin(0, 0, 0, 400)
        .inject(AppBeeConfig.class);

    BeeLog.d("MainActivity", "onCreate");
    BeeLog.d("MainActivity", "user logged in");
    BeeLog.d("MainActivity", "user logged out");
    BeeLog.d("MainActivity", "onDestroyed");
  }

}
