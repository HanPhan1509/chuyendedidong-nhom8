package com.example.shippedd;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Info extends AppCompatActivity {
    DatabaseReference firebaseDatabase;

    TextView idshiper;
    TextView hotenshiper;
    TextView txtEmail;
    TextView socancuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.info_shipper);

        idshiper = findViewById(R.id.txtidshipper);
        hotenshiper = findViewById(R.id.txtHoVaTenQLTK);
        txtEmail = findViewById(R.id.txtEmail);
        socancuoc = findViewById(R.id.txtsocancuocQLTK);

        // vào data lấy dữ liệu:
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.child("shiper").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals(LoginActivity.id)) {
                    ThongTinShip thongTinShip = snapshot.getValue(ThongTinShip.class);
                    idshiper.setText(thongTinShip.getId());
                    hotenshiper.setText(thongTinShip.getName());
                    txtEmail.setText(thongTinShip.getEmail());
                    socancuoc.setText(thongTinShip.getSocancuoc());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
