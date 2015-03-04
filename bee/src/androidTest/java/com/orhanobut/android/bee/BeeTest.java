package com.orhanobut.android.bee;

import com.orhanobut.bee.Bee;
import com.orhanobut.bee.BeeConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Orhan Obut
 */
public class BeeTest extends BaseTest {


    public void testInjectContextAndClazzNull() {
        try {
            Bee.inject(null, null);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage());
        }
    }

    public void testInjectContextNull() {
        try {
            Bee.inject(null, BeeConfig.class);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage());
        }
    }

    public void testInjectClazzNull() {
        try {
            Bee.inject(context, null);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage());
        }
    }

}
