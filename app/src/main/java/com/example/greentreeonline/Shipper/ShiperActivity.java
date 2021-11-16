package com.example.greentreeonline.Shipper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greentreeonline.R;
import com.google.firebase.database.FirebaseDatabase;

public class ShiperActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiper2);


        firebaseDatabase.getReference().child("shiper").setValue("NodeValue");

        Button addtkship = findViewById(R.id.addtkship);
        addtkship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}