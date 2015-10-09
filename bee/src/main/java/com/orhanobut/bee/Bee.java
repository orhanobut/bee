package com.orhanobut.bee;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Bee is a debug and QA tool to analyze the app, change the settings or view custom information
 */
public class Bee {

  private static final String TAG = Bee.class.getSimpleName();

  private final Settings settings;

  public Bee(Settings settings) {
    this.settings = settings;
  }

  public static Settings init(Context context) {
    return new Settings(context);
  }

  public void inject(Class<?> config) {
    if (settings.getContext() == null) {
      throw new NullPointerException("Context may not be null");
    }
    if (config == null) {
      throw new NullPointerException("Class may not be null");
    }

    //noinspection TryWithIdenticalCatches
    try {
      new BeeHandler(settings, config);
    } catch (IllegalAccessException e) {
      Log.d(TAG, e.getMessage());
    } catch (InstantiationException e) {
      Log.d(TAG, e.getMessage());
    }
  }

  static class BeeHandler {

    private final ConfigListener instance;
    private final List<MethodInfo> methodInfoList = new ArrayList<>();
    private final UiHandler helper;

    BeeHandler(Settings settings, Class<?> clazz) throws IllegalAccessException, InstantiationException {
      instance = (ConfigListener) clazz.newInstance();
      instance.setContext(settings.getContext());

      fillMethods(clazz.getDeclaredMethods());

      helper = new UiHandler(settings, methodInfoList, instance);
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
