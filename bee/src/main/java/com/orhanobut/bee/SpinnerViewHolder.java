package com.orhanobut.bee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * @author Orhan Obut
 */
final class SpinnerViewHolder implements ViewHolder, AdapterView.OnItemSelectedListener {

    private final Context context;
    private final String title;
    private final int requestCode;
    private final ConfigListener listener;
    private final Spinner spinner;

    SpinnerViewHolder(Context context, String title, String[] list, int requestCode, ConfigListener listener) {
        this.context = context;
        this.title = title;
        this.requestCode = requestCode;
        this.listener = listener;

        LayoutInflater inflater = LayoutInflater.from(context);
        spinner = (Spinner) inflater.inflate(R.layout.spinner, null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, R.layout.simple_spinner_item, list
        );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(PrefHelper.getInt(context, requestCode));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) parent.getItemAtPosition(position);
        listener.onItemSelected(requestCode, value);

        PrefHelper.setInt(context, requestCode, position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View getView() {
        return spinner;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static class Builder extends ViewBuilder<Builder> {

        private String[] list;

        public Builder setList(String[] list) {
            this.list = list;
            return this;
        }

        @Override
        public ViewHolder build() {
            return new SpinnerViewHolder(context, title, list, requestCode, listener);
        }
    }
}
