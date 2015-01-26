package com.orhanobut.bee;

import android.content.Context;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Orhan Obut
 */
public class Bee {

    private static final String TAG = Bee.class.getSimpleName();

    public static void inject(Context context, Class<?> clazz) {
        try {
            new BeeHandler(context, clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    static class BeeHandler {

        private final Context context;
        private final ConfigListener instance;
        private final List<MethodInfo> methodInfoList = new ArrayList<>();
        private final BeeHelper helper;

        BeeHandler(Context context, Class<?> clazz) throws IllegalAccessException, InstantiationException {
            this.context = context;

            instance = (ConfigListener) clazz.newInstance();
            instance.setContext(context);

            fillMethods(clazz.getDeclaredMethods());

            helper = new BeeHelper(context, methodInfoList, instance);
            helper.inject();
        }

        private void fillMethods(Method[] methods) {
            for (Method method : methods) {
                MethodInfo methodInfo = MethodInfo.newInstance(context, method, instance);
                if (methodInfo.getViewType() != MethodInfo.INVALID) {
                    methodInfoList.add(methodInfo);
                }
            }
        }

    }
}
