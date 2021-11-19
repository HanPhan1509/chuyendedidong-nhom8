package com.example.greentreeonline.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.greentreeonline.Adapter.AdapterBanner;
import com.example.greentreeonline.Adapter.AdapterSale;
import com.example.greentreeonline.Adapter.New.AdapterProduct;
import com.example.greentreeonline.Class.Bill.Bill;
import com.example.greentreeonline.Class.Cart;
import com.example.greentreeonline.Class.Category.Category;
import com.example.greentreeonline.Class.Category.CategoryChild;
import com.example.greentreeonline.Class.Customer;
import com.example.greentreeonline.Class.Favourite;
import com.example.greentreeonline.Class.New.Product;
import com.example.greentreeonline.Class.Sale;
import com.example.greentreeonline.Class.SlidePhoto;
import com.example.greentreeonline.ConnectServer.ConnectServer;
import com.example.greentreeonline.Firebase.FirebaseCallback;
import com.example.greentreeonline.Firebase.ProductFirebase;
import com.example.greentreeonline.Main.MainCart;
import com.example.greentreeonline.Main.MainSearch;
import com.example.greentreeonline.Main.Sale.MainSale;
import com.example.greentreeonline.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome<mDatabase> extends Fragment {
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    ViewFlipper viewFlipper;
    Toolbar toolbar;
    RecyclerView recyclerView, resale;
    AdapterProduct adapcay;
    ImageButton gh;
    TextView xemthem, tvsreach;
    ListView lvsale;
    SearchView searchView;

    ProductFirebase productFirebase;

    boolean isloading, limitdata = false;
    public static ArrayList<Product> sp;
    public static ArrayList<Favourite> yt;

    String urlcaycanh = ConnectServer.trangchu;
    String urlsale = ConnectServer.sale;

    public static ArrayList<Cart> listgh;
    public static ArrayList<Customer> mangkhachHang;
    public static ArrayList<Bill> objdh;
    public static ArrayList<Product> vc;
    //  public static ArrayList<objhome> listyt;
    private ViewPager viewPagerSlidePhoto;
    private Timer mTimer;
    private View mView;
    private AdapterBanner slidePhotoAdapter;

    private RecyclerView rcvProduct, rcvSale;
    private CircleIndicator circleIndicator;
    List<SlidePhoto> listSlidePhoto = new ArrayList<>();

    public FragmentHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("category");

//        //category
//        //sửa lại cái cây cảnh là category
//        String userId = myRef.push().getKey();
//        Category category = new Category(userId, "CHĂM SÓC TOÀN THÂN");
//        myRef.child(userId).setValue(category);
//
//        //categorychilid
//        //còn cái này là add cate child cho nó
//
//        ArrayList<String> categoryChild = new ArrayList<String>();
//        categoryChild.add("Dưỡng thể - Body Lotion");
//        categoryChild.add("Sữa tăm");
//        categoryChild.add("Kem dưỡng toàn thân");
//        categoryChild.add("Dưỡng da tay");
//        categoryChild.add("Tẩy tế bào chết");
//        categoryChild.add("Tẩy lông");
//        categoryChild.add("Khủ mùi");
//        categoryChild.add("Chăm sóc răng miệng");
//
//
//        for (int i = 0; i < categoryChild.size(); i++){
//            myRef = database.getReference("categorychild");
//            String catchildID = myRef.push().getKey();
//            CategoryChild catchild = new CategoryChild(catchildID, categoryChild.get(i), userId);
//            myRef.child(catchildID).setValue(catchild);
//        }
        View view = inflater.inflate(R.layout.activity_main, container, false);
        //atcProductSearch = mView.findViewById(R.id.atc_product_search);

        productFirebase = new ProductFirebase();

        toolbar = view.findViewById(R.id.toolbarmanhinh);
        recyclerView = view.findViewById(R.id.rcv_product);
        gh = view.findViewById(R.id.giohang);
        tvsreach = view.findViewById(R.id.tvsreach);


        viewPagerSlidePhoto = view.findViewById(R.id.vp_slide_photo);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        listSlidePhoto = getListSlidePhoto();


        // searchView = view.findViewById(R.id.search);
        ////////////////////////////////////////////////////////////////////
        sp = new ArrayList<Product>();
        adapcay = new AdapterProduct(getActivity(), sp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapcay);
        if (listgh != null) {
        } else {
            listgh = new ArrayList<Cart>();
        }
        if (mangkhachHang != null) {

        } else {
            mangkhachHang = new ArrayList<Customer>();
        }
        if (yt != null) {

        } else {
            yt = new ArrayList<Favourite>();
        }
        if (vc != null) {

        } else {
            vc = new ArrayList<Product>();
        }
        if (objdh != null) {

        } else {
            objdh = new ArrayList<Bill>();
        }
        getcay();
        //Quangcao();
        event();
        setDataSlidePhotoAdapter();
        timkiem();
        return view;
    }

    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu menu) {
        getLayoutInflater().inflate(R.menu.sreach, (ViewGroup) menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                adapcay.filter(s.trim());
                return false;
            }
        });
        return true;
    }

    // Set Adapter cho viewPagerSlidePhoto
    private void setDataSlidePhotoAdapter() {


        slidePhotoAdapter = new AdapterBanner(listSlidePhoto, this);
        viewPagerSlidePhoto.setAdapter(slidePhotoAdapter);
        circleIndicator.setViewPager(viewPagerSlidePhoto);
        slidePhotoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        // Auto chuyển các slide photo
        autoSildeImage();
    }

    // Lấy Product để vào slide
    //Thêm thông báo
    private List<SlidePhoto> getListSlidePhoto() {

        listSlidePhoto.add(new SlidePhoto("https://adminbeauty.hvnet.vn/Upload/Files/banner-min.jpg?v=1"));
        listSlidePhoto.add(new SlidePhoto("https://cdn.cocolux.com/2021/10/images/banners/1635328587776-NHNH.jpeg"));
        listSlidePhoto.add(new SlidePhoto("https://cdn.cocolux.com/2021/10/images/banners/1635498215069-kw.jpeg"));
        //  viewPagerSlidePhoto.setAdapter(slidePhotoAdapter,this);
        //  adaperSlide.registerDataSetObserver(circleIndicator.getDataSetObserver());
        return listSlidePhoto;


    }


    private void autoSildeImage() {
        if (listSlidePhoto == null || listSlidePhoto.isEmpty() || viewPagerSlidePhoto == null) {
            return;
        }
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPagerSlidePhoto.getCurrentItem();
                        int totalItem = listSlidePhoto.size() - 1;

                        // Nếu item hiện tại chưa phải cuối cùng
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPagerSlidePhoto.setCurrentItem(currentItem);
                        } else {
                            viewPagerSlidePhoto.setCurrentItem(0);
                        }
                    }
                });
            }

            // xử lý thêm để set time
        }, 500, 3000);
    }


    public void event() {

        gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainCart.class);
                startActivity(intent);
            }
        });

//        xemthem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainSale.class);
//
//                startActivity(intent);
//
//            }
//        });
//        searchView.setOnSearchClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View v) { ;
//                adapcay.filter(v.toString());
//            }
//        });
    }


    public void timkiem() {
        tvsreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainSearch.class);
                startActivity(intent);
            }
        });


    }

    public void Quangcao() {
        ArrayList<String> QC = new ArrayList<>();
        QC.add("https://1989.com.vn/wp-content/uploads/2019/03/top30.jpg");
        QC.add("https://sendalongphung.com/wp-content/uploads/2018/09/0-cay-canh-trong-trong-nha.jpg");
        QC.add("https://bancongxanh.com/wp-content/uploads/2019/04/Gi%C3%A1-c%C3%A2y-c%E1%BA%A3nh-v%C4%83n-ph%C3%B2ng-%C4%90%C3%A0-N%E1%BA%B5ng-m%E1%BB%9Bi-nh%E1%BA%A5t-2019.jpg");
        QC.add("https://ncppb.com/wp-content/uploads/2019/04/top-10-website-ban-cay-canh.jpg");
        QC.add("https://afamilycdn.com/2019/9/5/33-1567649196717622170194-crop-15676492145021350614745.jpg");
        QC.add("https://afamilycdn.com/2019/10/2/20190530beginnerplants0007-1570002818813385100229-crop-157000282538490310658.jpg");
        QC.add("https://kienviet.net/wp-content/uploads/2019/05/H1-e1559014099672.jpg");
        for (int i = 0; i < QC.size(); i++) {
            ImageView image = new ImageView(getActivity());
            Picasso.get().load(QC.get(i)).into(image);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            // image.setImageResource(QC.get(i));
            viewFlipper.addView(image);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.hieuung);
        Animation animation_out = AnimationUtils.loadAnimation(getActivity(), R.anim.hieuung2);
        viewFlipper.setInAnimation(animation);
        viewFlipper.setOutAnimation(animation_out);
    }


    private void getcay(){
        productFirebase.getAllProduct(new FirebaseCallback() {
            @Override
            public void onCallBack(Object obj) {
                for (Product product: (ArrayList<Product>)obj){
                    sp.add(product);
                }
                adapcay.notifyDataSetChanged();
            }
        });
    }
}