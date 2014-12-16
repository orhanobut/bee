package com.github.nr4bt.bee;

import android.util.Log;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Orhan Obut
 */
public class BeeLog {

    private static final List<ContentHolder> logHistory = new LinkedList<>();
    private static List<ContentHolder> clipboardContent;

    public static void d(String tag, String message) {
        Log.d(tag, message);
        addToHistory(tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
        addToHistory(tag, message);
    }

    public static void w(String tag, String message) {
        Log.w(tag, message);
        addToHistory(tag, message);
    }

    public static void i(String tag, String message) {
        Log.i(tag, message);
        addToHistory(tag, message);
    }

    private static void addToHistory(String tag, String message) {
        logHistory.add(new LogHistory(tag, message, new Date()));
    }

    static List<ContentHolder> getLogHistory() {
        return logHistory;
    }

    public static List<ContentHolder> getClipboardContent() {
        return clipboardContent;
    }
}
