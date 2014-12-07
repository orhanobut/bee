package com.orhanobut.android.bee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * @author Orhan Obut
 */
final class CheckboxViewHolder implements ViewHolder, CompoundButton.OnCheckedChangeListener {

    private Context context;
    private String title;
    private int requestCode;
    private BeeConfigListener listener;
    private LayoutInflater layoutInflater;

    private CheckboxViewHolder() {
    }

    @Override
    public View getView() {
        CheckBox checkBox = (CheckBox) layoutInflater.inflate(R.layout.checkbox, null);
        checkBox.setOnCheckedChangeListener(this);
        checkBox.setChecked(PrefsHelper.getBoolean(context, requestCode));
        return checkBox;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listener.onCheckedChanged(requestCode, buttonView, isChecked);

        PrefsHelper.setBoolean(context, requestCode, isChecked);
    }

    public static class Builder {
        private final CheckboxViewHolder holder = new CheckboxViewHolder();

        public Builder with(Context context) {
            holder.context = context;
            return this;
        }

        public Builder setTitle(String title) {
            holder.title = title;
            return this;
        }

        public Builder from(int requestCode) {
            holder.requestCode = requestCode;
            return this;
        }

        public Builder to(BeeConfigListener listener) {
            holder.listener = listener;
            return this;
        }

        public ViewHolder build() {
            holder.layoutInflater = LayoutInflater.from(holder.context);
            return holder;
        }
    }
}
