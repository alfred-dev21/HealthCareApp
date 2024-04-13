package com.example.healthcareapp.validations;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.healthcareapp.database.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDetails {
    private String username, email, password1, getPassword2;
    String Email_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
    DatabaseHelper database;
    Context context;

    public ValidateDetails(
            Context context,
            DatabaseHelper database,
            String username,
            String email,
            String password1,
            String getPassword2
    ) {
        this.username = username;
        this.email = email;
        this.password1 = password1;
        this.getPassword2 = getPassword2;
        this.database = database;
        this.context = context;
    }

    public boolean validate(){
        if (!areAllFilled()){
            Toast.makeText(context, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isValidEmail()){
            Toast.makeText(context, "Please enter a valid email.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!arePasswordsEqual()){
            Toast.makeText(context, "Passwords are not the same.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isEmailNew()){
            Toast.makeText(context, "An account with that email already exists", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isEmailNew() {
        Cursor cursor = database.doQuery(String.format(
                "SELECT * FROM userDetails WHERE email='%s'", email
        ));

        return !cursor.moveToNext();
    }

    public boolean areAllFilled(){
        return  !username.isEmpty() &&
                !email.isEmpty() &&
                !password1.isEmpty() &&
                !getPassword2.isEmpty();
    }

    public boolean arePasswordsEqual(){
        return password1.equals(getPassword2);
    }

    public boolean isValidEmail(){
        Pattern pattern = Pattern.compile(Email_REGEX);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getGetPassword2() {
        return getPassword2;
    }

    public void setGetPassword2(String getPassword2) {
        this.getPassword2 = getPassword2;
    }
}
