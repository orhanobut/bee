package com.orhanobut.android.beesample;

import android.content.Context;

import com.orhanobut.android.bee.Bee;
import com.orhanobut.android.bee.BeeListener;

/**
 * @author Orhan Obut
 */
public class SampleBeeListener implements BeeListener {

    @Override
    public void inject(Context context) {
        new Bee.Builder()
                .with(context)
                .to(this)
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
                .addSpinner("test", new String[]{"asdf"}, 0)
                .addSpinner("test", new String[]{"asdf"}, 0)
                .build()
                .inject();
    }

    @Override
    public void onClose(Context context) {

    }

    @Override
    public void onSave(Context context) {

    }

    @Override
    public void onItemSelected(Context context, int requestCode, String data) {

    }
}
