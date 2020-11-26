package com.kuil.KuilLottery.acitvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.kuil.KuilLottery.R;
import com.kuil.KuilLottery.session.SessonManager;

public class SplashActivity extends AppCompatActivity {

    SessonManager sessonManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        sessonManager = new SessonManager(SplashActivity.this);
        String token = sessonManager.getToken();

        if(token.isEmpty()){
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            finishAffinity();
        }else {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finishAffinity();
        }
    }
}