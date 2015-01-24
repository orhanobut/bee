package com.orhanobut.bee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @author Orhan Obut
 */
class SettingsAdapter extends BaseAdapter {

    private final List<ViewHolder> list;
    private final LayoutInflater inflater;

    SettingsAdapter(Context context, List<ViewHolder> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ViewHolder getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.item_settings, parent, false);

            holder = new Holder();
            holder.text = (TextView) view.findViewById(R.id.title);
            holder.container = (ViewGroup) view.findViewById(R.id.item_container);

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        ViewHolder item = getItem(position);
        String title = item.getTitle();
        if (title == null) {
            holder.text.setVisibility(View.GONE);
        } else {
            holder.text.setText(item.getTitle());
        }
        holder.container.removeAllViews();
        holder.container.addView(item.getView());

        return view;
    }

    private static class Holder {

        TextView text;
        ViewGroup container;

    }
}
