package com.example.android.covidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CitizenLoginOrRegister extends AppCompatActivity {

    EditText email,password;
    Button login,register;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityclor);
        email=findViewById(R.id.etmail);
        password=findViewById(R.id.etpass);
        login=findViewById(R.id.loginbut);

        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(CitizenLoginOrRegister.this,"please enter the required fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    auth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent g=new Intent(CitizenLoginOrRegister.this,Citizen.class);
                                startActivity(g);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(CitizenLoginOrRegister.this,"Authentication Failed!!!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        register=findViewById(R.id.regbuttononmainpage);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), CitizenRegister.class);
                startActivity(i);
            }
        });
    }
}
