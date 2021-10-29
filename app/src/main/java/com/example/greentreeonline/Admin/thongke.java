package com.example.greentreeonline.Admin;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Class.New.DoanhThu;
import com.example.greentreeonline.Firebase.DoanhThuFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.R;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class thongke extends AppCompatActivity {
    ArrayList<objthongke> mang;
    ArrayList<objthongke>mang1;
    BarChart barChart;
    ArrayList barEntriesArrayList;
    BarData barData;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // variable for our bar data set.
    BarDataSet barDataSet;
    Toolbar toolbar;
    Button btnnam;
    EditText editText;

    String idUser;

    DoanhThuFirebase doanhThuFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke2);
        anhxa();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mang = new ArrayList<>();
        mang1 = new ArrayList<>();

        sharedPreferences = this.getSharedPreferences("luutaikhoan", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        doanhThuFirebase = new DoanhThuFirebase();

        barChart = findViewById(R.id.bieudo);

        getBarEntries();

        ActionBar();
//        btnnam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tukhoa = editText.getText().toString().trim();
//                Log.d("Tukhoa",tukhoa);
//                String query = "SELECT Year(ngay) as ngay, sum(tongtien) as tongtien FROM donhang WHERE  Year(ngay) >= "+tukhoa+-4+" AND  Year(ngay) <=  "+tukhoa+" GROUP BY Year(ngay)";
//                Nam(query);
//            }
//        });
        getDoanhThu();
    }
    private void anhxa() {
        toolbar = (Toolbar) findViewById(R.id.tool_thongke);
//        btnnam = (Button) findViewById(R.id.btnNam);
//        editText= (EditText) findViewById(R.id.edt);

    }
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 0));
        barEntriesArrayList.add(new BarEntry(2f, 0));
        barEntriesArrayList.add(new BarEntry(3f, 0));
        barEntriesArrayList.add(new BarEntry(4f, 0));
        barEntriesArrayList.add(new BarEntry(5f, 0));
        barEntriesArrayList.add(new BarEntry(6f, 0));
        barEntriesArrayList.add(new BarEntry(7f, 0));
        barEntriesArrayList.add(new BarEntry(8f, 0));
        barEntriesArrayList.add(new BarEntry(9f, 0));
        barEntriesArrayList.add(new BarEntry(10f, 0));
        barEntriesArrayList.add(new BarEntry(11f, 0));
        barEntriesArrayList.add(new BarEntry(12f, 0));
    }

    public void getDoanhThu(){
        doanhThuFirebase.GetDoanhThuByIdShop(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for(DoanhThu doanhThu: (ArrayList<DoanhThu>) obj){
                    BarEntry barEntry = (BarEntry) barEntriesArrayList.get(doanhThu.getThang() - 1);
                    barEntry.setY((float) doanhThu.getDoanhthu());
                    barEntriesArrayList.set(doanhThu.getThang() - 1, barEntry);
                }
                setBar();
            }
        });
    }

    public void setBar(){
        barDataSet = new BarDataSet(barEntriesArrayList, "Doanh thu");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(10f);
        barChart.getDescription().setEnabled(false);
    }

    public void Nam(final String query){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConnectServer.thongkenam, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String thoigian = jsonObject.getString("ngay");
                            int tongtiennam = jsonObject.getInt("tongtien");

                            Toast.makeText(thongke.this, "năm  "+thoigian, Toast.LENGTH_SHORT).show();
                            Toast.makeText(thongke.this, "tổng tiền"+tongtiennam, Toast.LENGTH_SHORT).show();

                            mang1.add(new objthongke(thoigian,tongtiennam));

                            ArrayList<BarEntry> dataValu = new ArrayList<>();
                            for(int x = 0; x < mang1.size();x++) {
                                dataValu.add(new BarEntry(Float.parseFloat(mang1.get(0).getThoigian()), mang1.get(0).getTongtien()));
                                BarDataSet barDataSet = new BarDataSet(dataValu, "Thống kê doanh thu theo từng năm ");

                                barDataSet.setValueTextColor(Color.BLACK);
                                barDataSet.setValueTextSize(15f);
                                BarData barData = new BarData(barDataSet);
                                barChart.setFitBars(true);
                                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                                barChart.setData(barData);
                                barChart.getDescription().setText("Biểu đồ doanh thu theo từng năm");
                                barChart.animateY(4000);

                                XAxis xAxis = barChart.getXAxis();
                                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                                xAxis.setGranularity(1);
                                xAxis.setGranularityEnabled(true);
                                barChart.setDragEnabled(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("loiii", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("query_user", query);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
