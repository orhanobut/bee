package com.orhanobut.android.bee;

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

    private Context context;
    private String[] list;
    private String title;
    private int requestCode;
    private BeeConfigListener listener;
    private LayoutInflater layoutInflater;

    private SpinnerViewHolder() {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) parent.getItemAtPosition(position);
        listener.onItemSelected(requestCode, value);

        PrefsHelper.setInt(context, requestCode, position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View getView() {
        Spinner spinner = (Spinner) layoutInflater.inflate(R.layout.spinner, null);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context, R.layout.simple_spinner_item, list
        );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(PrefsHelper.getInt(context, requestCode));
        return spinner;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static class Builder {
        private final SpinnerViewHolder spinnerHolder = new SpinnerViewHolder();

        public Builder with(Context context) {
            spinnerHolder.context = context;
            return this;
        }

        public Builder setTitle(String title) {
            spinnerHolder.title = title;
            return this;
        }

        public Builder setList(String[] list) {
            spinnerHolder.list = list;
            return this;
        }

        public Builder from(int requestCode) {
            spinnerHolder.requestCode = requestCode;
            return this;
        }

        public Builder to(BeeConfigListener listener) {
            spinnerHolder.listener = listener;
            return this;
        }

        public ViewHolder build() {
            spinnerHolder.layoutInflater = LayoutInflater.from(spinnerHolder.context);
            return spinnerHolder;
        }
    }
}
