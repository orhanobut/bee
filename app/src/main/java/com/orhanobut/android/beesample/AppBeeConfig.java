package com.orhanobut.android.beesample;

import android.util.Log;

import com.orhanobut.bee.BeeConfig;
import com.orhanobut.bee.widgets.Button;
import com.orhanobut.bee.widgets.CheckBox;
import com.orhanobut.bee.widgets.Spinner;
import com.orhanobut.bee.widgets.Title;

import java.util.Map;

public class AppBeeConfig extends BeeConfig {

  private static final String TAG = AppBeeConfig.class.getSimpleName();

  /**
   * Add extra information by using content object.
   */
  @Override public void onInfoContentCreated(Map<String, String> content) {
    content.put("Current End Point", "http://www.google.com");
  }

  /**
   * It is called when the close button is pressed
   */
  @Override public void onClose() {
    super.onClose();
  }

  /**
   * Use Button annotation to add button to the settings menu.
   * Title is used for button text.
   * Method should have no parameter.
   */
  @Title("Reset")
  @Button
  public void onResetClicked() {
    Log.d(TAG, "onResetClicked");
  }

  @Title("Restart")
  @Button
  public void onRestartClicked() {
    Log.d(TAG, "onRestartClicked");
  }

  /**
   * Use CheckBox annotation to add checkbox to the settings menu.
   * Title is used for the label.
   * Method should have a boolean parameter
   */
  @Title("Show splash screen")
  @CheckBox
  public void onShowSplashChecked(boolean isChecked) {
    Log.d(TAG, "onShowSplashChecked");
  }

  @Title("Show ads")
  @CheckBox
  public void onShowAdsChecked(boolean isChecked) {
    Log.d(TAG, "onShowAdsChecked");
  }

  /**
   * Use Spinner annotation to add spinner to the settings menu.
   * Spinner annotation gets the content of spinner, either String[] or just String
   * Title is used for the label.
   * Method should have String parameter
   */
  @Title("End Point")
  @Spinner({"Staging", "Live", "Mock"})
  public void onEndPointSelected(String selectedValue) {
    Log.d(TAG, "onEndPointSelected");
  }

  @Title("Select user")
  @Spinner({"John Doe", "Foo Bar"})
  public void onUserSelected(String selectedValue) {
    Log.d(TAG, "onUserSelected");
  }

  @Title("Select store option")
  @Spinner({"SQLite", "In-Memory"})
  public void onStoreOptionSelected(String selectedValue) {
    Log.d(TAG, "onStoreOptionSelected");
  }

}
