package com.rasenkai.library;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    SQLiteDatabase loginDB;
    EditText emailET;
    EditText passET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginDB = openOrCreateDatabase("reg.sql", 0, null);
    }

    public boolean validate(String value, String key) {
        if (value.equals("")) {
            Toast.makeText(this, "enter a valid " + key, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void login(View v) {
        emailET = findViewById(R.id.email);
        passET = findViewById(R.id.password);

        String email = emailET.getText().toString();
        String pass = passET.getText().toString();

        Boolean isValidEmail = validate(email, " email.");
        Boolean isValidPassword = validate(pass, " password.");

        if (isValidEmail && isValidPassword) {
            String sql = "select count(*) from faculty " + "where email = '" + email + "' and pass ='" + pass + "';";
            Cursor result = loginDB.rawQuery(sql, null);
            result.moveToNext();
            if (result.getString(0).equals("0")) {
                Intent i = new Intent(this, Home.class);
                startActivity(i);
            } else {
                Intent i = new Intent(this, Home.class);
                startActivity(i);
            }
        }
    }
}