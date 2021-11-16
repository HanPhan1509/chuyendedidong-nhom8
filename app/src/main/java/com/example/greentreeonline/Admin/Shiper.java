package com.example.greentreeonline.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greentreeonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;
import java.util.UUID;


public class Shiper extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiper);


        final Button addtkship = findViewById(R.id.addtkship);
        final EditText emailship = findViewById(R.id.emailship);
        final EditText passship = findViewById(R.id.passship);
        final EditText nameship = findViewById(R.id.nameship);
        final EditText socancuocship = findViewById(R.id.socancuocship);

        addtkship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emailship.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                }
                if(nameship.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                }
                if(passship.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                if(socancuocship.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your ID number", Toast.LENGTH_SHORT).show();
                }
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                if(!(emailship.getText().toString().isEmpty() && nameship.getText().toString().isEmpty() && passship.getText().toString().isEmpty() && socancuocship.getText().toString().isEmpty() )){
                    firebaseAuth.createUserWithEmailAndPassword(emailship.getText().toString(), passship.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                               // String id = UUID.randomUUID().toString();
                                ThongTinShip thongTinShip = new ThongTinShip(emailship.getText().toString(),nameship.getText().toString(), passship.getText().toString(), socancuocship.getText().toString(), firebaseAuth.getUid());
                                firebaseDatabase.getReference().child("shiper").child(thongTinShip.getId()).setValue(thongTinShip);
                                Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Shiper.this, Shiper.class);
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