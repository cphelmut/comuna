package Datos;

import android.text.BoringLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Conexion.Conexion;

public class clsEventos {
    private int Id_Evento;
    private String Nombre_Evento;
    private String Descripcion_Evento;
    private String Estado_Evento;
    private String Fecha_Evento;
    private clsTipoEvento objTipoEvento;
    private String Imagen_Evento;
    private Double Latitud;
    private Double Longitud;

    public clsEventos(int intIdEvento, String strNombreEvento, String strDescripcionEvento, String strEstadoEvento, String strFechaEvento, clsTipoEvento objTipoEvento){
        this.setId_Evento(intIdEvento);
        this.setNombre_Evento(strNombreEvento);
        this.setDescripcion_Evento(strDescripcionEvento);
        this.setEstado_Evento(strEstadoEvento);
        this.setFecha_Evento(strFechaEvento);
        this.setObjTipoEvento(objTipoEvento);
    }

    public clsEventos(){}


    public int getId_Evento() {
        return Id_Evento;
    }

    public void setId_Evento(int id_Evento) {
        Id_Evento = id_Evento;
    }

    public String getNombre_Evento() {
        return Nombre_Evento;
    }

    public void setNombre_Evento(String nombre_Evento) {
        Nombre_Evento = nombre_Evento;
    }

    public String getDescripcion_Evento() {
        return Descripcion_Evento;
    }

    public void setDescripcion_Evento(String descripcion_Evento) {
        Descripcion_Evento = descripcion_Evento;
    }

    public String getEstado_Evento() {
        return Estado_Evento;
    }

    public void setEstado_Evento(String estado_Evento) {
        Estado_Evento = estado_Evento;
    }

    public String getFecha_Evento() {
        return Fecha_Evento;
    }

    public void setFecha_Evento(String fecha_Evento) {
        Fecha_Evento = fecha_Evento;
    }

    public clsTipoEvento getObjTipoEvento() {
        return objTipoEvento;
    }

    public void setObjTipoEvento(clsTipoEvento objTipoEvento) {
        this.objTipoEvento = objTipoEvento;
    }

    public String getImagen_Evento() {
        return Imagen_Evento;
    }

    public void setImagen_Evento(String imagen_Evento) {
        Imagen_Evento = imagen_Evento;
    }

    public Double getLatitud() {
        return Latitud;
    }

    public void setLatitud(Double latitud) {
        Latitud = latitud;
    }

    public Double getLongitud() {
        return Longitud;
    }

    public void setLongitud(Double longitud) {
        Longitud = longitud;
    }

    public List<clsEventos> Listar_Eventos(int intOpcion){
        List<clsEventos> eventosList = new ArrayList<clsEventos>();
        String strURL = "";
        if(intOpcion == 2) {
            strURL  = "index_eventos.php?opcion=Listar_Eventos&op=1";
        }else{
            strURL = "index_eventos.php?opcion=Listar_Eventos&op=0";
        }
        Conexion cn = new Conexion();
        String strjsonRequest = cn.HttpRequest(strURL);
        JSONObject jsonObject = cn.Convertir_JsonObject(strjsonRequest);
        if(jsonObject == null){
            return null;
        }else {

            try {
                JSONArray jArray = jsonObject.getJSONArray("Listar_Eventos");
                for(int x= 0; x< jArray.length(); x++){
                    clsEventos objEvento = new clsEventos();
                    clsTipoEvento objTipoEvento = new clsTipoEvento();
                    JSONObject jObject = (JSONObject) jArray.get(x);
                    objEvento.setId_Evento(jObject.getInt("Id_Evento"));
                    objEvento.setNombre_Evento(jObject.getString("Nombre_Evento"));
                    objEvento.setDescripcion_Evento(jObject.getString("Descripcion_Evento"));
                    objEvento.setImagen_Evento(jObject.getString("Imagen_Evento"));
                    objEvento.setEstado_Evento(jObject.getString("Estado_Evento"));
                    objTipoEvento.setId_TipoEvento(jObject.getInt("Id_TipoEvento"));
                    objTipoEvento.setNombre_TipoEvento(jObject.getString("Nombre_TipoEvento"));
                    objEvento.setLatitud(jObject.getDouble("Latitud"));
                    objEvento.setLongitud(jObject.getDouble("Longitud"));
                    objEvento.setObjTipoEvento(objTipoEvento);
                    eventosList.add(objEvento);
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return eventosList;
    }


    public Boolean Registrar_Eventos(clsEventos objEventos){
        //String strURL = "index_eventos.php?opcion=Registrar_Evento&Nombre_Evento="+objEventos.getNombre_Evento()+"&Descripcion_Evento="+objEventos.getDescripcion()+"&Fecha_Evento=2021-05-08%2011:30:00&Id_Usuario="+objEventos.getObjUsuario().getId_Usuario();
        String strURL = "index_eventos.php?opcion=Registrar_Evento";
        HashMap<String,String> HashMapParams = new HashMap<String,String>();
        HashMapParams.put("Nombre_Evento", objEventos.getNombre_Evento());
        HashMapParams.put("Descripcion_Evento", objEventos.getDescripcion_Evento());
        HashMapParams.put("Fecha_Evento", objEventos.getFecha_Evento());
        HashMapParams.put("Imagen_Evento", objEventos.getImagen_Evento());
        Conexion con = new Conexion();
        String strjsonRequest = con.Ejecutar_Post(strURL, HashMapParams);
        if(strjsonRequest.contains("1")){
            return true;
        }else{
            return false;
        }

    }



}
