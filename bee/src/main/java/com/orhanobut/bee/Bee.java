package com.orhanobut.bee;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Bee is a debug and QA tool to analyze the app, change the settings or view custom information
 *
 * @author Orhan Obut
 */
public class Bee {

    private static final String TAG = Bee.class.getSimpleName();

    public static void inject(Context context, Class<?> clazz) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        if (clazz == null) {
            throw new NullPointerException("Class may not be null");
        }

        try {
            new BeeHandler(context, clazz);
        } catch (IllegalAccessException | InstantiationException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    static class BeeHandler {

        private final ConfigListener instance;
        private final List<MethodInfo> methodInfoList = new ArrayList<>();
        private final UiHandler helper;

        BeeHandler(Context context, Class<?> clazz) throws IllegalAccessException, InstantiationException {
            instance = (ConfigListener) clazz.newInstance();
            instance.setContext(context);

            fillMethods(clazz.getDeclaredMethods());

            helper = new UiHandler(context, methodInfoList, instance);
            helper.inject();
        }

        private void fillMethods(Method[] methods) {
            for (Method method : methods) {
                MethodInfo methodInfo = MethodInfo.newInstance(method, instance);
                if (methodInfo.getViewType() != MethodInfo.INVALID) {
                    methodInfoList.add(methodInfo);
                }
            }
        }

    }
}
