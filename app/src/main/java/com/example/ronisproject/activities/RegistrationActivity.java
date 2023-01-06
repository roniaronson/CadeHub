package com.example.ronisproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ronisproject.R;
import com.example.ronisproject.http.MyHTTPInterface;
import com.example.ronisproject.http.register.Register;
import com.example.ronisproject.support.Validator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class RegistrationActivity extends AppCompatActivity {

    public static String GLOBAL_EMAIL_REG;
    private TextInputLayout reg_EDT_email;
    private TextInputLayout reg_EDT_password;
    private TextInputLayout reg_EDT_name;
    private TextInputLayout reg_EDT_phone;
    private MaterialTextView reg_TXT_login;

    private TextInputLayout[] allFields;
    private MaterialButton reg_BTN_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        findViews();
        initBTNs();
        checkFormValidation();

    }

    private boolean isValid() {
        for (TextInputLayout field : allFields) {
            if (field.getError() != null || field.getEditText().getText().toString().isEmpty())
                return false;
        }
        return true;
    }

    private void checkFormValidation() {

        Validator.Builder
                .make(reg_EDT_password)
                .addWatcher(new Validator.Watcher_Password("Invalid password"))
                .build();

        Validator.Builder
                .make(reg_EDT_phone)
                .addWatcher(new Validator.Watcher_Phone("Invalid phone number"))
                .build();

        Validator.Builder
                .make(reg_EDT_email)
                .addWatcher(new Validator.Watcher_Email("Invalid email"))
                .build();
    }

    private void initBTNs() {
        reg_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { register();}
        });

        reg_TXT_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        if (isValid()) {
            //addToDB();


            String email = reg_EDT_email.getEditText().getText().toString().trim();
            String password = reg_EDT_password.getEditText().getText().toString().trim();
            String name = reg_EDT_name.getEditText().getText().toString().trim();
            String phone = reg_EDT_phone.getEditText().getText().toString().trim();

            GLOBAL_EMAIL_REG = email;

            Register reg = (Register) new Register(new MyHTTPInterface() {
                @Override
                public void myMethod(boolean result) {
                    if (result == true) {
                        Toast.makeText(RegistrationActivity.this, "Registered successfully!", Toast.LENGTH_LONG).show();
                    }
                }
            }).execute();

            Toast.makeText(this, "Registration completed!", Toast.LENGTH_LONG).show();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        else
            Toast.makeText(this, "One or more field are invalid!", Toast.LENGTH_LONG).show();
    }

    private void findViews() {
        reg_EDT_email = findViewById(R.id.reg_EDT_email);
        reg_EDT_password = findViewById(R.id.reg_EDT_password);
        reg_EDT_name = findViewById(R.id.reg_EDT_name);
        reg_EDT_phone = findViewById(R.id.reg_EDT_phone);
        reg_BTN_register = findViewById(R.id.reg_BTN_register);
        reg_TXT_login = findViewById(R.id.reg_TXT_login);

        allFields = new TextInputLayout[] {
                reg_EDT_email,
                reg_EDT_password,
                reg_EDT_name,
                reg_EDT_phone
        };
    }

}
