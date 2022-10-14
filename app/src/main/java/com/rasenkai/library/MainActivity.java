package com.rasenkai.library;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button registerB;
    boolean isAllFieldsChecked = false;
    SQLiteDatabase registerDB;
    EditText fnameET;
    EditText emailEt;
    EditText confirm_passET;
    EditText passEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerB = findViewById(R.id.register_btn);
        fnameET = findViewById(R.id.full_name);
        emailEt = findViewById(R.id.email);
        confirm_passET = findViewById(R.id.password);
        passEt = findViewById(R.id.password2);

        registerDB = openOrCreateDatabase("reg.sql",MODE_PRIVATE,null);
        registerDB.execSQL("create table if not exists " + "faculty(name VARCHAR,email VARCHAR,confirm_pass VARCHAR,pass VARCHAR);");
    
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    Intent i = new Intent(MainActivity.this, login.class);
                    startActivity(i);
                }
            }
        });
    }

    public void register(View v) {
        fnameET = findViewById(R.id.full_name);
        emailEt = findViewById(R.id.email);
        passEt = findViewById(R.id.password);
        confirm_passET = findViewById(R.id.password2);

        String fname = fnameET.getText().toString();
        String email = emailEt.getText().toString();
        String confirm_pass = confirm_passET.getText().toString();
        String pass = passEt.getText().toString();

        String sql = "insert into faculty " + "values('"+fname+"','"+email+"','"+ confirm_pass+"','"+pass+"');";
        registerDB.execSQL(sql);
        Intent i = new Intent(this,login.class);
        startActivity(i);
    }

    private boolean CheckAllFields(){
        if (fnameET.length() == 0){
            fnameET.setError("This field is required");
            return false;
        }
        if (emailEt.length() == 0) {
            emailEt.setError("This field is required");
            return false;
        }
        if (confirm_passET.length() == 0) {
            confirm_passET.setError("Password does not match");
            return false;
        }
        if (passEt.length() == 0 || passEt.length() < 8) {
            passEt.setError("Password is required");
            return false;
        }
        return true;
    }
}