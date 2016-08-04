package com.developer.jcdc.mytips;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        String id = Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        Toast.makeText(Splash.this, id, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashMain = new Intent(Splash.this, MainActivity.class);
                startActivity(SplashMain);
                finish();
            }
        }, 3000);
    }
}

