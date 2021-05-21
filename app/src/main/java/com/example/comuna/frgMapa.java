package com.example.comuna;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import Datos.clsEventos;
import Logica.Eventos;
import Logica.Usuarios;
import Posicionamiento.markersEventos;
import adapter.adapterEventos;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;


public class frgMapa extends Fragment implements OnMapReadyCallback, ClusterManager.OnClusterItemInfoWindowClickListener<markersEventos>,
        ClusterManager.OnClusterItemClickListener, ClusterManager.OnClusterClickListener
{
    public GoogleMap gMap;
    public MapView mapView;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Eventos objEventos;
    private Usuarios objUsuarios;
    private List<clsEventos> listEventos;
    //protected ArrayList<Marker> marcasTiempoRealTemp = new ArrayList<>();
    //protected ArrayList<Marker> marcasTiempoReal = new ArrayList<>();
    private ClusterManager<markersEventos> clusterManager;

    public frgMapa() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        mapView = (MapView) view.findViewById(R.id.map2);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        boolean success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        getActivity(), R.raw.estilomapa));
        if (success) {

            updateLocationUI();
            getDeviceLocation();
            clusterManager = new ClusterManager<markersEventos>(getContext(), gMap);
           gMap.setOnCameraIdleListener(clusterManager);
           gMap.setOnMarkerClickListener(clusterManager);
           clusterManager.setOnClusterClickListener(this);
           clusterManager.setOnClusterItemClickListener(this);

            Listar_Eventos();
        }

    }

    private void updateLocationUI() {
        if (gMap == null) {
            Log.e("Hola Jhon ", "Gmap esta null");
        }
        try {
            gMap.setMyLocationEnabled(true);
            gMap.getUiSettings().setMyLocationButtonEnabled(true);
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getDeviceLocation() {

        try {
            Task locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Location mLastKnownLocation = (Location) task.getResult();
                        if(mLastKnownLocation == null){
                            Toast.makeText(getContext(), "Verifica si tienes activo el gps",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), 17));
                            //Listar_Eventos();

                        }
                    }
                }
            });
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    public void Listar_Eventos(){
        Thread threadListarEventos = new Thread(new Runnable() {
            Handler handlerListarEventos = new Handler();
            @Override
            public void run() {
                objEventos = new Eventos();
                objUsuarios = new Usuarios();
                listEventos = new ArrayList<clsEventos>();
                listEventos = objEventos.Listar_Eventos(objUsuarios.Obtener_VariableSession(getContext(), 2));
                handlerListarEventos.post(new Runnable() {
                    @Override
                    public void run() {
                        if(listEventos!= null && listEventos.size() > 0){
                            for(int x =0; x<listEventos.size(); x++){
                                clsEventos objEventos = listEventos.get(x);
                                markersEventos objMarker = new markersEventos(objEventos.getLatitud(), objEventos.getLongitud(), objEventos.getNombre_Evento(), objEventos.getDescripcion_Evento(), objEventos);
                                clusterManager.addItem(objMarker);
                               /* clsEventos objEventos = listEventos.get(x);
                                MarkerOptions markerOptionsEventos = new MarkerOptions();
                                markerOptionsEventos.position(new LatLng(objEventos.getLatitud(), objEventos.getLongitud()));
                                Marker marker = gMap.addMarker(markerOptionsEventos);
                                marker.setTag(objEventos);
                                marker.setTitle(objEventos.getNombre_Evento());
                                marcasTiempoReal.add(marker);*/
                            }
                            clusterManager.cluster();
                        }
                    }
                });
            }
        });threadListarEventos.start();
    }

    @Override
    public void onClusterItemInfoWindowClick(markersEventos item) {

    }

    @Override
    public boolean onClusterItemClick(ClusterItem item) {
        Log.e("Hola Jhon ", "Cluster Item");
        return false;
    }

    @Override
    public boolean onClusterClick(Cluster cluster) {
        Log.e("Hola Jhon ", "Cluster Click");
        return false;
    }
}
