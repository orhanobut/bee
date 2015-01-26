package com.orhanobut.bee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Orhan Obut
 */
class SettingsAdapter extends BaseAdapter {

    private final List<MethodInfo> list;
    private final LayoutInflater inflater;

    private SettingsAdapter(Context context, List<MethodInfo> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    static BaseAdapter newInstance(Context context, List<MethodInfo> list) {
        return new SettingsAdapter(context, list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MethodInfo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        switch (getItemViewType(position)) {
            case ViewType.BUTTON:
                return createButton(parent, position);
            case ViewType.CHECKBOX:
                return createCheckBox(parent, position);
            case ViewType.SPINNER:
                return createSpinner(parent, position);
        }
        return null;
    }

    private View createSpinner(ViewGroup parent, int position) {

        final MethodInfo item = getItem(position);
        final Method method = item.getMethod();
        final Object instance = item.getInstance();
        final Context context = parent.getContext();

        View view = inflater.inflate(R.layout.item_settings_spinner, parent, false);
        ((TextView) view.findViewById(R.id.title)).setText(item.getTitle());

        String[] dataList = (String[]) item.getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                parent.getContext(), R.layout.simple_spinner_item, dataList
        );

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);

                try {
                    method.invoke(instance, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                PrefHelper.setInt(context, method.getName(), position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setSelection(PrefHelper.getInt(context, method.getName()));
        return view;
    }

    private View createButton(ViewGroup parent, int position) {

        final MethodInfo item = getItem(position);
        final Method method = item.getMethod();
        final Object instance = item.getInstance();

        View view = inflater.inflate(R.layout.item_settings_button, parent, false);
        Button button = (Button) view.findViewById(R.id.button);
        button.setText(String.valueOf(item.getData()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    method.invoke(instance);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private View createCheckBox(ViewGroup parent, int position) {
        final MethodInfo item = getItem(position);
        final Method method = item.getMethod();
        final Object instance = item.getInstance();
        final Context context = parent.getContext();

        View view = inflater.inflate(R.layout.item_settings_checkbox, parent, false);
        ((TextView) view.findViewById(R.id.title)).setText(item.getTitle());
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    method.invoke(instance, isChecked);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                PrefHelper.setBoolean(context, method.getName(), isChecked);
            }
        });
        checkBox.setChecked(PrefHelper.getBoolean(context, method.getName()));

        return view;
    }
}
