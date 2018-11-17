package mihaiolaru.com.roadtrip;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.HandlerThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.CountDownLatch;

public class chooseLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Double lat;
    Double lng;

    Double latStart;
    Double lngStart;

    final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);


        final LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {

                lat = location.getLatitude();
                lng = location.getLongitude();
                Log.d("location",lat+" "+lng);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Log.d("kys","here we go");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(chooseLocation.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(chooseLocation.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(chooseLocation.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        else {
            Log.d("rip","locationManager");
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, mLocationListener);
            Location curr = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(curr != null) {
                latStart = curr.getLatitude();
                lngStart = curr.getLongitude();
            }
            else{
                latStart = 0d;
                lngStart = 0d;
            }

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
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

        LatLng start = new LatLng(44.4425812,26.0530043);
        if(latStart != null) {
            start = new LatLng(latStart, lngStart);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start, 8.0f));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title("New Marker");

                mMap.addMarker(marker);

                lat = point.latitude;
                lng = point.longitude;
                Log.d("coords",lat+" "+lng);
            }
        });
        Button register = (Button) findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent initial = getIntent();
                if(initial.getStringExtra("from").equals("start")){
                    Intent intent = new Intent(chooseLocation.this, AddTrip.class);
                    intent.putExtra("start_coords", lat + ":" + lng);

                    if (initial.getStringExtra("dataPlecare") != null) {
                        intent.putExtra("dataPlecare", initial.getStringExtra("dataPlecare") );
                    }
                    if (initial.getStringExtra("dataIntoarcere") != null) {
                        intent.putExtra("dataIntoarcere", initial.getStringExtra("dataIntoarcere") );
                    }

                    if(initial.getStringExtra("end_coords") != null)
                        intent.putExtra("end_coords",initial.getStringExtra("end_coords"));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(chooseLocation.this, AddTrip.class);
                    if(initial.getStringExtra("start_coords") != null)
                        intent.putExtra("start_coords",initial.getStringExtra("start_coords"));
                    intent.putExtra("end_coords", lat + ":" + lng);

                    if (initial.getStringExtra("dataPlecare") != null) {
                        intent.putExtra("dataPlecare", initial.getStringExtra("dataPlecare") );
                    }
                    if (initial.getStringExtra("dataIntoarcere") != null) {
                        intent.putExtra("dataIntoarcere", initial.getStringExtra("dataIntoarcere") );
                    }

                    startActivity(intent);
                }
            }
        });
    }
}
