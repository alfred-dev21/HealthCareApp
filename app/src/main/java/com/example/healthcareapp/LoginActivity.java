package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcareapp.database.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private String email, password;
    DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void toRegistration(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void retrieveData(View view){
        EditText inputEmail = findViewById(R.id.EmailAddress);
        EditText inputPassword = findViewById(R.id.editTextTextPassword);

        email = inputEmail.getText().toString();
        password = inputPassword.getText().toString();

        if(validateDetails()){
            Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean allFilled(){
        return !email.isEmpty() && !password.isEmpty();
    }

    public boolean isValidEmail(){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidUser(){
        Cursor cursor = database.doQuery(
                String.format(
                        "SELECT email, password FROM userDetails WHERE email='%s' AND password='%s'", email, password
                )
        );

        return cursor.moveToNext();
    }

    public boolean validateDetails(){
        if (allFilled()){
            if (!isValidEmail()){
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!isValidUser()){
                Toast.makeText(this, "Either your password or email is incorrect", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
        Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
        return false;
    }
}