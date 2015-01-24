package com.orhanobut.bee;

import android.content.Context;

/**
 * @author Orhan Obut
 */
abstract class ViewBuilder<T> {

    protected Context context;
    protected String title;
    protected int requestCode;
    protected ConfigListener listener;

    public T with(Context context) {
        this.context = context;
        return (T) this;
    }

    public T setTitle(String title) {
        this.title = title;
        return (T) this;
    }

    public T from(int requestCode) {
        this.requestCode = requestCode;
        return (T) this;
    }

    public T to(ConfigListener listener) {
        this.listener = listener;
        return (T) this;
    }

    public abstract ViewHolder build();
}
