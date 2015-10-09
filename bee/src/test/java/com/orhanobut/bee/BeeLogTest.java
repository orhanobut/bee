package com.orhanobut.bee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class BeeLogTest {

  @Test public void getLogHistory() throws Exception {
    BeeLog.d("d", "message");
    BeeLog.e("e", "message");
    BeeLog.i("i", "message");
    BeeLog.w("w", "message");

    List<ContentHolder> logHistories = BeeLog.getLogHistory();

    assertThat(logHistories).hasSize(4);
    assertThat(logHistories.get(0).getValue()).isEqualTo("d: message");
  }
}