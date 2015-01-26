package com.orhanobut.bee;

import android.content.Context;

import com.orhanobut.bee.widgets.Button;
import com.orhanobut.bee.widgets.CheckBox;
import com.orhanobut.bee.widgets.Spinner;
import com.orhanobut.bee.widgets.Title;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Orhan Obut
 */
class MethodInfo {

    static final int INVALID = -1;

    private final Context context;
    private final Method method;

    private int viewType = INVALID;
    private Object data;
    private String title;
    private Object instance;

    private MethodInfo(Context context, Method method, Object instance) {
        this.context = context;
        this.method = method;
        this.instance = instance;

        parseMethodAnnotations();
    }

    static MethodInfo newInstance(Context context, Method method, Object instance) {
        return new MethodInfo(context, method, instance);
    }

    private void parseMethodAnnotations() {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();

            if (annotationType == Title.class) {
                title = ((Title) annotation).value();
                continue;
            }

            if (annotationType == Button.class) {
                data = ((Button) annotation).value();
                viewType = ViewType.BUTTON;
                continue;
            }

            if (annotationType == CheckBox.class) {
                viewType = ViewType.CHECKBOX;
                continue;
            }

            if (annotationType == Spinner.class) {
                data = ((Spinner) annotation).value();
                viewType = ViewType.SPINNER;
            }
        }
    }

    Method getMethod() {
        return method;
    }

    int getViewType() {
        return viewType;
    }

    Object getData() {
        return data;
    }

    String getTitle() {
        return title;
    }

    Object getInstance() {
        return instance;
    }
}
