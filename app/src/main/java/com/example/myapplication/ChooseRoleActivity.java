package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityChooseRoleBinding;

public class ChooseRoleActivity extends AppCompatActivity {

    private ActivityChooseRoleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityChooseRoleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String password = getIntent().getStringExtra("password");

        binding.cardShopOwner.setChecked(true);
        binding.cardShopOwner.setOnClickListener(v -> {
            binding.cardShopOwner.setChecked(true);
            binding.cardArtisan.setChecked(false);
        });

        binding.cardArtisan.setOnClickListener(v -> {
            binding.cardArtisan.setChecked(true);
            binding.cardShopOwner.setChecked(false);
        });

        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnContinue.setOnClickListener(v -> {
            Intent intent;

            if (binding.cardShopOwner.isChecked()) {
                intent = new Intent(ChooseRoleActivity.this, ShopOwnerSignUpActivity.class);
            } else {
                intent = new Intent(ChooseRoleActivity.this, ArtisanSignUpActivity.class);
            }
            intent.putExtra("firstName", firstName);
            intent.putExtra("lastName", lastName);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("password", password);

            startActivity(intent);
        });
    }
}
