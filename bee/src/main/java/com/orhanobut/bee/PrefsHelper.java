package com.orhanobut.bee;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Orhan Obut
 */
final class PrefsHelper {

    private static final String KEY_PREFS = "BeeConfigPrefs";

    static void setBoolean(Context context, int requestCode, boolean value) {
        getEditor(context).putBoolean(getKey(requestCode), value).commit();
    }

    static boolean getBoolean(Context context, int requestCode) {
        return getPrefs(context).getBoolean(getKey(requestCode), false);
    }

    static void setInt(Context context, int requestCode, int value) {
        getEditor(context).putInt(getKey(requestCode), value).commit();
    }

    static int getInt(Context context, int requestCode) {
        return getPrefs(context).getInt(getKey(requestCode), 0);
    }

    static SharedPreferences getPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        return prefs;
    }

    static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        return prefs.edit();
    }

    static String getKey(int requestCode) {
        return KEY_PREFS + requestCode;
    }
}
