package com.orhanobut.android.bee;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * @author Orhan Obut.
 *         <p/>
 *         For the future, all operations should be added here in order to take action
 */
interface BeeConfigListener {

    void inject(Context context);

    void onMenuContentCreated(Bee.Builder builder);

    void onInfoContentCreated(Map<String, String> content);

    void onLogContentCreated(List<String> list);

    void onClipboardContentCreated(Map<String, String> content);

    void onClose(Context context);

    void onSave(Context context);

    void onItemSelected(Context context, int requestCode, String data);
}
