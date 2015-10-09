package com.orhanobut.bee;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SettingsTest {

  private Context context;

  @Before public void setup() {
    context = Robolectric.buildActivity(Activity.class).create().get();
  }

  @Test public void setBeePosition() throws Exception {
    Settings settings = new Settings(context);
    settings.setBeePosition(Gravity.NO_GRAVITY);

    assertThat(settings.getGravity()).isEqualTo(Gravity.NO_GRAVITY);
  }

  @Test public void setBeeMargin() throws Exception {
    Settings settings = new Settings(context);
    settings.setBeeMargin(1, 2, 3, 4);

    assertThat(settings.getBeeMargin()[0]).isEqualTo(1);
    assertThat(settings.getBeeMargin()[1]).isEqualTo(2);
    assertThat(settings.getBeeMargin()[2]).isEqualTo(3);
    assertThat(settings.getBeeMargin()[3]).isEqualTo(4);
  }

  @Test public void setBeeSize() throws Exception {
    Settings settings = new Settings(context);
    settings.setBeeSize(100);

    assertThat(settings.getBeeSize()).isEqualTo(100);
  }

  @Test public void getContext() throws Exception {
    Settings settings = new Settings(context);

    assertThat(settings.getContext()).isEqualTo(context);
  }

  static class CustomConfig extends BeeConfig {
  }
}