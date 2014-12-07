package com.orhanobut.android.beesample;

import android.content.Context;

import com.orhanobut.android.bee.Bee;
import com.orhanobut.android.bee.BeeConfig;

import java.util.Map;

/**
 * @author Orhan Obut
 */
public class AppBeeConfig extends BeeConfig {

    @Override
    public void onClose(Context context) {

    }

    @Override
    public void onSave(Context context) {

    }

    @Override
    public void onItemSelected(Context context, int requestCode, String data) {

    }

    @Override
    public void onMenuContentCreated(Bee.Builder builder) {
        super.onMenuContentCreated(builder);
        builder.addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0);
    }

    @Override
    public void onInfoContentCreated(Map<String, String> content) {
        content.put("Current End Point", "http://asdfadsfdsa");
    }

    @Override
    public void onClipboardContentCreated(Map<String, String> content) {
        content.put("Visa", "234 234 234 234 23423");
        content.put("Visa Expire Date", "06/16");
        content.put("Visa Code", "737");
    }
}
