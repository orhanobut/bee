package com.orhanobut.bee;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Orhan Obut
 */
public final class Bee implements View.OnClickListener {

    private static final String TAG = Bee.class.getSimpleName();

    /**
     * Bee button width
     */
    private static final int DRAWER_BUTTON_WIDTH = 100;

    /**
     * Stores all settings views
     */
    private final List<ViewHolder> viewHolderList;

    /**
     * Must be activity context
     */
    private Context context;

    /**
     * All interaction will be handled by this
     */
    private final BeeConfigListener config;

    /**
     * Top most view that user sees
     */
    private final ViewGroup mainContainer;

    /**
     * The bee button that user can drag and open the menu
     */
    private final ImageView beeImageView;

    /**
     * Settings container
     */
    private final ViewGroup menuContainer;

    /**
     * This is the actual container for the settings
     */
    private final ScrollView menuScrollContainer;

    /**
     * All bee logs will be shown in this list
     */
    private final ListView logListView;

    /**
     * All information will be shown in this list
     */
    private final ListView infoListView;

    /**
     * All clipboard information will be shown in this list
     */
    private final ListView clipboardListView;

    /**
     * This is the title that is shown at top in order to navigate the user
     */
    private final TextView titleTextView;

    private Bee(Builder builder) {
        this.context = builder.context;
        this.config = builder.config;
        this.viewHolderList = builder.holderList;

        Activity activity = (Activity) context;
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.container, rootView, true);
        mainContainer = (ViewGroup) rootView.findViewById(R.id.main_container);
        menuContainer = (ViewGroup) mainContainer.findViewById(R.id.menu_container);
        logListView = (ListView) mainContainer.findViewById(R.id.log_list);
        infoListView = (ListView) mainContainer.findViewById(R.id.info_list);
        clipboardListView = (ListView) mainContainer.findViewById(R.id.clipboard_list);
        menuScrollContainer = (ScrollView) mainContainer.findViewById(R.id.menu_scroll_container);
        titleTextView = (TextView) mainContainer.findViewById(R.id.container_title);
        beeImageView = new ImageView(context);
    }

    /**
     * Inject the bee mainContainer and adds all preferences that are set
     */
    public void inject() {
        Activity activity = (Activity) context;
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        setBeeButton(rootView);
        initListeners(rootView);
        initMenuContent(menuContainer);
        initInfoContent(infoListView);
        initLogContent(logListView);
        initClipboardContent(clipboardListView);
    }

    /**
     * It is called when the clipboard is created
     *
     * @param listView is required
     */
    private void initClipboardContent(ListView listView) {
        Map<String, String> content = new LinkedHashMap<>();
        config.onClipboardContentCreated(content);

        List<ContentHolder> list = new ArrayList<>(content.size());
        for (Map.Entry<String, String> entry : content.entrySet()) {
            list.add(new ContentItem(entry.getKey(), entry.getValue()));
        }
        ContentAdapter adapter = new ContentAdapter(context, list);
        listView.setAdapter(adapter);
    }

    /**
     * It is called when the log content is created
     *
     * @param listView is required
     */
    private void initLogContent(ListView listView) {
        List<ContentHolder> list = BeeLog.getLogHistory();
        Collections.reverse(list);
        ContentAdapter adapter = new ContentAdapter(context, list);
        listView.setAdapter(adapter);
    }

    /**
     * It is called when the information content is created
     *
     * @param listView is required
     */
    private void initInfoContent(ListView listView) {
        Map<String, String> infoContent = createInfoContent();
        config.onInfoContentCreated(infoContent);

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
        view.findViewById(R.id.close_button).setOnClickListener(this);
        view.findViewById(R.id.save_button).setOnClickListener(this);
        view.findViewById(R.id.menu_button).setOnClickListener(this);
        view.findViewById(R.id.info_button).setOnClickListener(this);
        view.findViewById(R.id.log_button).setOnClickListener(this);
        view.findViewById(R.id.clipboard_button).setOnClickListener(this);
    }

    /**
     * This is the title that user sees for each container
     *
     * @param resourceId
     */
    private void setTitle(int resourceId) {
        titleTextView.setText(resourceId);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.save_button) {
            config.onSave();
            return;
        }
        if (id == R.id.close_button) {
            mainContainer.setVisibility(View.GONE);
            beeImageView.setVisibility(View.VISIBLE);
            config.onClose();
            return;
        }
        if (id == R.id.menu_button) {
            menuScrollContainer.setVisibility(View.VISIBLE);
            logListView.setVisibility(View.GONE);
            infoListView.setVisibility(View.GONE);
            clipboardListView.setVisibility(View.GONE);
            setTitle(R.string.settings);
            return;
        }
        if (id == R.id.info_button) {
            menuScrollContainer.setVisibility(View.GONE);
            logListView.setVisibility(View.GONE);
            infoListView.setVisibility(View.VISIBLE);
            clipboardListView.setVisibility(View.GONE);
            setTitle(R.string.info);
            return;
        }
        if (id == R.id.log_button) {
            menuScrollContainer.setVisibility(View.GONE);
            logListView.setVisibility(View.VISIBLE);
            infoListView.setVisibility(View.GONE);
            clipboardListView.setVisibility(View.GONE);
            setTitle(R.string.log);
            return;
        }
        if (id == R.id.clipboard_button) {
            menuScrollContainer.setVisibility(View.GONE);
            logListView.setVisibility(View.GONE);
            infoListView.setVisibility(View.GONE);
            clipboardListView.setVisibility(View.VISIBLE);
            setTitle(R.string.clipboard);
        }
    }

    private void initMenuContent(ViewGroup container) {
        for (ViewHolder viewHolder : viewHolderList) {
            createRow(container, viewHolder.getTitle(), viewHolder.getView());
        }
    }

    /**
     * Creates a row for the mainContainer
     *
     * @param container Parent view group
     * @param title     to show in drawer
     * @param view      to add next to title
     */
    private void createRow(ViewGroup container, String title, View view) {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1
        );
        TableRow row = new TableRow(context);
        TextView textView = new TextView(context);
        textView.setLayoutParams(params);
        textView.setText(title);
        textView.setTextColor(Color.BLACK);
        view.setLayoutParams(params);
        row.addView(textView);
        row.addView(view);
        container.addView(row);
    }

    private void setBeeButton(ViewGroup rootView) {
        beeImageView.setImageResource(R.drawable.bee);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                DRAWER_BUTTON_WIDTH,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL | Gravity.END
        );
        beeImageView.setLayoutParams(params);
        beeImageView.setOnClickListener(null);

        beeImageView.setOnTouchListener(onTouchListener);
        rootView.addView(beeImageView);
    }

    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        final BeeGestureListener gestureListener = new BeeGestureListener();
        final GestureDetector gestureDetector = new GestureDetector(context, gestureListener);

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (gestureDetector.onTouchEvent(event)) {
                return true;
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    int x = (int) event.getRawX();
                    int y = (int) event.getRawY();
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
                    params.topMargin = y - v.getHeight() / 2;
                    params.leftMargin = x - v.getWidth() / 2;
                    params.gravity = Gravity.NO_GRAVITY;
                    v.setLayoutParams(params);
                    return true;
            }
            return false;
        }
    };

    /**
     * If the user single tap, it will open the bee, if the user moves the bee, nothing will happen but movement
     */
    private class BeeGestureListener implements GestureDetector.OnGestureListener {

        private final String TAG = BeeGestureListener.class.getSimpleName();

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            mainContainer.setVisibility(View.VISIBLE);
            beeImageView.setVisibility(View.GONE);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    /**
     * This builder must be called to inject preferences.
     * with, to and build must be set
     */
    public static class Builder {

        private Context context;
        private BeeConfigListener config;
        private final List<ViewHolder> holderList = new ArrayList<>();

        //TODO: context check should be done because now we assume it is an Activity
        public Builder with(Context context) {
            if (context == null) {
                throw new NullPointerException("Context may not be null");
            }
            this.context = context;
            return this;
        }

        public Builder to(BeeConfigListener config) {
            if (config == null) {
                throw new NullPointerException("Config may not be null");
            }
            this.config = config;
            return this;
        }

        public Builder addSpinner(String title, String[] list, int requestCode) {
            if (this.context == null) {
                throw new NullPointerException("Set context first");
            }
            if (this.config == null) {
                throw new NullPointerException("Set config first");
            }
            if (list == null || list.length == 0) {
                throw new IllegalArgumentException("List should not be empty or null");
            }
            this.holderList.add(
                    new SpinnerViewHolder.Builder()
                            .with(this.context)
                            .from(requestCode)
                            .to(this.config)
                            .setList(list)
                            .setTitle(title)
                            .build()
            );
            return this;
        }

        public Builder addCheckbox(String title, int requestCode) {
            if (this.context == null) {
                throw new NullPointerException("Set context first");
            }
            if (this.config == null) {
                throw new NullPointerException("Set config first");
            }
            this.holderList.add(
                    new CheckboxViewHolder.Builder()
                            .with(this.context)
                            .from(requestCode)
                            .to(this.config)
                            .setTitle(title)
                            .build()
            );
            return this;
        }

        public Bee build() {
            return new Bee(this);
        }
    }
}
