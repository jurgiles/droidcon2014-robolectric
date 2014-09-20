package com.jurgiles.sdk.droidcon2014.app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.press_me_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView helloDroidconText = (TextView) findViewById(R.id.hello_droidcon_text);
                helloDroidconText.setText("Dance Dance Dance");
            }
        });

        findViewById(R.id.big_red_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, BrandNewActivity.class));

                MainActivity.this.finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText("Unce Unce Unce")
                .setContentTitle("Dance Party")
                .build();

        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(123, notification);
    }
}
