package com.example.cyclingadministration;

import androidx.appcompat.app.AppCompatActivity;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cyclingadministration.backend.Login;
import com.google.android.material.textfield.TextInputEditText;

public class LoginPage extends AppCompatActivity {
    TextInputEditText account;
    String username, password;
    EditText pass;
    Button submitLogin;
    boolean isLogged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        submitLogin = findViewById(R.id.loginButton);
        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = findViewById(R.id.user_account);
                username = account.getText().toString();

                pass = findViewById(R.id.user_pass);
                password = pass.getText().toString();

                submitLogin = findViewById(R.id.LogInButton);

                Login login = new Login();
            login.getUser(username, password, new Login.LoginCallback() {
                    @Override
                    public void onResult(boolean success, String role) {

                        if (success) {
                            Intent i = new Intent(LoginPage.this, WelcomePageTwo.class);
                            i.putExtra("KEY_USERNAME", username);
                            i.putExtra("KEY_ROLE", role);
                            startActivity(i);
                            Log.d(TAG, "Role: " + role);
                        } else {
                            account.setError("Username and password do not match!");
                            Log.d(TAG, "No matching data found.");
                        }
                    }
                });
            }
        });


    }
}