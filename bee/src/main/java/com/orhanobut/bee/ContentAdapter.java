package com.orhanobut.bee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class ContentAdapter extends BaseAdapter {

  private final List<ContentHolder> list;
  private final LayoutInflater layoutInflater;

  public ContentAdapter(Context context, List<ContentHolder> list) {
    this.layoutInflater = LayoutInflater.from(context);
    this.list = list;
  }

  @Override public int getCount() {
    return list.size();
  }

  @Override public ContentHolder getItem(int position) {
    return list.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    Holder holder;
    View view = convertView;

    if (view == null) {
      view = layoutInflater.inflate(R.layout.item_content, parent, false);
      holder = new Holder();
      holder.title = (TextView) view.findViewById(R.id.title);
      holder.value = (TextView) view.findViewById(R.id.value);
      view.setTag(holder);
    } else {
      holder = (Holder) view.getTag();
    }

    ContentHolder item = getItem(position);
    holder.title.setText(item.getTitle());
    holder.value.setText(item.getValue());

    return view;
  }

  static class Holder {
    TextView title;
    TextView value;
  }
}
