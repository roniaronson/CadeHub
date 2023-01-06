package com.example.ronisproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.ronisproject.R;
import com.example.ronisproject.http.MyHTTPInterface;
import com.example.ronisproject.http.login.Login;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static String GLOBAL_EMAIL;

    private TextInputLayout start_EDT_email;
    private TextInputLayout start_EDT_password;
    private MaterialButton start_BTN_login;
    private MaterialButton start_BTN_register;
    private MaterialTextView start_TXT_forgot;
    private TextInputLayout[] loginFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cade-Hub

        findViews();
        initBTNs();

    }

    private void initBTNs() {

        start_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        start_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {register();}
        });

    }


    private void login() throws IOException {
        if (isValid()) {

            String email, password;
            email = start_EDT_email.getEditText().getText().toString().trim();
            password = start_EDT_password.getEditText().getText().toString().trim();

            Login success = (Login) new Login(email, password, new MyHTTPInterface() {
                @Override
                public void myMethod(boolean result) {
                    if (result == true) {
                        GLOBAL_EMAIL = email;
                        Toast.makeText(MainActivity.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Email or Password are incorrect!", Toast.LENGTH_LONG).show();                    }
                }
            }).execute();
        }

        else
            Toast.makeText(this, "One or more field are invalid!", Toast.LENGTH_LONG).show();

    }

    private void register() {
        finish();
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


    private boolean isValid() {
        for (TextInputLayout field : loginFields) {
            if (field.getError() != null || field.getEditText().getText().toString().isEmpty())
                return false;
        }
        return true;
    }





    private void findViews() {
        start_EDT_email = findViewById(R.id.start_EDT_email);
        start_EDT_password = findViewById(R.id.start_EDT_password);
        start_BTN_login = findViewById(R.id.start_BTN_login);
        start_BTN_register = findViewById(R.id.start_BTN_register);
        start_TXT_forgot = findViewById(R.id.start_TXT_forgot);
        loginFields = new TextInputLayout[] {
                start_EDT_email,
                start_EDT_password
        };
    }

}
