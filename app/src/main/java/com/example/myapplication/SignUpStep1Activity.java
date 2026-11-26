package com.example.myapplication;

import static com.example.myapplication.ValidationUtils.checkField;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivitySignUpStep1Binding;

public class SignUpStep1Activity extends AppCompatActivity {

    private ActivitySignUpStep1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpStep1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnContinue.setOnClickListener(v -> {
            String firstName = binding.etFirstName.getText().toString().trim();
            String lastName = binding.etLastName.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String phone = binding.etPhone.getText().toString().trim();
            String password = binding.etPassword.getText().toString();
            String confirmPassword = binding.etConfirmPassword.getText().toString();

            boolean isFirstNameValid = checkField(binding.etFirstName, ValidationUtils.isValidName(firstName), "Enter a valid first name");
            boolean isLastNameValid = checkField(binding.etLastName, ValidationUtils.isValidName(lastName), "Enter a valid last name");
            boolean isEmailValid = checkField(binding.etEmail, ValidationUtils.isValidEmail(email), "Enter a valid email");

            if (isEmailValid && DatabaseHelper.getInstance(this).isEmailExists(email)) {
                binding.etEmail.setError("This email is already registered");
                binding.etEmail.requestFocus();
                return;
            }            boolean isPhoneValid = checkField(binding.etPhone, ValidationUtils.isValidPhoneNumber(phone), "Enter a valid 8-digit phone number");
            boolean isPasswordValid = checkField(binding.etPassword, ValidationUtils.isValidPassword(password), "Password must be at least 8 characters");
            boolean isConfirmPasswordValid = checkField(binding.etConfirmPassword, ValidationUtils.doPasswordsMatch(password, confirmPassword), "Passwords do not match");

            if (!isFirstNameValid || !isLastNameValid || !isEmailValid ||
                    !isPhoneValid || !isPasswordValid || !isConfirmPasswordValid) {
                return;
            }

            Intent intent = new Intent(SignUpStep1Activity.this, ChooseRoleActivity.class);
            intent.putExtra("firstName", firstName);
            intent.putExtra("lastName", lastName);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("password", password);
            startActivity(intent);
        });

        binding.tvSignIn.setOnClickListener(v ->
                startActivity(new Intent(SignUpStep1Activity.this, SignInActivity.class))
        );
    }


}