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

public class Register extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btn_reg;

    private TextView txt_log;

    TextView txt_loging;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);

        email=findViewById(R.id.txt_email);
        password=findViewById(R.id.txt_password);
        btn_reg=findViewById(R.id.btn_login);
        txt_log=findViewById(R.id.tv_login);
        txt_loging=findViewById(R.id.tv_login);

        txt_loging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail=email.getText().toString().trim();
                String mpassword=password.getText().toString().trim();

                if(mEmail.isEmpty() && mpassword.isEmpty()) {
                    Toast.makeText(Register.this, "Field are Empty", Toast.LENGTH_SHORT).show();
                }else if (mEmail.isEmpty()){
                    email.setError("Required Field..");
                    email.requestFocus();
                }else if (mpassword.isEmpty()){
                    password.setError("Required Field..");
                    password.requestFocus();
                }else if (!(mEmail.isEmpty() && mpassword.isEmpty())) {

                    mDialog.setMessage("Processing..");
                    mDialog.show();

                    mAuth.createUserWithEmailAndPassword(mEmail, mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration successfully..now you can login..", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);
                                mDialog.dismiss();
                            } else {
                                Toast.makeText(getApplicationContext(), "Problem", Toast.LENGTH_LONG).show();
                                mDialog.dismiss();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"Error Occurred!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
