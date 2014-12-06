package com.orhanobut.android.bee;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Orhan Obut
 *         <p/>
 *         To use debugdrawer, builder must be called and appropriate methods must be called
 */
public final class Bee implements View.OnClickListener {

    private static final String TAG = Bee.class.getSimpleName();
    private static final int DRAWER_BUTTON_WIDTH = 100;

    private final List<Holder> holderList = new ArrayList<Holder>();

    private Context context;
    private BeeListener listener;
    private ViewGroup mainContainer;
    private ImageView beeImageView;
    private ViewGroup menuContainer;
    private ViewGroup logContainer;
    private ViewGroup infoContainer;

    private Bee() {
    }

    /**
     * Inject the debug drawer mainContainer and adds all preferences that are set
     */
    public void inject() {
        Activity activity = (Activity) context;
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.container, rootView, true);
        mainContainer = (ViewGroup) rootView.findViewById(R.id.main_container);
        menuContainer = (ViewGroup) mainContainer.findViewById(R.id.menu_container);
        logContainer = (ViewGroup) mainContainer.findViewById(R.id.log_container);
        infoContainer = (ViewGroup) mainContainer.findViewById(R.id.info_container);
        setBeeImageView(rootView);
        setView(menuContainer);

        // To not expose to outside
        rootView.findViewById(R.id.close_button).setOnClickListener(this);
        rootView.findViewById(R.id.save_button).setOnClickListener(this);
        rootView.findViewById(R.id.menu_button).setOnClickListener(this);
        rootView.findViewById(R.id.info_button).setOnClickListener(this);
        rootView.findViewById(R.id.log_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.save_button) {
            listener.onSave(context);
            return;
        }
        if (id == R.id.close_button) {
            mainContainer.setVisibility(View.GONE);
            beeImageView.setVisibility(View.VISIBLE);
            listener.onClose(context);
            return;
        }
        if (id == R.id.menu_button) {
            menuContainer.setVisibility(View.VISIBLE);
            logContainer.setVisibility(View.GONE);
            infoContainer.setVisibility(View.GONE);
            return;
        }
        if (id == R.id.info_button) {
            menuContainer.setVisibility(View.GONE);
            logContainer.setVisibility(View.GONE);
            infoContainer.setVisibility(View.VISIBLE);
            return;
        }
        if (id == R.id.log_button) {
            menuContainer.setVisibility(View.GONE);
            logContainer.setVisibility(View.VISIBLE);
            infoContainer.setVisibility(View.GONE);
        }
    }

    private void setView(ViewGroup container) {
        for (Holder holder : holderList) {
            createRow(container, holder.getTitle(), holder.getView());
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

    private void setBeeImageView(ViewGroup rootView) {
        beeImageView = new ImageView(context);
        beeImageView.setImageResource(R.drawable.bee);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                DRAWER_BUTTON_WIDTH,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER_VERTICAL | Gravity.RIGHT
        );
        beeImageView.setLayoutParams(params);
        beeImageView.setOnClickListener(null);

        beeImageView.setOnTouchListener(onTouchListener);
        rootView.addView(beeImageView);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        final BeeGestureListener gestureListener = new BeeGestureListener();
        final GestureDetector gestureDetector = new GestureDetector(Bee.this.context, gestureListener);

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
        private final Bee drawer = new Bee();

        //TODO: context check should be done because now we assume it is an Activity, let's think about that
        public Builder with(Context context) {
            drawer.context = context;
            return this;
        }

        public Builder to(BeeListener listener) {
            drawer.listener = listener;
            return this;
        }

        public Builder addSpinner(String title, String[] list, int requestCode) {
            drawer.holderList.add(
                    new SpinnerHolder.Builder()
                            .with(drawer.context)
                            .from(requestCode)
                            .to(drawer.listener)
                            .setList(list)
                            .setTitle(title)
                            .build()
            );
            return this;
        }

        public Bee build() {
            return drawer;
        }
    }
}
