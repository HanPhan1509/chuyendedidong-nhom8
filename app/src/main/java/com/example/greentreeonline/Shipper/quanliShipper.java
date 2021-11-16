package com.example.greentreeonline.Shipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greentreeonline.Admin.Shiper;
import com.example.greentreeonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class quanliShipper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiper);


       final Button addtkship = findViewById(R.id.addtkship);
       final EditText emailship = findViewById(R.id.emailship);
       final EditText passship = findViewById(R.id.passship);

       // bấm thêm shipper:
        addtkship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailship.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                if(passship.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                if(!(emailship.getText().toString().isEmpty() && passship.getText().toString().isEmpty())){
                    // thêm vào auth:
                    firebaseAuth.createUserWithEmailAndPassword(emailship.getText().toString(), passship.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(quanliShipper.this, Shiper.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}