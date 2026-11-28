package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivitySuccessBinding;

public class SuccessActivity extends AppCompatActivity {

    private ActivitySuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String role = getIntent().getStringExtra("role");

        if ("SHOP_OWNER".equals(role)) {
            binding.tvSuccessSubtitle.setText("Welcome to Souk Hirfa.\nYour shop is ready.\nDiscover artisans near you.");
        } else {
            binding.tvSuccessSubtitle.setText("Welcome to Souk Hirfa.\nYour artisan profile is ready.\nStart showcasing your crafts.");
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
