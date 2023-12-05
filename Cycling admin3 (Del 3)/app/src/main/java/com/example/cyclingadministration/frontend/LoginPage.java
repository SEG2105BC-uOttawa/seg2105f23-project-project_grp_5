package com.example.cyclingadministration.frontend;

import androidx.appcompat.app.AppCompatActivity;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Users;
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
        Log.d(TAG, "Role: " +  ((UserState) this.getApplication()).getUserState());
        submitLogin = findViewById(R.id.loginButton);
        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = findViewById(R.id.user_account);
                username = account.getText().toString();


                pass = findViewById(R.id.user_pass);
                password = pass.getText().toString();

                submitLogin = findViewById(R.id.LogInButton);

                Users login = new Users();
            login.getUser(username, password, new Users.LoginCallback() {
                    @Override
                    public void onResult(boolean success, String role, String isComplete) {

                        if(success) {
                            Intent i = null;
                            Log.d(TAG, "Role: " + username);
                            ((UserState) LoginPage.this.getApplication()).setUserState(true);
                            ((UserState) LoginPage.this.getApplication()).setRoleForUser(role);
                            ((UserState) LoginPage.this.getApplication()).setIsCompleteForUser(Boolean.parseBoolean(isComplete));
                            ((UserState) LoginPage.this.getApplication()).setUsernameForUser(username);
                            ((UserState) LoginPage.this.getApplication()).setPasswordForUser(password);
                            Log.d(TAG, "Role: " + ((UserState) LoginPage.this.getApplication()).getUserState());
                            Log.d(TAG, "username: " + ((UserState) LoginPage.this.getApplication()).getUsernameForUser());
                            if (success && role.equals("Participant")) {
                                i = new Intent(LoginPage.this, ParticipantMain.class);
                                startActivity(i);
                                Log.d(TAG, "Role: " + role);
                            } else if(success && role.equals("Club Representative")){
                                i = new Intent(LoginPage.this, clubowner_main.class);
                            }

                            else if (success && role.equals("admin")) {
                                i = new Intent(LoginPage.this, AdminDashboard.class);
                            }
                            startActivity(i);
                            Log.d(TAG, "Role: " + role);
                        }else {
                            account.setError("Username and password do not match!");
                            Log.d(TAG, "No matching data found.");
                        }
                    }
                });
            }
        });


    }
}