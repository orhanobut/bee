package com.orhanobut.bee;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(manifest = Config.NONE, constants = BuildConfig.class, sdk = 21)
public class ContentAdapterTest {

  private Context context;
  private List<ContentHolder> list = new ArrayList<>();

  @Before public void setup() {
    context = Robolectric.buildActivity(Activity.class).create().get();
    list.add(new ContentHolder() {
      @Override public String getTitle() {
        return "title1";
      }

      @Override public String getValue() {
        return "value1";
      }
    });

    list.add(new ContentHolder() {
      @Override public String getTitle() {
        return "title2";
      }

      @Override public String getValue() {
        return "value2";
      }
    });
  }

  @Test public void getCount() throws Exception {
    ContentAdapter contentAdapter = new ContentAdapter(context, list);

    assertThat(contentAdapter.getCount()).isEqualTo(list.size());
  }

  @Test public void getItem() throws Exception {
    ContentAdapter contentAdapter = new ContentAdapter(context, list);

    assertThat(contentAdapter.getItem(0).getTitle()).isEqualTo("title1");
    assertThat(contentAdapter.getItem(1).getTitle()).isEqualTo("title2");
  }

  @Test public void getItemId() throws Exception {
    ContentAdapter contentAdapter = new ContentAdapter(context, list);

    assertThat(contentAdapter.getItemId(0)).isEqualTo(0);
    assertThat(contentAdapter.getItemId(1)).isEqualTo(1);
  }

  @Test public void getViewShouldNotReturnNull() throws Exception {
    ContentAdapter contentAdapter = new ContentAdapter(context, list);
    View view = contentAdapter.getView(0, null, new LinearLayout(context));

    assertThat(view).isNotNull();
  }

  @Test public void getViewShouldHaveContentHolderInTag() throws Exception {
    ContentAdapter contentAdapter = new ContentAdapter(context, list);
    View view = contentAdapter.getView(0, null, new LinearLayout(context));

    ContentAdapter.Holder holder = (ContentAdapter.Holder) view.getTag();

    assertThat(holder).isNotNull();
    assertThat(holder.title.getText()).isEqualTo("title1");
    assertThat(holder.value.getText()).isEqualTo("value1");
  }
}