package com.orhanobut.bee;

import android.content.Context;
import android.widget.CompoundButton;

import java.util.List;
import java.util.Map;

/**
 * @author Orhan Obut
 */
public abstract class BeeConfig implements BeeConfigListener {

    private Context context;

    @Override
    public void inject(Context context) {
        this.context = context;

        Bee.Builder builder = new Bee.Builder()
                .with(context)
                .to(this);
        onMenuContentCreated(builder);
        builder.build().inject();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onMenuContentCreated(Bee.Builder builder) {
    }

    @Override
    public void onClose() {

    }

    @Override
    public void onSave() {

    }

    @Override
    public void onItemSelected(int requestCode, String data) {

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

    @Override
    public void onCheckedChanged(int requestCode, CompoundButton buttonView, boolean isChecked) {

    }
}
