package com.orhanobut.bee;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * It is used to provide necessary methods
 */
interface ConfigListener {

  @SuppressWarnings("unused") void onInfoContentCreated(Map<String, String> content);

  @SuppressWarnings("unused") void onLogContentCreated(List<String> list);

  @SuppressWarnings("unused") void onClose();

  @SuppressWarnings("unused") Context getContext();

  @SuppressWarnings("unused") void setContext(Context context);
}
