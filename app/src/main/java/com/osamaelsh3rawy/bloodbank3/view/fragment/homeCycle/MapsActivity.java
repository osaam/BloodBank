package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.osamaelsh3rawy.bloodbank3.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button MapsActivity;
    private double lat;
    private double lang;
    public static String addressString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        MapsActivity=findViewById(R.id.maps_btn);
MapsActivity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});
    }

    private  void getAddress (){
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());
        List<Address> addresses= null;
        try {
            addresses = geocoder.getFromLocation(lat,lang,1);
            addressString =addresses.get(0).getAddressLine(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                lat=latLng.latitude;
                lang=latLng.longitude;

                LatLng h=new LatLng(lat,lang);

                mMap.addMarker(new MarkerOptions().position(h).title(" ADDRESS "));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(h,0));
                getAddress();
            }
        });
    }
}
