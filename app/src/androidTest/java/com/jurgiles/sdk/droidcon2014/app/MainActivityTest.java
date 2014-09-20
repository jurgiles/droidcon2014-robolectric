package com.jurgiles.sdk.droidcon2014.app;


import android.widget.TextView;
import org.fest.assertions.api.ANDROID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldCreateActivityWithHelloText() {
        ActivityController<MainActivity> controller = ActivityController.of(MainActivity.class);
        MainActivity mainActivity = controller.create().get();

        TextView helloDroidconView = (TextView) mainActivity.findViewById(R.id.hello_droidcon_text);

        ANDROID.assertThat(helloDroidconView).containsText("Hello Droidcon!");
    }
}