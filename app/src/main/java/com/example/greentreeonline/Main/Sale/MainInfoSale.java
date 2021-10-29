package com.example.greentreeonline.Main.Sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.greentreeonline.Class.Cart;
import com.example.greentreeonline.Class.Sale;
import com.example.greentreeonline.Main.MainCart;
import com.example.greentreeonline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import static com.example.greentreeonline.Fragment.FragmentHome.listgh;

public class MainInfoSale extends AppCompatActivity {

    Sale sl;
    ImageView imgcayct;
    Cart gh;
    TextView ctcay, ctgia, ctmota,ctsl;
    Toolbar toolbar;
    Integer[] soluong;
    EditText edgiatri;
    Button tang, giam, ttmua;
    ImageButton addgh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsale);
        anhxa();
        themgh();
        soluong();
        sale();
//      getdatacay();
    }

    private void anhxa() {
        imgcayct = (ImageView) findViewById(R.id.imageView);
        ctcay = (TextView) findViewById(R.id.tensp);
        ctgia = (TextView) findViewById(R.id.giathanh);
        ctmota = (TextView) findViewById(R.id.dacdiem);
        tang = findViewById(R.id.butontang);
        giam = findViewById(R.id.butongiam);
        edgiatri = findViewById(R.id.edtgiatri);
        ctsl=findViewById(R.id.ctsale);
        addgh = (ImageButton) findViewById(R.id.addgiohang);


    }

    public void sale() {
        Intent intent = getIntent();
        sl = (Sale) intent.getSerializableExtra("homesale");
        Picasso.get().load(sl.getImgspsale()).into(imgcayct);
        ctcay.setText(sl.getTenspsale());
        ctsl.setText(sl.getSale()+" %");
        DecimalFormat decimalformat = new DecimalFormat("###,###,###");
        ctgia.setText(decimalformat.format(sl.sale()) + " VNĐ");
        ctmota.setText(sl.getMota());
    }


    public void soluong() {
        tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(edgiatri.getText().toString()) + 1;

                edgiatri.setText(slmoi + "");


                if (slmoi > 100) {
                    tang.setVisibility(View.INVISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                } else {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                }
            }
        });
        giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slmoi = Integer.parseInt(edgiatri.getText().toString()) - 1;

                edgiatri.setText(slmoi + "");
                if (slmoi < 1) {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.INVISIBLE);
                    edgiatri.setText(slmoi + "");
                } else {
                    tang.setVisibility(View.VISIBLE);
                    giam.setVisibility(View.VISIBLE);
                    edgiatri.setText(slmoi + "");
                }
            }
        });

    }

    private void themgh() {
        addgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   if (listgh.size()>0) {
                boolean exist = false;
                for (int i = 0; i < listgh.size(); i++) {
                    if (listgh.get(i).getIdgh() == sl.getIdspsale()) {
                        int soLuong = Integer.parseInt(edgiatri.getText().toString().trim());
                        int sl = soLuong + listgh.get(i).getSl();
                        if (sl > 100) {
                            listgh.get(i).setSl(100);
                        } else {
                            listgh.get(i).setSl(sl);
                        }
                        exist = true;
                    }
                }
                if (listgh.size() > 0) {
                    int soLuong = Integer.parseInt(edgiatri.getText().toString().trim());
                    Cart gioHang = new Cart(sl.getIdspsale(), sl.getTenspsale(), sl.getImgspsale(), sl.sale(), soLuong);
                    listgh.add(gioHang);


                } else {
                    int soLuong = Integer.parseInt(edgiatri.getText().toString().trim());
                    Cart gioHang = new Cart(sl.getIdspsale(), sl.getTenspsale(), sl.getImgspsale(), sl.sale(), soLuong);
                    listgh.add(gioHang);
                }
                startActivity(new Intent(MainInfoSale.this, MainCart.class));
                //  }
            }
        });
    }


}
