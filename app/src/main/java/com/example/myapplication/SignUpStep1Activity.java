package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivitySignUpStep1Binding;

public class SignUpStep1Activity extends AppCompatActivity {

    private ActivitySignUpStep1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySignUpStep1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Button btnContinue;
        TextView tvSignIn;
        btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(v -> {
            String firstName = ((EditText) findViewById(R.id.etFirstName)).getText().toString();
            String lastName = ((EditText) findViewById(R.id.etLastName)).getText().toString();
            String email = ((EditText) findViewById(R.id.etEmail)).getText().toString();
            String phone = ((EditText) findViewById(R.id.etPhone)).getText().toString();
            String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
            String confirmPassword = ((EditText) findViewById(R.id.etConfirmPassword)).getText().toString();
            boolean isFirstNameValid = checkField(binding.etFirstName, ValidationUtils.isValidName(firstName), "Enter a valid first name");
            boolean isLastNameValid = checkField(binding.etLastName, ValidationUtils.isValidName(lastName), "Enter a valid last name");
            boolean isEmailValid = checkField(binding.etEmail, ValidationUtils.isValidEmail(email), "Enter a valid email");
            boolean isPhoneValid = checkField(binding.etPhone, ValidationUtils.isValidPhoneNumber(phone), "Enter a valid 8-digit phone number");
            boolean isPasswordValid = checkField(binding.etPassword, ValidationUtils.isValidPassword(password), "Password must be at least 8 characters");
            boolean isConfirmPasswordValid = checkField(binding.etConfirmPassword, ValidationUtils.doPasswordsMatch(password, confirmPassword), "Passwords do not match");
            if (!isFirstNameValid || !isLastNameValid ||
                    !isEmailValid || !isPhoneValid ||
                    !isPasswordValid || !isConfirmPasswordValid ) {
                return;
            }
            Intent intent = new Intent(SignUpStep1Activity.this, ChooseRoleActivity.class);
            startActivity(intent);
        });

        tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpStep1Activity.this, SignInActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean checkField(EditText editText, boolean isValid, String errorMsg) {
        if (!isValid) {
            editText.setError(errorMsg);
            editText.requestFocus(); // Puts the cursor in the field
            return false;
        } else {
            editText.setError(null); // Clears the error
            return true;
        }
    }




}