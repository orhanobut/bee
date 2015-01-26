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

    @Button("Button")
    public void onTestClicked() {
        Log.d(TAG, "onTestClicked");
    }

    @Title("Checkbox")
    @CheckBox
    public void onTest2Clicked(boolean isChecked) {
        Log.d(TAG, "onTest2Clicked");
    }

    @Title("Test")
    @Spinner({"content", "content2"})
    public void onSpinnerClicked(String selectedValue) {
        Log.d(TAG, "onSpinnerClicked");
    }

}
