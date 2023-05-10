package com.example.log_reg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    TextInputEditText regEmailtxt, regPhonetxt, regUsernametxt, regAddresstxt, regPasswordtxt;
    Button signinbtn;
    TextView signintxt;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        regUsernametxt = findViewById(R.id.regUsername);
        regPhonetxt = findViewById(R.id.regPhone);
        regEmailtxt = findViewById(R.id.regEmail);
        regAddresstxt = findViewById(R.id.regAddress);
        regPasswordtxt = findViewById(R.id.regPassword);

        signinbtn = findViewById(R.id.signupbtn);
        signintxt = findViewById(R.id.txtvLogin);

        signintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
                finish();
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regEmail, regAddress, regPassword, regPhone, regUsername;

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String email = regEmailtxt.getText().toString();
                String phone = regPhonetxt.getText().toString();
                String username = regUsernametxt.getText().toString();
                String address = regAddresstxt.getText().toString();
                String password = regPasswordtxt.getText().toString();

                HelperClass helperClass = new HelperClass(email, phone, username, address, password);
                reference.child(username).setValue(helperClass);

                regEmail = String.valueOf(regEmailtxt.getText());
                regAddress = String.valueOf(regAddresstxt.getText());
                regPassword = String.valueOf(regPasswordtxt.getText());
                regPhone = String.valueOf(regPhonetxt.getText());
                regUsername = String.valueOf(regUsernametxt.getText());


                if(TextUtils.isEmpty(regEmail)){
                    Toast.makeText(register.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(regAddress)){
                    Toast.makeText(register.this, "Enter your Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(regPassword)){
                    Toast.makeText(register.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(regPhone)){
                    Toast.makeText(register.this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(regUsername)){
                    Toast.makeText(register.this, "Enter your Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(regEmail, regPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(register.this, "Registration is Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register.this, home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(register.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}