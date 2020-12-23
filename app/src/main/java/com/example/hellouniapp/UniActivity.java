package com.example.hellouniapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.dcloud.feature.sdk.DCUniMPSDK;

public class UniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySplashView mySplashView=new MySplashView();

        try {
            DCUniMPSDK.getInstance().startApp(UniActivity.this, "__UNI__B92044B", MySplashView.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(mySplashView.splashView);
    }
}