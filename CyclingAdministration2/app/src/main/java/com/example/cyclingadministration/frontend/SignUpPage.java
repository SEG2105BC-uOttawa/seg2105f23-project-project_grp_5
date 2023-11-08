package com.example.cyclingadministration.frontend;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cyclingadministration.R;
import com.example.cyclingadministration.backend.Registration;
public class SignUpPage extends AppCompatActivity {
    private String username, password, cpassword, role;
    EditText usernamet, passwordt, cpasswordt;
    Button submit;
    Spinner spinner;

    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        submit = findViewById(R.id.sign_up_button);

        spinner = findViewById(R.id.spinner);
        String[] items = {"Participant", "Club Representative"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle the case where nothing is selected, if needed.
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernamet = findViewById(R.id.username);
                username = usernamet.getText().toString();

                passwordt = findViewById(R.id.password);
                password = passwordt.getText().toString();

                cpasswordt = findViewById(R.id.confirm_password);
                cpassword = cpasswordt.getText().toString();

                role = spinner.getSelectedItem().toString();
                if (role.equals("Participant")) {
                    Toast.makeText(SignUpPage.this, "Participant Sign-Up Selected", Toast.LENGTH_SHORT).show();
                } else if (role.equals("Club Representative")) {
                    Toast.makeText(SignUpPage.this, "Club Representative Sign-Up Selected", Toast.LENGTH_SHORT).show();
                }

                isAllFieldsChecked = validateFields();

                if (isAllFieldsChecked){
                    Registration newUser = new Registration();
                    newUser.addUser(username, password, role);
                    Intent i = new Intent(SignUpPage.this, WelcomePageTwo.class);
                    i.putExtra("KEY_USERNAME", username);
                    i.putExtra("KEY_ROLE", role);
                    startActivity(i);
                    startActivity(i);
                }

            }
        });
        }

        public boolean validateFields(){
            String regex1 = "^[A-Za-z]\\w{5,29}$";
            String regex2 = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
            Pattern p1 = Pattern.compile(regex1);
            Matcher m1 = p1.matcher(username);
            Pattern p2 = Pattern.compile(regex2);
            Matcher m2 = p2.matcher(password);

            if (!m1.matches() || username.isEmpty()){
                usernamet.setError("Invalid username");
                return false;
            } else if (!m2.matches() || password.isEmpty()){
                passwordt.setError("Invalid Password!");
                return false;
            } else if (!password.equals(cpassword)){
                cpasswordt.setError("Password does not match");
                return false;
            }
            return true;
        }


}
