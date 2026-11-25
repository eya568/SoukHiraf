package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class ChooseRoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_role);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MaterialCardView cardOwner = findViewById(R.id.cardOwner);
        MaterialCardView cardArtisan = findViewById(R.id.cardArtisan);

        cardOwner.setOnClickListener(v -> {
            cardOwner.setChecked(true);
            cardArtisan.setChecked(false);
        });

        cardArtisan.setOnClickListener(v -> {
            cardArtisan.setChecked(true);
            cardOwner.setChecked(false);
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnContinue).setOnClickListener(v -> {
            String role = cardOwner.isChecked() ? "owner" : "artisan";
           // Intent intent = new Intent(this, NextActivity.class); 
            //intent.putExtra("role", role);
            //startActivity(intent);
        });
    }
}