package com.jurgiles.sdk.droidcon2014.app;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import org.fest.assertions.api.ANDROID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowNotification;
import org.robolectric.util.ActivityController;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.fest.assertions.api.Assertions.assertThat;

@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldCreateActivityWithHelloTextWithoutFEST() {
        ActivityController<MainActivity> controller = ActivityController.of(MainActivity.class);
        MainActivity activity = controller.create().get();

        TextView helloDroidconView = (TextView) activity.findViewById(R.id.hello_droidcon_text);

        assertTrue("Hello Droidcon!".equals(helloDroidconView.getText()));
    }

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

    @Test
    public void shouldLaunchNewActivityWhenBigRedButtonPressed() {
        MainActivity activity = ActivityController.of(MainActivity.class).create().visible().get();

        Robolectric.clickOn(activity.findViewById(R.id.big_red_button));

        Intent nextStartedActivity = Robolectric.getShadowApplication().getNextStartedActivity();
        ANDROID.assertThat(nextStartedActivity).hasComponent(Robolectric.application, BrandNewActivity.class);
    }

    @Test
    public void shouldLaunchWelcomeNotificationOnStart() {
        ActivityController.of(MainActivity.class).create().visible().start();

        NotificationManager notificationManager = (NotificationManager) Robolectric.application.getSystemService(Context.NOTIFICATION_SERVICE);
        List<Notification> allNotifications = Robolectric.shadowOf(notificationManager).getAllNotifications();

        assertThat(allNotifications).hasSize(1);
    }

    @Test
    public void shouldLaunchWelcomeNotificationOnStartWithStuff() {
        ActivityController.of(MainActivity.class).create().visible().start().get();

        NotificationManager notificationManager = (NotificationManager) Robolectric.application.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = Robolectric.shadowOf(notificationManager).getNotification(null, 123);

        ShadowNotification shadowNotification = Robolectric.shadowOf(notification);
        assertThat(shadowNotification.getContentTitle()).isEqualTo("Dance Party");
        assertThat(shadowNotification.getContentText()).isEqualTo("Unce Unce Unce");
    }

    @Test
    public void shouldKickoffAsyncTaskOnStart() {
        Robolectric.getBackgroundScheduler().pause();

        ActivityController.of(MainActivity.class).create().visible().start().get();

        assertEquals(Robolectric.getBackgroundScheduler().enqueuedTaskCount(), 1);

        Robolectric.runBackgroundTasks();

        assertThat(Robolectric.getBackgroundScheduler().size()).isEqualTo(0);
    }
}