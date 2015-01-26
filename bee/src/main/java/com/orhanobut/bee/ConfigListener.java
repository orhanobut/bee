package com.orhanobut.bee;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * @author Orhan Obut.
 *         <p/>
 *         For the future, all operations should be added here in order to take action
 */
interface ConfigListener {

    void onInfoContentCreated(Map<String, String> content);

    void onLogContentCreated(List<String> list);

    void onClipboardContentCreated(Map<String, String> content);

    void onClose();

    void onSave();

    Context getContext();

    void setContext(Context context);
}
