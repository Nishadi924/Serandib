package com.example.searchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;//show current location
    private static final int Request_User_Location_code=99;
    private double latitide,longitude;
    private int proximityRadius=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void onClick(View v)
    {
        //String hotel="hotel", restaurant="restaurant";
        Object transferData[]=new Object[2];

       GetNearbyPlaces getNearbyPlaces=new GetNearbyPlaces();



        switch(v.getId())
        {
            case R.id.search_address:
                EditText addressfield=(EditText) findViewById(R.id.location_search);
                String address=addressfield.getText().toString();

                List<Address> addressList=null;
                MarkerOptions userMarkerOptions=new MarkerOptions();

                if(!TextUtils.isEmpty(address))
                {
                    Geocoder geocoder=new Geocoder(this);

                    try {
                        addressList=geocoder.getFromLocationName(address, 6);

                        if(addressList != null)
                        {
                            for(int i=0; i<addressList.size();i++)
                            {
                                Address userAddress=addressList.get(i);
                                LatLng latLng= new LatLng(userAddress.getLatitude(),userAddress.getLongitude());

                                userMarkerOptions.position(latLng);
                                userMarkerOptions.title(address);
                                userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

                                mMap.addMarker(userMarkerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }

                        }
                        else{
                            Toast.makeText(this, "Location not found...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    Toast.makeText(this, "please write any location name...", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.hotels_nearby:
                mMap.clear();
                //
                String hotel="hotel";
                String url= getUrl(latitide, longitude, hotel);
                transferData[0]=mMap;
                transferData[1]=url;

                //newly added this line
                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "searching for near by hotels..", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "showing near by hotels..", Toast.LENGTH_SHORT).show();
                break;

            case R.id.restaurants_nearby:
                mMap.clear();
                //

                String restaurant="restaurant";
                String url1= getUrl(latitide, longitude, restaurant);
                transferData[0]=mMap;
                transferData[1]=url1;

                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "searching for near by restaurants..", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "showing near by restaurants..", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    private String getUrl(double latitide,double longitude,String nearbyplace)
    {
        StringBuilder googleURL=new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location="+latitide+"," +longitude);
        googleURL.append("&radius="+proximityRadius);
        googleURL.append("&type="+nearbyplace);
        googleURL.append("&sensor=true");
        //get Api key
        googleURL.append("&key="+"AIzaSyAOLVTT5dGhmK9F5u0QOW42JSItMtaa2Os");

        Log.d("GoogleMapActivity","url="+googleURL.toString());

        return googleURL.toString();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //getting current locatin of user
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {

            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }


    }
     public boolean checkUserLocationPermission()
     {
         if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
         {
             if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
             {
                 ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_code);
             }
             else
             {
                 ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_code);
             }
             return false;
         }
         else{
             return true;
         }

     }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case Request_User_Location_code:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                    {
                        if(googleApiClient== null){
                            buildGoogleApiClient(); //creating a new client
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this, "permission Dinied...", Toast.LENGTH_SHORT).show();
                }
                return;

        }
    }

    protected synchronized void buildGoogleApiClient()
        {
            googleApiClient=new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            googleApiClient.connect();
        }

        @Override
        public void onLocationChanged(@NonNull Location location) {


            latitide=location.getLatitude();
            longitude=location.getLongitude();
            lastLocation =location;
            if (currentLocationMarker!=null)
            {
                currentLocationMarker.remove();
            }
            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

            //display the marker at current position

            MarkerOptions markerOptions= new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("user Current Location");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            currentLocationMarker= mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

            if(googleApiClient != null)
            {
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            }

        }

        @Override
        public void onConnected(@Nullable Bundle bundle) {

            locationRequest= new LocationRequest();
            locationRequest.setInterval(1100);
            locationRequest.setFastestInterval(1100);
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            //get the current location of the user
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
            }
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }
    }