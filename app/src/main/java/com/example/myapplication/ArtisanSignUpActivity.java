package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityArtisanSignUpBinding;


public class ArtisanSignUpActivity extends AppCompatActivity {
    private ActivityArtisanSignUpBinding binding;
    private String firstName, lastName, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtisanSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) ->   {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");

        binding.btnBackArtisan.setOnClickListener(v -> finish());
        binding.btnContinueArtisan.setOnClickListener(v -> saveArtisan());
    }

    private void saveArtisan() {
        String brandName = binding.etBrandName.getText().toString().trim();
        String businessType = binding.etBusinessType.getText().toString().trim();
        String bio = binding.etBrandBio.getText().toString().trim();

        String selectedMaking = ValidationUtils.getSelectedChips(
                binding.chipGroupMaking
        );

        // Validate text fields
        boolean isBrandNameValid = ValidationUtils.checkField(
                binding.etBrandName,
                ValidationUtils.isValidText(brandName),
                "Brand name is required"
        );

        boolean isBusinessTypeValid = ValidationUtils.checkField(
                binding.etBusinessType,
                ValidationUtils.isValidText(businessType),
                "Business type is required"
        );

        boolean isBioValid = ValidationUtils.checkField(
                binding.etBrandBio,
                ValidationUtils.isValidText(bio),
                "Please write a short bio"
        );

        if (!isBrandNameValid || !isBusinessTypeValid || !isBioValid) {
            return;
        }

        if (selectedMaking.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please select at least one category",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        // Insert user into database
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);

        boolean isInserted = dbHelper.insertUser(
                email, password, "ARTISAN", firstName, lastName, phone,
                brandName, businessType, selectedMaking, bio
        );

        if (isInserted) {
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.createLoginSession(email, "ARTISAN");
            Toast.makeText(
                    this,
                    "Account created successfully!",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(
                    ArtisanSignUpActivity.this,
                    SuccessActivity.class
            );

            intent.putExtra("role", "ARTISAN");
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);
            finish();

        } else {
            Toast.makeText(
                    this,
                    "Error creating account.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }



}