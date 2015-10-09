package com.orhanobut.bee;

import com.orhanobut.bee.widgets.Button;
import com.orhanobut.bee.widgets.CheckBox;
import com.orhanobut.bee.widgets.Spinner;
import com.orhanobut.bee.widgets.Title;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodInfoTest {

  @Test public void newInstance() throws Exception {
    class MyConfig {
      @Title("Reset") @Button public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();
    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], null);

    assertThat(methodInfo).isNotNull();
  }

  @Test public void getMethod() throws Exception {
    class MyConfig {
      @Title("Reset") @Button public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();

    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], null);

    assertThat(methodInfo.getMethod()).isEqualTo(methods[0]);
  }

  @Test public void getViewTypeShouldReturnButton() throws Exception {
    class MyConfig {
      @Title("Reset") @Button public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();

    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], instance);

    assertThat(methodInfo.getViewType()).isEqualTo(MethodInfo.VIEW_BUTTON);
  }

  @Test public void getViewTypeShouldReturnCheckBox() throws Exception {
    class MyConfig {
      @Title("Reset") @CheckBox public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();

    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], instance);

    assertThat(methodInfo.getViewType()).isEqualTo(MethodInfo.VIEW_CHECKBOX);
  }

  @Test public void getViewTypeShouldReturnSpinner() throws Exception {
    class MyConfig {
      @Title("Reset") @Spinner("one") public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();

    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], instance);

    assertThat(methodInfo.getViewType()).isEqualTo(MethodInfo.VIEW_SPINNER);
  }

  @Test public void getData() throws Exception {
    class MyConfig {
      @Title("Reset") @Spinner({"one"}) public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();
    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], instance);

    assertThat(methodInfo.getData()).isNotNull();
    assertThat(methodInfo.getData()).isInstanceOf(String[].class);

    String[] values = (String[]) methodInfo.getData();
    assertThat(values).hasSize(1);
    assertThat(values[0]).isEqualTo("one");
  }

  @Test public void getTitle() throws Exception {
    class MyConfig {
      @Title("title")
      @Spinner({"one"})
      public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();
    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], instance);

    assertThat(methodInfo.getTitle()).isEqualTo("title");
  }

  @Test public void getInstance() throws Exception {
    class MyConfig {
      @Title("title")
      @Spinner({"one"})
      public void foo() {
      }
    }

    Object instance = new MyConfig();
    Method[] methods = instance.getClass().getDeclaredMethods();
    MethodInfo methodInfo = MethodInfo.newInstance(methods[0], instance);

    assertThat(methodInfo.getInstance()).isEqualTo(instance);
  }
}