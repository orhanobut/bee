package com.orhanobut.bee;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Utility class to store settings
 */
final class PrefHelper {

  private PrefHelper() {
    // no instance
  }

  private static final String KEY_PREFS = "BeeConfigPrefs";

  static void setBoolean(Context context, String key, boolean value) {
    getEditor(context).putBoolean(key, value).commit();
  }

  static boolean getBoolean(Context context, String key) {
    return getPrefs(context).getBoolean(key, false);
  }

  static void setInt(Context context, String key, int value) {
    getEditor(context).putInt(key, value).commit();
  }

  static int getInt(Context context, String key) {
    return getPrefs(context).getInt(key, 0);
  }

  static SharedPreferences getPrefs(Context context) {
    return context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
  }

  private static SharedPreferences.Editor getEditor(Context context) {
    SharedPreferences prefs = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
    return prefs.edit();
  }

}
