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
final class SpinnerHolder implements Holder, AdapterView.OnItemSelectedListener {

    private Context context;
    private String[] list;
    private String title;
    private int requestCode;
    private BeeListener listener;
    private LayoutInflater layoutInflater;

    private SpinnerHolder() {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) parent.getItemAtPosition(position);
        listener.onItemSelected(context, requestCode, value);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View getView() {
        Spinner spinner = (Spinner) layoutInflater.inflate(R.layout.spinner, null);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context, R.layout.simple_spinner_item, list
        );
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return spinner;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static class Builder {
        private final SpinnerHolder spinnerHolder = new SpinnerHolder();

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

        public Builder to(BeeListener listener) {
            spinnerHolder.listener = listener;
            return this;
        }

        public Holder build() {
            spinnerHolder.layoutInflater = LayoutInflater.from(spinnerHolder.context);
            return spinnerHolder;
        }
    }
}
