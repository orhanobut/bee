package com.orhanobut.android.bee;

import android.content.Context;

/**
 * @author Orhan Obut.
 *         <p/>
 *         For the future, all operations should be added here in order to take action
 */
public interface BeeListener {

    void inject(Context context);

    void onClose(Context context);

    void onSave(Context context);

    void onItemSelected(Context context, int requestCode, String data);
}
