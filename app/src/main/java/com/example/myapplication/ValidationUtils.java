package com.example.myapplication;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class ValidationUtils {

    /**
     * Checks if email is non-null, non-empty, and matches valid email format.
     */
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Checks if password is non-null and at least 8 characters long.
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    /**
     * Checks if two passwords match.
     */
    public static boolean doPasswordsMatch(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }

    /**
     * Checks if phone number is non-null, 8 digits long, and contains only numbers.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{8}");
    }

    /**
     * Checks if name contains letters, spaces, hyphens, or apostrophes (at least 2 chars).
     */
    public static boolean isValidName(String name) {
        return name != null && name.trim().matches("^[a-zA-Z\\s'-]{2,}$");
    }

    /**
     * Checks if text is not null and not blank (ignoring leading/trailing whitespace).
     */
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }


    public static boolean checkField(EditText editText, boolean isValid, String errorMsg) {
        if (!isValid) {
            editText.setError(errorMsg);
            editText.requestFocus(); // Focuses every invalid field sequentially
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }
    public static String getSelectedChips(ChipGroup chipGroup) {
        StringBuilder selectedItems = new StringBuilder();
        for (int id : chipGroup.getCheckedChipIds()) {
            Chip chip = chipGroup.findViewById(id);
            if (chip != null) {
                if (selectedItems.length() > 0) {
                    selectedItems.append(", ");
                }
                selectedItems.append(chip.getText());
            }
        }
        return selectedItems.toString();
    }
}