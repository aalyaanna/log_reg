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

public class login extends AppCompatActivity {

    TextInputEditText loginEmail, loginPassword;
    Button loginbtn;
    TextView txtvReg;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        loginEmail = findViewById(R.id.logEmail);
        loginPassword = findViewById(R.id.logPassword);
        loginbtn = findViewById(R.id.loginbtn);
        txtvReg = findViewById(R.id.txtvReg);

        txtvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(loginEmail.getText());
                password = String.valueOf(loginPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "Enter your Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Enter your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(login.this, "Login is Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login.this, home.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(login.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}