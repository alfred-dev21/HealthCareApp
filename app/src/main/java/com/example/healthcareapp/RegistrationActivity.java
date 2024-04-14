package com.example.healthcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcareapp.database.DatabaseHelper;
import com.example.healthcareapp.validations.ValidateDetails;

public class RegistrationActivity extends AppCompatActivity {
    private String username, email, password1, getPassword2;
    private EditText Uname, mail, pass1, pass2;
    private CheckBox checkBox;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        database = new DatabaseHelper(this, "app");
    }

    public void toLogin(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void retrieveData(View view) {
        Uname = findViewById(R.id.usernameInput);
        mail = findViewById(R.id.EmailAddress);
        pass1 = findViewById(R.id.editTextTextPassword);
        pass2 = findViewById(R.id.editTextTextConfirmPassword);
        checkBox = findViewById(R.id.checkBox);

        username = Uname.getText().toString();
        email = mail.getText().toString();
        password1 = pass1.getText().toString();
        getPassword2 = pass2.getText().toString();

        ValidateDetails validateDetails = new ValidateDetails(
                this,
                database,
                username,
                email,
                password1,
                getPassword2
        );

        boolean valid = validateDetails.validate();
        if (valid){
            Toast.makeText(this, "Account successfully registered.", Toast.LENGTH_SHORT).show();
            Uname.setText(""); mail.setText("");
            pass1.setText(""); pass2.setText("");
            checkBox.setChecked(false);

            doInsert();
        }
    }

    public void doInsert(){
        String[] values = {username, email, password1};
        database.doUpdate("INSERT INTO userDetails (username, email, password) VALUES (?,?,?)", values);
    }

}