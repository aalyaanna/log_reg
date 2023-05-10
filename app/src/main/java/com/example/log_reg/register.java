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

public class register extends AppCompatActivity {

    TextInputEditText regEmailtxt, regPhonetxt, regNumbertxt, regAddresstxt, regPasswordtxt;
    Button signinbtn;
    TextView signintxt;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        regEmailtxt = findViewById(R.id.regEmail);
        regPhonetxt = findViewById(R.id.regPhone);
        regNumbertxt = findViewById(R.id.regNumber);
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
                String regEmail, regAddress, regPassword, regPhone, regNumber;

                regEmail = String.valueOf(regEmailtxt.getText());
                regAddress = String.valueOf(regAddresstxt.getText());
                regPassword = String.valueOf(regPasswordtxt.getText());
                regPhone = String.valueOf(regPhonetxt.getText());
                regNumber = String.valueOf(regNumbertxt.getText());

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

                if(TextUtils.isEmpty(regNumber)){
                    Toast.makeText(register.this, "Enter your Verification Code", Toast.LENGTH_SHORT).show();
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