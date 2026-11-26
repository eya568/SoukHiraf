package com.example.myapplication;

import static com.example.myapplication.ValidationUtils.checkField;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(
                binding.main,
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        binding.tvCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SignInActivity.this,
                    SignUpStep1Activity.class
            );
            startActivity(intent);
        });

        binding.btnSignIn.setOnClickListener(v -> {

            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString();

            boolean isEmailValid = checkField(
                    binding.etEmail,
                    ValidationUtils.isValidEmail(email),
                    "Enter a valid email"
            );

            boolean isPasswordValid = checkField(
                    binding.etPassword,
                    !password.isEmpty(),
                    "Password is required"
            );

            if (!isEmailValid || !isPasswordValid) {
                return;
            }

            DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);

            boolean isValidUser = dbHelper.checkUser(email, password);

            if (isValidUser) {

                Intent intent = new Intent(
                        SignInActivity.this,
                        HomeActivity.class
                );

                intent.setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                );

                startActivity(intent);
                finish();

            } else {
                Toast.makeText(
                        this,
                        "Invalid email or password",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

}