package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityLoadingScreenBinding;

public class LoadingScreen extends AppCompatActivity {
    private ActivityLoadingScreenBinding binding;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoadingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        runnable = () -> {
            SessionManager sessionManager = new SessionManager(LoadingScreen.this);
            Intent intent;
            if (sessionManager.isLoggedIn()) {
                intent = new Intent(LoadingScreen.this, HomeActivity.class);
            } else {
                intent = new Intent(LoadingScreen.this, WelcomeActivity.class);
            }
            startActivity(intent);
            finish();
        };
        handler.postDelayed(runnable, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
