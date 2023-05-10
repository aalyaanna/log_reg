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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

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
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Enter your Password", Toast.LENGTH_SHORT).show();
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
                                    checkUser();
                                }
                            }
                        });

            }
        });
    }
    public void checkUser(){
        String userEmail = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("user-email").equalTo(userEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    loginEmail.setError(null);
                    String passwordFromDB = snapshot.child(userEmail).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        loginEmail.setError(null);

                        String emailFromDB = snapshot.child(userEmail).child("email").getValue(String.class);
                        String addressFromDB = snapshot.child(userEmail).child("address").getValue(String.class);
                        String phoneFromDB = snapshot.child(userEmail).child("phone").getValue(String.class);
                        String numberFromDB = snapshot.child(userEmail).child("number").getValue(String.class);

                        Intent intent = new Intent(login.this, register.class);

                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("phone", phoneFromDB);
                        intent.putExtra("number", numberFromDB);
                    } else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginEmail.setError("Email does not Exist");
                    loginEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    };
}