package com.example.greentreeonline.sanphamdamua;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.New.AdapterFavourite;
import com.example.greentreeonline.Class.New.Favourite;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.FavouriteFirebase;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.greentreeonline.Fragment.FragmentHome.yt;

public class sanphamdamua extends AppCompatActivity {
    String urlcaycanh = ConnectServer.yeuthich;
    ArrayList<Favourite> yt;
    AdapterFavourite like;
    RecyclerView lv;
    Button btyt;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    String idUser;
    FavouriteFirebase favouriteFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanphamdamua);
        sharedPreferences = sanphamdamua.this.getSharedPreferences("luutaikhoan", sanphamdamua.this.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        idUser = sharedPreferences.getString("id", "0");

        favouriteFirebase = new FavouriteFirebase();

        lv = findViewById(R.id.lvdm);
        anhxa();
        getcay();
    }
    private void anhxa() {
        yt = new ArrayList<Favourite>();
        like = new AdapterFavourite(sanphamdamua.this, yt);
        lv.setHasFixedSize(true);
        lv.setLayoutManager(new GridLayoutManager(this, 1));
        lv.setAdapter(like);

    }
    public void bothich(final int idsp) {

    }
    public void getcay() {
        favouriteFirebase.getFavouriteUserId(idUser, new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for(Favourite favourite: (ArrayList<Favourite>) obj){
                    yt.add(favourite);
                }
                like.notifyDataSetChanged();
            }
        });
    }
}
