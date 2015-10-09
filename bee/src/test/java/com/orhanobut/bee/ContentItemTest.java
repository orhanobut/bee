package com.orhanobut.bee;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentItemTest {

  @Test public void getTitle() throws Exception {
    ContentItem item = new ContentItem("title", "value");

    assertThat(item.getTitle()).isEqualTo("title");
  }

  @Test public void getValue() throws Exception {
    ContentItem item = new ContentItem("title", "value");

    assertThat(item.getValue()).isEqualTo("value");
  }
}