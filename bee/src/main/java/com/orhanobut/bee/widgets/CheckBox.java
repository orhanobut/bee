package com.orhanobut.bee.widgets;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * It is used to add checkbox to the settings menu
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface CheckBox {
}
