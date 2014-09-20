package com.jurgiles.sdk.droidcon2014.app;


import android.widget.TextView;
import org.fest.assertions.api.ANDROID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldCreateActivityWithHelloText() {
        ActivityController<MainActivity> controller = ActivityController.of(MainActivity.class);
        MainActivity activity = controller.create().get();

        TextView helloDroidconView = (TextView) activity.findViewById(R.id.hello_droidcon_text);

        ANDROID.assertThat(helloDroidconView).containsText("Hello Droidcon!");
    }

    @Test
    public void shouldChangeTextWhenButtonPressed() {
        MainActivity activity = ActivityController.of(MainActivity.class).create().visible().get();

        Robolectric.clickOn(activity.findViewById(R.id.press_me_button));

        TextView helloDroidconView = (TextView) activity.findViewById(R.id.hello_droidcon_text);
        ANDROID.assertThat(helloDroidconView).containsText("Dance Dance Dance");
    }
    
    @Test
    public void shouldCloseActivityWhenButtonIsPressed() {
        MainActivity activity = ActivityController.of(MainActivity.class).create().visible().get();

        Robolectric.clickOn(activity.findViewById(R.id.big_red_button));

        ANDROID.assertThat(activity).isFinishing();
    }
}