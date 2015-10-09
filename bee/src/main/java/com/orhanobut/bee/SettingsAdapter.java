package com.orhanobut.bee;

import android.content.Context;
import android.util.Log;
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

import java.lang.reflect.Method;
import java.util.List;

/**
 * It is used to create setting items by using the given config class
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

  @Override public int getCount() {
    return list.size();
  }

  @Override public MethodInfo getItem(int position) {
    return list.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public int getItemViewType(int position) {
    return getItem(position).getViewType();
  }

  @Override public View getView(int position, View convertView, final ViewGroup parent) {
    MethodInfo methodInfo = getItem(position);
    switch (getItemViewType(position)) {
      case MethodInfo.VIEW_BUTTON:
        return createButton(parent, methodInfo);
      case MethodInfo.VIEW_CHECKBOX:
        return createCheckBox(parent, methodInfo);
      case MethodInfo.VIEW_SPINNER:
        return createSpinner(parent, methodInfo);
      default:
        throw new IllegalArgumentException("view type should be one of the following: BUTTON, CHECKBOX, SPINNER");
    }
  }

  private View createSpinner(ViewGroup parent, MethodInfo methodInfo) {

    final Method method = methodInfo.getMethod();
    final Object instance = methodInfo.getInstance();
    final Context context = parent.getContext();

    View view = inflater.inflate(R.layout.item_settings_spinner, parent, false);
    ((TextView) view.findViewById(R.id.title)).setText(methodInfo.getTitle());

    String[] dataList = (String[]) methodInfo.getData();
    ArrayAdapter<String> adapter = new ArrayAdapter<>(
        parent.getContext(), R.layout.simple_spinner_item, dataList
    );

    Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) parent.getItemAtPosition(position);

        try {
          method.invoke(instance, value);
        } catch (Exception e) {
          Log.e("Bee", e.getMessage());
        }

        PrefHelper.setInt(context, method.getName(), position);
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {
      }
    });
    spinner.setSelection(PrefHelper.getInt(context, method.getName()));
    return view;
  }

  private View createButton(ViewGroup parent, MethodInfo methodInfo) {

    final Method method = methodInfo.getMethod();
    final Object instance = methodInfo.getInstance();

    View view = inflater.inflate(R.layout.item_settings_button, parent, false);
    Button button = (Button) view.findViewById(R.id.button);
    button.setText(methodInfo.getTitle());
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          method.invoke(instance);
        } catch (Exception e) {
          Log.e("Bee", e.getMessage());
        }
      }
    });

    return view;
  }

  private View createCheckBox(ViewGroup parent, MethodInfo methodInfo) {
    final Method method = methodInfo.getMethod();
    final Object instance = methodInfo.getInstance();
    final Context context = parent.getContext();

    View view = inflater.inflate(R.layout.item_settings_checkbox, parent, false);
    ((TextView) view.findViewById(R.id.title)).setText(methodInfo.getTitle());
    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {
          method.invoke(instance, isChecked);
        } catch (Exception e) {
          Log.e("Bee", e.getMessage());
        }

        PrefHelper.setBoolean(context, method.getName(), isChecked);
      }
    });
    checkBox.setChecked(PrefHelper.getBoolean(context, method.getName()));

    return view;
  }

}
