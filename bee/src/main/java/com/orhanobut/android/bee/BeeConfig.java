package com.orhanobut.android.bee;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * @author Orhan Obut
 */
public abstract class BeeConfig implements BeeConfigListener {

    @Override
    public void inject(Context context) {
        Bee.Builder builder = new Bee.Builder()
                .with(context)
                .to(this);
        onMenuContentCreated(builder);
        builder.build().inject();
    }

    @Override
    public void onMenuContentCreated(Bee.Builder builder) {
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

    @Override
    public void onInfoContentCreated(Map<String, String> content) {
    }

    @Override
    public void onLogContentCreated(List<String> logList) {

    }

    @Override
    public void onClipboardContentCreated(Map<String, String> content) {

    }
}
