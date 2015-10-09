package com.orhanobut.bee;

import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class LogHistoryTest {

  @Test public void getTitle() throws Exception {
    Date date = new Date();
    LogHistory logHistory = new LogHistory("tag", "message", date);

    assertThat(logHistory.getTitle()).isEqualTo(date.toString());
  }

  @Test public void getValue() throws Exception {
    Date date = new Date();
    LogHistory logHistory = new LogHistory("tag", "message", date);

    assertThat(logHistory.getValue()).isEqualTo("tag: message");
  }
}