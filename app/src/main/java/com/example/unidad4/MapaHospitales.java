package com.example.unidad4;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaHospitales extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private final LatLng UCAM = new LatLng(37.9913674,-1.185472);
    public static HospitalesBD hospitales;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapahospitales);

        // Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(false);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UCAM, 15));
        mapa.addMarker(new MarkerOptions()
                .position(UCAM)
                .title("UCAM")
                .snippet("Universidad Católica de Murcia"));

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setCompassEnabled(true);
        }
    }

    // Desplaza el punto de visualización a las coordenadas de la UCAM
    // Sin cambiar el nivel de zoom fijado
    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(UCAM));
    }

    // Añade un marcador pasando como parámetro las coordenadas del punto
    // donde se ha pulsado
    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(
                mapa.getCameraPosition().target));
    }
}
