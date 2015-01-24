package com.orhanobut.bee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


/**
 * @author Orhan Obut
 */
final class ButtonViewHolder implements ViewHolder, View.OnClickListener {

    private final int requestCode;
    private final ConfigListener listener;
    private final Button button;

    ButtonViewHolder(Context context, String title, int requestCode, ConfigListener listener) {
        this.requestCode = requestCode;
        this.listener = listener;

        LayoutInflater inflater = LayoutInflater.from(context);
        button = (Button) inflater.inflate(R.layout.button, null);
        button.setText(title);
        button.setOnClickListener(this);
    }

    @Override
    public View getView() {
        return button;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        listener.onClickListener(requestCode);
    }

    public static class Builder extends ViewBuilder<Builder> {

        public ViewHolder build() {
            return new ButtonViewHolder(context, title, requestCode, listener);
        }
    }
}
