package com.orhanobut.bee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;


/**
 * @author Orhan Obut
 */
final class CheckboxViewHolder implements ViewHolder, CompoundButton.OnCheckedChangeListener {

    private final Context context;
    private final String title;
    private final int requestCode;
    private final ConfigListener listener;
    private final CheckBox checkBox;

    CheckboxViewHolder(Context context, String title, int requestCode, ConfigListener listener) {
        this.context = context;
        this.title = title;
        this.requestCode = requestCode;
        this.listener = listener;

        LayoutInflater inflater = LayoutInflater.from(context);
        checkBox = (CheckBox) inflater.inflate(R.layout.checkbox, null);
        checkBox.setOnCheckedChangeListener(this);
        checkBox.setChecked(PrefHelper.getBoolean(context, requestCode));
    }

    @Override
    public View getView() {
        return checkBox;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listener.onCheckedChanged(requestCode, buttonView, isChecked);

        PrefHelper.setBoolean(context, requestCode, isChecked);
    }

    public static class Builder extends ViewBuilder<Builder> {

        public ViewHolder build() {
            return new CheckboxViewHolder(context, title, requestCode, listener);
        }
    }
}
