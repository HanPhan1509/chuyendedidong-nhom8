package com.example.greentreeonline.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.AdapterProduct;
import com.example.greentreeonline.Class.Favourite;
import com.example.greentreeonline.Class.Product;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Main.Bill.MainBill;
import com.example.greentreeonline.Main.Login.MainLogin;
import com.example.greentreeonline.Main.Login.MainSetting;
import com.example.greentreeonline.Main.Login.MainUpdateProfile;
import com.example.greentreeonline.Main.MainMap;
import com.example.greentreeonline.Navigation;
import com.example.greentreeonline.R;
import com.example.greentreeonline.sanphamdamua.sanphamdamua;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

import static com.example.greentreeonline.Fragment.FragmentHome.objdh;
import static com.example.greentreeonline.Main.Login.MainLogin.TAG;


public class FragmentProfile extends Fragment {
    public static TextView hoten1, gioitinh, date, mail, sdt1, dc, cho;
    private int i;
    ImageView tt;
    RecyclerView recyclerView;
 //   Button btn, set, dh, dn;
    ArrayList<Favourite> yeut;
    public static ArrayList<Product> sp;
    AdapterProduct adapcay;
    private TextView btndangxuat, dcs, btndangnhap, update,xemlsdh,like;
    //public static List<objacc> acc = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //////////////////////////////////
    private SharedPreferences sharedPreferences1;
    private SharedPreferences.Editor editor1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        EventBus.getDefault().register(this);
        hoten1 = view.findViewById(R.id.hotennguoidung);
//        gioitinh = view.findViewById(R.id.gioitinh);
//        date = view.findViewById(R.id.ngaysinh);
//        mail = view.findViewById(R.id.gmail);
//        sdt1 = view.findViewById(R.id.tv_sdt);
//        dc = view.findViewById(R.id.tv_diachi);
        tt = view.findViewById(R.id.imgthongtin);

        btndangxuat = view.findViewById(R.id.btndangxuat);
        btndangnhap = view.findViewById(R.id.btndangnhap);
        dcs = view.findViewById(R.id.dcshop);
        update= view.findViewById(R.id.updatetk);
        recyclerView = view.findViewById(R.id.review1);
        xemlsdh = view.findViewById(R.id.btndonhang);
        like = view.findViewById(R.id.txtlike);
        cho = view.findViewById(R.id.tinh);
       // set = view.findViewById(R.id.setting);
        sp = new ArrayList<Product>();
        adapcay = new AdapterProduct(getActivity(), sp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(adapcay);


        sharedPreferences = getContext().getSharedPreferences("luutaikhoan", getContext().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        ////////////////////////////////////////////
        sharedPreferences1 = getContext().getSharedPreferences("chitiet", getContext().MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();

        CheckData();
        onclick();
      //  check();
        getcay();

        diachishop();
        checkdangnhap();
        dangnhap();
        dangxuat();
        return view;
    }

    public void diachishop() {
        dcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainMap.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainUpdateProfile.class);
                startActivity(intent);
            }
        });
    }

    public void checkdangnhap() {
        String TenTk = sharedPreferences.getString("taikhoan", "");
        if (!TextUtils.isEmpty(TenTk)) {
            btndangxuat.setVisibility(View.VISIBLE);
            btndangnhap.setVisibility(View.INVISIBLE);
        } else {
            Toast.makeText(getContext(), "Bạn cần đăng nhập", Toast.LENGTH_SHORT).show();
            //  startActivity(new Intent(getActivity(),dangnhap.class));
            btndangxuat.setVisibility(View.INVISIBLE);
            btndangnhap.setVisibility(View.VISIBLE);

            // finish();
        }
    }
        public void dangnhap() {
            btndangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), MainLogin.class);
                    startActivity(intent);
                }
            });
        }

        public void dangxuat() {

            btndangxuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    editor.clear();
                    editor.commit();

                    EventBus.getDefault().post(true, "loginSuccess");
                    Intent intent = new Intent(getContext(), Navigation.class);
                    startActivity(intent);

                    Toast.makeText(getContext(), "Bạn Đã Đăng Xuất! ", Toast.LENGTH_SHORT).show();
                }
            });
        }


    public static void check() {
        int id = 0;
        id += objdh.size();
      //  cho.setText(id + "");
    }

    public void onclick() {

        xemlsdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainBill.class);
                startActivity(intent);
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), sanphamdamua.class);
                startActivity(intent);
            }
        });
    }

    @Subscriber(tag = "loginSuccess")
    void loginSuccess(boolean b) {
        Log.e(TAG, "loginSuccess: ");
        CheckData();
    }

    private void getcay() {

        RequestQueue connnect = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArray = new JsonArrayRequest(Request.Method.GET, ConnectServer.trangchu, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonsp = response.getJSONObject(i);
                        int id = jsonsp.getInt("id");
                        String tensp = jsonsp.getString("tensp");
                        int gia = jsonsp.getInt("giasp");
                        String igmsp = jsonsp.getString("igmsp");
                        String mota = jsonsp.getString("mota");

                        sp.add(new Product(id, tensp, gia, igmsp, mota));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                adapcay.notifyDataSetChanged();
                //Toast.makeText(getContext().getApplicationContext(), "" + ssale.size(), Toast.LENGTH_SHORT).show();
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        );
        connnect.add(jsonArray);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void CheckData() {
        String ten = sharedPreferences.getString("taikhoan", "");

        if (!TextUtils.isEmpty(ten)) {
            hoten1.setText(sharedPreferences.getString("hoten", ""));


            //  tt.setImageBitmap(sharedPreferences.getString("imgtk",));
//            Picasso.get().load(sharedPreferences.getString("imgtk",""));

        } else {
            hoten1.setText("Tên người dùng");
//            gioitinh.setText("");
//            date.setText("");
//            mail.setText("");
//            sdt1.setText("");
//            dc.setText("");

        }
    }

}
