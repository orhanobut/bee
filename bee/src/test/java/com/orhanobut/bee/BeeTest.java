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
@Config(sdk = 21, constants = BuildConfig.class)
public class BeeTest {

  private Context context;

  static class CustomConfig extends BeeConfig {
  }

  @Before public void setup() {
    context = Robolectric.buildActivity(Activity.class).create().get();
  }

  @Test public void initShouldNotAcceptNull() throws Exception {
    try {
      Bee.init(null);
    } catch (Exception e) {
      assertThat(e).hasMessage("context should not be null");
    }
  }

  @Test public void inject() throws Exception {
    Bee.init(context).inject(SettingsTest.CustomConfig.class);
  }

  @Test public void injectShouldNotAcceptNull() throws Exception {
    try {
      Bee.init(context).inject(null);
    } catch (Exception e) {
      assertThat(e).hasMessage("config should not be null");
    }
  }
}