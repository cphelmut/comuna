package Posicionamiento;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import Datos.clsEventos;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class markersEventos implements ClusterItem {
    private  LatLng position;
    private  String title;
    private  String snippet;
    public clsEventos objEventos;

    public markersEventos(double lat, double lng, String title, String snippet, clsEventos objEvento) {
        position = new LatLng(lat, lng);
        this.title = title;
        this.snippet = snippet;
        this.objEventos = objEvento;
    }

    @NonNull
    @Override
    public LatLng getPosition() {
        return position;
    }

    @Nullable
    @Override
    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public String getSnippet() {
        return snippet;
    }
}
