package com.jurgiles.sdk.droidcon2014.app;

import android.app.Activity;
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
    }
}
