package com.orhanobut.bee;

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

    private final Method method;

    private int viewType = INVALID;
    private Object data;
    private String title;
    private Object instance;

    private MethodInfo(Method method, Object instance) {
        this.method = method;
        this.instance = instance;

        parseMethodAnnotations();
    }

    static MethodInfo newInstance(Method method, Object instance) {
        return new MethodInfo(method, instance);
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
