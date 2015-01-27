package com.orhanobut.android.beesample;

import android.util.Log;

import com.orhanobut.bee.BeeConfig;
import com.orhanobut.bee.widgets.Button;
import com.orhanobut.bee.widgets.CheckBox;
import com.orhanobut.bee.widgets.Spinner;
import com.orhanobut.bee.widgets.Title;

import java.util.Map;

/**
 * @author Orhan Obut
 */
public class AppBeeConfig extends BeeConfig {

    private static final String TAG = AppBeeConfig.class.getSimpleName();

    @Override
    public void onInfoContentCreated(Map<String, String> content) {
        content.put("Current End Point", "http://www.google.com");
    }

    @Override
    public void onClipboardContentCreated(Map<String, String> content) {
        content.put("User1", "324234234");
        content.put("Visa Expire Date", "2/16");
        content.put("Visa Code", "34");
    }

    @Override
    public void onSave() {
        super.onSave();
    }

    @Override
    public void onClose() {
        super.onClose();
    }

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
