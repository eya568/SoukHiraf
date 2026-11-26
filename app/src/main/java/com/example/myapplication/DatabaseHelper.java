package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.mindrot.jbcrypt.BCrypt;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String TABLE_USERS = "users";

    // Column Names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_SHOP_NAME = "shop_name";
    private static final String COLUMN_BUSINESS_TYPE = "business_type";
    private static final String COLUMN_SOURCING = "sourcing";
    private static final String COLUMN_BIO = "bio";

    private SQLiteDatabase database;
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

        database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_SHOP_NAME + " TEXT, " +
                COLUMN_BUSINESS_TYPE + " TEXT, " +
                COLUMN_SOURCING + " TEXT, " +
                COLUMN_BIO + " TEXT);");
    }
    public boolean isEmailExists(String email) {
        Cursor cursor = database.rawQuery(
                "SELECT 1 FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?",
                new String[]{email}
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public boolean insertUser(String email, String password, String role, String firstName,
                              String lastName, String phone, String shopName,
                              String businessType, String sourcing, String bio) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);

        values.put(COLUMN_PASSWORD, BCrypt.hashpw(password, BCrypt.gensalt(12)));
        values.put(COLUMN_ROLE, role);
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_SHOP_NAME, shopName);
        values.put(COLUMN_BUSINESS_TYPE, businessType);
        values.put(COLUMN_SOURCING, sourcing);
        values.put(COLUMN_BIO, bio);

        long result = database.insert(TABLE_USERS, null, values);
        return result != -1;
    }
    public String getUserRole(String email) {
        Cursor cursor = database.rawQuery(
                "SELECT " + COLUMN_ROLE + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ?",
                new String[]{email}
        );
        try {
            if (cursor.moveToFirst()) {
                return cursor.getString(0);
            }
            return null;
        } finally {
            cursor.close();
        }
    }
    public boolean checkUser(String email, String password) {

        Cursor cursor = database.rawQuery(
                "SELECT " + COLUMN_PASSWORD +
                        " FROM " + TABLE_USERS +
                        " WHERE " + COLUMN_EMAIL + " = ?",
                new String[]{email}
        );

        try {
            if (cursor.moveToFirst()) {

                String storedHash = cursor.getString(0);

                return BCrypt.checkpw(password, storedHash);
            }

            return false;

        } finally {
            cursor.close();
        }
    }
}