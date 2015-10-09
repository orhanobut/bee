package com.orhanobut.bee;

final class ContentItem implements ContentHolder {

  private final String title;
  private final String value;

  ContentItem(String title, String value) {
    this.title = title;
    this.value = value;
  }

  @Override public String getTitle() {
    return title;
  }

  @Override public String getValue() {
    return value;
  }
}
