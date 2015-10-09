package com.orhanobut.bee;

import android.content.Context;
import android.view.Gravity;

public class Settings {

  private static final int DEFAULT_BEE_SIZE = 80;

  private Context context;
  private int gravity = Gravity.CENTER_VERTICAL | Gravity.END;
  private int beeSize = DEFAULT_BEE_SIZE;
  private int[] beeMargin;

  public Settings(Context context) {
    this.context = context;
  }

  public Settings setBeePosition(int gravity) {
    this.gravity = gravity;
    return this;
  }

  public Settings setBeeMargin(int left, int top, int right, int bottom) {
    this.beeMargin = new int[]{left, top, right, bottom};
    return this;
  }

  public Settings setBeeSize(int size) {
    this.beeSize = size;
    return this;
  }

  public void inject(Class<?> config) {
    if (config == null) {
      throw new NullPointerException("config should not be null");
    }
    new Bee(this).inject(config);
  }

  public Context getContext() {
    return context;
  }

  public int getGravity() {
    return gravity;
  }

  public int getBeeSize() {
    return beeSize;
  }

  public int[] getBeeMargin() {
    return beeMargin;
  }
}
