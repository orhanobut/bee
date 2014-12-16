package com.github.nr4bt.bee;

import android.content.Context;
import android.widget.CompoundButton;

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

    void onClose();

    void onSave();

    void onItemSelected(int requestCode, String data);

    void onCheckedChanged(int requestCode, CompoundButton buttonView, boolean isChecked);

    Context getContext();
}
