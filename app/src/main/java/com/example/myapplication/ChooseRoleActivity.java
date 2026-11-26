
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
                String firstName = getIntent().getStringExtra("firstName");
                String lastName = getIntent().getStringExtra("lastName");
                String email = getIntent().getStringExtra("email");
                String phone = getIntent().getStringExtra("phone");
                String password = getIntent().getStringExtra("password");
                MaterialCardView cardOwner = findViewById(R.id.cardShopOwner);
                MaterialCardView cardArtisan = findViewById(R.id.cardArtisan);
                cardOwner.setChecked(true);
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
                    Intent intent;

                    if (cardOwner.isChecked()) {
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

