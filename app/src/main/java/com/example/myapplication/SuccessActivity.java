package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        String role = getIntent().getStringExtra("role");
        TextView tvSubtitle = findViewById(R.id.tvSuccessSubtitle);

        if ("SHOP_OWNER".equals(role)) {
            tvSubtitle.setText("Welcome to Souk Hirfa.\nYour shop is ready.\nDiscover artisans near you.");
        } else {
            tvSubtitle.setText("Welcome to Souk Hirfa.\nYour artisan profile is ready.\nStart showcasing your crafts.");
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SuccessActivity.this, HomeActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}