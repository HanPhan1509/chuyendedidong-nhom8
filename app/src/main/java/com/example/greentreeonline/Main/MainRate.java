package com.example.greentreeonline.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greentreeonline.Adapter.New.AdapRate;
import com.example.greentreeonline.Class.New.Rate;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.RateFirebase;
import com.example.greentreeonline.R;

import java.util.ArrayList;

public class MainRate extends AppCompatActivity {

    EditText danhgia;
    RatingBar star;
    RecyclerView rcv_rate;
    Button submit;
    Toast toast;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String iduser, name, idsp, comment;
    float stars;

    RateFirebase rateFirebase;

    AdapRate adapRate;
    ArrayList<Rate> rates;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        star = findViewById(R.id.ratingBar_yours);
        danhgia = findViewById(R.id.addmota);
        rcv_rate = findViewById(R.id.rcv_rate);
        submit = findViewById(R.id.button_submit);

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        iduser = sharedPreferences.getString("id", "0");
        name = sharedPreferences.getString("hoten", "0");

        Intent intent = getIntent();
        idsp = intent.getStringExtra("idsp");

        rateFirebase = new RateFirebase();

        rates = new ArrayList<>();
        adapRate = new AdapRate(getApplicationContext(), rates);
        rcv_rate.setHasFixedSize(true);
        rcv_rate.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        rcv_rate.setAdapter(adapRate);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRate();
            }
        });
        getRate();
    }

    public void addRate(){
        comment = danhgia.getText().toString().trim();
        stars = star.getRating();

        if(stars == 0 || comment.isEmpty()){
            Toast.makeText(MainRate.this, "Vui lòng nhập đủ đánh giá và chọn sao", Toast.LENGTH_SHORT).show();
        }else {
            Rate rate = new Rate(idsp, iduser, name, comment, stars);
            rateFirebase.addRate(rate, new FirebaseCallback() {
                @Override
                public void onCallBack(Object obj) {
                    if((boolean) obj == true){
                        danhgia.setText("");
                        danhgia.clearFocus();
                        toast = Toast.makeText(getBaseContext(), "Bạn đã đánh giá sản phẩm thành công", Toast.LENGTH_SHORT);
                        System.out.println("tttt");
                        toast.show();
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if(toast != null){
            toast.cancel();
        }
        super.onBackPressed();
    }

    public void getRate(){
        rateFirebase.getRate(idsp, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for (Rate rate: (ArrayList<Rate>) obj){
                    rates.add(rate);
                }
                adapRate.notifyDataSetChanged();
            }
        });
    }
}
