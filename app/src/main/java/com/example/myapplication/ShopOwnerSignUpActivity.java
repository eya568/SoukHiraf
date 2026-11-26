package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import static com.example.myapplication.ValidationUtils.getSelectedChips;

import com.example.myapplication.databinding.ActivityShopOwnerSignUpBinding;


public class ShopOwnerSignUpActivity extends AppCompatActivity {
    private ActivityShopOwnerSignUpBinding binding;
    private String firstName, lastName, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopOwnerSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.btnBackOwner.setOnClickListener(v -> finish());
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        password = getIntent().getStringExtra("password");
        firstName = getIntent().getStringExtra("firstName");
        lastName = getIntent().getStringExtra("lastName");

        binding.btnContinueOwner.setOnClickListener(v -> saveShopOwner());


    }

    private void saveShopOwner() {
        String shopName = binding.etBusinessName.getText().toString().trim();
        String businessType = binding.etBusinessType.getText().toString().trim();
        String bio = binding.etShopBio.getText().toString().trim();
        String selectedSourcing = getSelectedChips(binding.chipGroupSourcing);

        boolean isShopNameValid = ValidationUtils.checkField(
                binding.etBusinessName,
                ValidationUtils.isValidText(shopName),
                "Shop name is required"
        );

        boolean isBusinessTypeValid = ValidationUtils.checkField(
                binding.etBusinessType,
                ValidationUtils.isValidText(businessType),
                "Business type is required"
        );

        boolean isBioValid = ValidationUtils.checkField(
                binding.etShopBio,
                ValidationUtils.isValidText(bio),
                "Please write a short bio"
        );

        if (!isShopNameValid || !isBusinessTypeValid || !isBioValid) {
            return;
        }

        if (selectedSourcing.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please select at least one sourcing category",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);

        boolean isInserted = dbHelper.insertUser(
                email, password, "SHOP_OWNER", firstName, lastName, phone,
                shopName, businessType, selectedSourcing, bio
        );

        if (isInserted) {
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.createLoginSession(email, "SHOP_OWNER");
            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    ShopOwnerSignUpActivity.this,
                    SuccessActivity.class
            );

            intent.putExtra("role", "SHOP_OWNER");
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);
            finish();

        } else {
            Toast.makeText(
                    this,
                    "Error: Email might already exist.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    }
