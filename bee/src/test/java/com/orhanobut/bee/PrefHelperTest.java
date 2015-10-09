package com.orhanobut.bee;

import android.app.Activity;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PrefHelperTest {

  private Context context;

  @Before public void setup() {
    context = Robolectric.buildActivity(Activity.class).create().get();
  }

  @Test public void testBoolean() throws Exception {
    PrefHelper.setBoolean(context, "key", true);

    assertThat(PrefHelper.getBoolean(context, "key")).isTrue();
  }

  @Test public void testInt() throws Exception {
    PrefHelper.setInt(context, "key", 1);

    assertThat(PrefHelper.getInt(context, "key")).isEqualTo(1);
  }
}