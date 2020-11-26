package com.kuil.KuilLottery.acitvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.kuil.KuilLottery.R;

public class ThirdActivity extends AppCompatActivity {
    LinearLayout lastlear,linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().hide();
        lastlear=findViewById(R.id.lastlear);
        linearlayout=findViewById(R.id.linearlayout);
        lastlear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this,MainActivity.class));
            }
        });
        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, BookGameActivity.class));
            }
        });

    }



}
