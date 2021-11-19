package com.example.greentreeonline.Main;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

import com.example.greentreeonline.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainMap extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;
    GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng currentlocation = new LatLng(location.getLatitude(), location.getLongitude());
            Marker marker = map.addMarker(new MarkerOptions().position(currentlocation).title("Vị trí của tôi"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentlocation, 20));
        }
    };

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.ggmap);
        mapFragment.getMapAsync(this);
        Spinner();

        // ke();

    }

    public void Spinner() {
        Spinner spinner_maps_type = (Spinner) findViewById(R.id.spinnermap);
        String arrMap[] = getResources().getStringArray(R.array.maps_type);
        ArrayAdapter<String> adapterMap = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, arrMap);
        adapterMap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_maps_type.setAdapter(adapterMap);
        spinner_maps_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int type = GoogleMap.MAP_TYPE_NORMAL;
                switch (arg2) {

                    case 0:
                        type = GoogleMap.MAP_TYPE_NORMAL;
                        break;
                    case 1:
                        type = GoogleMap.MAP_TYPE_SATELLITE;
                        break;
                    case 2:
                        type = MAP_TYPE_TERRAIN;
                        break;
                    case 3:
                        type = GoogleMap.MAP_TYPE_HYBRID;
                        break;
                }
                map.setMapType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(myLocationChangeListener);
        LatLng nhavien = new LatLng(10.80984779989315,106.71264636807376); //10.80984779989315, 106.71264636807376
//        LatLng ute1 = new LatLng(20.8590086, 105.9996825);
//        LatLng ute2 = new LatLng(20.9450817, 106.0391655);
//        LatLng ute3 = new LatLng(20.9364662, 106.31045);
        map.addMarker(new MarkerOptions().position(nhavien).title("SHOP SGSOSMESTICS "));
//        map.addMarker(new MarkerOptions().position(ute1).title("cs1"));
//        map.addMarker(new MarkerOptions().position(ute2).title("Cs2"));
//        map.addMarker(new MarkerOptions().position(ute3).title("Cs3"));

        map.moveCamera(CameraUpdateFactory.newLatLng(nhavien));

    }

}

