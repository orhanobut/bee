package com.orhanobut.bee;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.os.SystemClock;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class UiHandler implements View.OnClickListener {

  private static final String TAG = UiHandler.class.getSimpleName();

  /**
   * Stores all settings views
   */
  private final List<MethodInfo> methodInfoList;

  /**
   * Must be activity context
   */
  private Context context;

  /**
   * Top most view that user sees
   */
  private final ViewGroup mainContainer;

  /**
   * The bee button that user can drag and open the menu
   */
  private final ImageView beeImageView;

  /**
   * All settings will be shown in this list
   */
  private final ListView listView;

  private final ConfigListener configListener;

  private final Point displaySize;

  private final Settings settings;

  public UiHandler(Settings settings, List<MethodInfo> list, ConfigListener listener) {
    this.settings = settings;
    this.context = settings.getContext();
    this.methodInfoList = list;
    this.configListener = listener;

    Activity activity = (Activity) context;
    ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
    LayoutInflater inflater = LayoutInflater.from(context);
    inflater.inflate(R.layout.container, rootView, true);
    mainContainer = (ViewGroup) rootView.findViewById(R.id.main_container);
    listView = (ListView) mainContainer.findViewById(R.id.list);
    beeImageView = new ImageView(context);
    initListView(listView);

    //calculate the display size
    Display display = activity.getWindowManager().getDefaultDisplay();
    displaySize = new Point();
    displaySize.set(display.getWidth(), display.getHeight());
  }

  private void initListView(ListView listView) {
    final ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ContentHolder contentHolder = (ContentHolder) parent.getItemAtPosition(position);
        clipboard.setText(contentHolder.getValue());
        showToast(contentHolder.getValue() + " is copied to clipboard");
        return true;
      }
    });
  }

  /**
   * Inject the bee mainContainer and adds all preferences that are set
   */
  void inject() {
    Activity activity = (Activity) context;
    ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
    setBeeButton(rootView);
    initListeners(rootView);
    initSettingsContent(methodInfoList);
  }

  void initSettingsContent(List<MethodInfo> list) {
    BaseAdapter adapter = SettingsAdapter.newInstance(context, list);
    listView.setAdapter(adapter);
  }

  private void showToast(String message) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }

  private void initLogContent() {
    List<ContentHolder> list = BeeLog.getLogHistory();
    Collections.reverse(list);
    ContentAdapter adapter = new ContentAdapter(context, list);
    listView.setAdapter(adapter);
  }

  private void initInfoContent() {
    Map<String, String> infoContent = createInfoContent();
    configListener.onInfoContentCreated(infoContent);

    List<ContentHolder> list = new ArrayList<>(infoContent.size());
    for (Map.Entry<String, String> entry : infoContent.entrySet()) {
      list.add(new ContentItem(entry.getKey(), entry.getValue()));
    }
    ContentAdapter adapter = new ContentAdapter(context, list);
    listView.setAdapter(adapter);
  }

  /**
   * It is called when the predefined information is created
   *
   * @return map which contains some predefined information
   */
  private Map<String, String> createInfoContent() {
    PackageInfo packageInfo;
    Map<String, String> content = new LinkedHashMap<>();
    try {
      PackageManager packageManager = context.getPackageManager();
      packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      Log.d(TAG, "name not found");
      return content;
    }

    content.put("Package Name", packageInfo.packageName);
    content.put("Version Name", packageInfo.versionName);
    content.put("Version Code", "" + packageInfo.versionCode);
    content.put("First Install Time", new Date(packageInfo.firstInstallTime).toString());
    content.put("Update Time", new Date(packageInfo.lastUpdateTime).toString());
    content.put("Android Version", Build.VERSION.RELEASE);
    content.put("Display", Build.DISPLAY);
    content.put("Device Model", Build.MODEL);
    return content;
  }

  /**
   * It is called in order to create listeners that user can interact with.
   *
   * @param view is the main container
   */
  private void initListeners(View view) {
    view.findViewById(R.id.close).setOnClickListener(this);
    view.findViewById(R.id.settings).setOnClickListener(this);
    view.findViewById(R.id.info).setOnClickListener(this);
    view.findViewById(R.id.log).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.close) {
      mainContainer.setVisibility(View.GONE);
      beeImageView.setVisibility(View.VISIBLE);
      configListener.onClose();
      return;
    }
    if (id == R.id.settings) {
      initSettingsContent(methodInfoList);
      return;
    }
    if (id == R.id.info) {
      initInfoContent();
      return;
    }
    if (id == R.id.log) {
      initLogContent();
    }
  }

  private void setBeeButton(ViewGroup rootView) {
    beeImageView.setImageResource(R.drawable.bee);
    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
        settings.getBeeSize(), settings.getBeeSize(), settings.getGravity()
    );
    int[] margin = settings.getBeeMargin();
    if (margin != null) {
      params.setMargins(margin[0], margin[1], margin[2], margin[3]);
    }

    beeImageView.setLayoutParams(params);
    beeImageView.setOnClickListener(null);

    beeImageView.setOnTouchListener(onTouchListener);
    rootView.addView(beeImageView);
  }

  private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {

    private static final int MIN_MOVEMENT = 20;

    final BeeGestureListener gestureListener = new BeeGestureListener();
    final GestureDetector gestureDetector = new GestureDetector(context, gestureListener);

    PointF touchPos = new PointF();
    long touchTime;

    @Override public boolean onTouch(View v, MotionEvent event) {
      if (gestureDetector.onTouchEvent(event)) {
        return true;
      }
      switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
          touchTime = SystemClock.uptimeMillis();
          touchPos.set(event.getX(), event.getY());
          break;
        case MotionEvent.ACTION_MOVE:
          int x = (int) event.getRawX();
          int y = (int) event.getRawY();

          if (!isMoveable(x, y)) {
            break;
          }
          if (!isInBoundaries(x, y)) {
            break;
          }

          FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
          params.topMargin = y - v.getHeight() / 2;
          params.leftMargin = x - v.getWidth() / 2;
          params.gravity = Gravity.NO_GRAVITY;
          v.setLayoutParams(params);
          break;
        default:
          break;
      }
      return SystemClock.uptimeMillis() - touchTime > 200;
    }

    private boolean isMoveable(int x, int y) {
      //noinspection SimplifiableIfStatement
      if (SystemClock.uptimeMillis() - touchTime < 200) {
        return false;
      }
      return (Math.abs(x - touchPos.x) > MIN_MOVEMENT || Math.abs(y - touchPos.y) > MIN_MOVEMENT);
    }

    private boolean isInBoundaries(int x, int y) {
      int half = settings.getBeeSize() / 2;
      return !(x + half > displaySize.x || x < half || y + half > displaySize.y || y < half + 50);
    }

  };

  /**
   * If the user single tap, it will open the bee, if the user moves the bee, nothing will happen but movement
   */
  private class BeeGestureListener implements GestureDetector.OnGestureListener {

    @Override public boolean onDown(MotionEvent e) {
      return false;
    }

    @Override public void onShowPress(MotionEvent e) {
    }

    @Override public boolean onSingleTapUp(MotionEvent e) {
      mainContainer.setVisibility(View.VISIBLE);
      beeImageView.setVisibility(View.GONE);
      return true;
    }

    @Override public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      return false;
    }

    @Override public void onLongPress(MotionEvent e) {
    }

    @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      return false;
    }
  }

}
