package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView tv_reg;
    private EditText email;
    private EditText password;
    private Button btn_login;

    private FirebaseAuth lAuth;
    private ProgressDialog lDialog;

    private FirebaseAuth.AuthStateListener authoStateListner;

    private ProgressDialog rDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lAuth = FirebaseAuth.getInstance();
        lDialog = new ProgressDialog(this);

        email = findViewById(R.id.txt_email);
        password = findViewById(R.id.txt_password);

        btn_login = findViewById(R.id.btn_login);
        rDialog = new ProgressDialog(this);

        tv_reg = (TextView) findViewById(R.id.tv_login);


        authoStateListner = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = lAuth.getCurrentUser();
                if (firebaseUser != null) {

                    Toast.makeText(getApplicationContext(), "Your logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Home.class));

                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Login", Toast.LENGTH_SHORT).show();
                }

            }
        };

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String lEmail = email.getText().toString().trim();
                String lPassword = password.getText().toString().trim();

                if (lEmail.isEmpty() && lPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();

                } else if (lEmail.isEmpty()) {
                    email.setError("Please Enter Email Address");
                    email.requestFocus();

                } else if (lPassword.isEmpty()) {
                    password.setError("Please Enter password");
                    password.requestFocus();

                } else if (!(lEmail.isEmpty() && lPassword.isEmpty())) {

                    rDialog.setMessage("Processing");
                    rDialog.show();

                    lAuth.signInWithEmailAndPassword(lEmail, lPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Error, please Login Again", Toast.LENGTH_SHORT).show();
                                rDialog.dismiss();
                            } else {
                                modul.email_address = lEmail;
                                Intent intHome = new Intent(MainActivity.this, Home.class);
                                finish();
                                startActivity(intHome);
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                rDialog.dismiss();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Error Occurred!", Toast.LENGTH_SHORT).show();
                    rDialog.dismiss();
                }
            }
        });

        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);

            }
        });
    }
}
