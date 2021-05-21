package Datos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Conexion.Conexion;

public class clsNoticias {
    private int Id_Noticia;
    private String Nombre_Noticia;
    private String Imagen_Noticia;
    private String Descripcion_Noticia;
    private int Estado_Noticia;
    private String Fecha_Noticia;

    public clsNoticias(int intIdNoticia, String strNombreNoticia, String strDescripcionNoticia, String strImagenNoticia, int intEstadoNoticia){
        this.Id_Noticia = intIdNoticia;
        this.Nombre_Noticia = strNombreNoticia;
        this.Descripcion_Noticia = strDescripcionNoticia;
        this.Imagen_Noticia = strImagenNoticia;
        this.Estado_Noticia = intEstadoNoticia;
    }
    public clsNoticias(){}

    public int getId_Noticia() {
        return Id_Noticia;
    }

    public void setId_Noticia(int id_Noticia) {
        Id_Noticia = id_Noticia;
    }

    public String getNombre_Noticia() {
        return Nombre_Noticia;
    }

    public void setNombre_Noticia(String nombre_Noticia) {
        Nombre_Noticia = nombre_Noticia;
    }

    public String getImagen_Noticia() {
        return Imagen_Noticia;
    }

    public void setImagen_Noticia(String imagen_Noticia) {
        Imagen_Noticia = imagen_Noticia;
    }

    public String getDescripcion_Noticia() {
        return Descripcion_Noticia;
    }

    public void setDescripcion_Noticia(String descripcion_Noticia) {
        Descripcion_Noticia = descripcion_Noticia;
    }

    public int getEstado_Noticia() {
        return Estado_Noticia;
    }

    public void setEstado_Noticia(int estado_Noticia) {
        Estado_Noticia = estado_Noticia;
    }

    public String getFecha_Noticia() {
        return Fecha_Noticia;
    }

    public void setFecha_Noticia(String fecha_Noticia) {
        Fecha_Noticia = fecha_Noticia;
    }

    public List<clsNoticias> Listar_Noticias(clsNoticias objNoticias){
        List<clsNoticias> noticiasList = new ArrayList<clsNoticias>();
        String strURL = "index_noticias.php?opcion=Listar_Noticias&op=2";
        Conexion cn = new Conexion();
        String strjsonRequest = cn.HttpRequest(strURL);
        JSONObject jsonObject = cn.Convertir_JsonObject(strjsonRequest);
        if(jsonObject == null){
            return null;
        }else {
            try {
                JSONArray jArray = jsonObject.getJSONArray("Listar_Noticias");
                for(int x= 0; x< jArray.length(); x++){
                    clsNoticias objNoticia = new clsNoticias();
                    JSONObject jObject = (JSONObject) jArray.get(x);
                    objNoticia.setId_Noticia(jObject.getInt("Id_Noticia"));
                    objNoticia.setNombre_Noticia(jObject.getString("Nombre_Noticia"));
                    objNoticia.setDescripcion_Noticia(jObject.getString("Descripcion_Noticia"));
                    objNoticia.setFecha_Noticia(jObject.getString("Fecha_Noticia"));
                    objNoticia.setImagen_Noticia(jObject.getString("Imagen_Noticia"));

                    noticiasList.add(objNoticia);
                }
            }  catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return noticiasList;
    }


    public Boolean Registrar_Noticia(clsNoticias objNoticias){
        String strURL = "index_noticias.php?opcion=Registrar_Noticia";
        Conexion con = new Conexion();
        HashMap<String,String> HashMapParams = new HashMap<String,String>();
        HashMapParams.put("Nombre_Noticia", objNoticias.getNombre_Noticia());
        HashMapParams.put("Descripcion", objNoticias.getDescripcion_Noticia());
        HashMapParams.put("Imagen_Noticia",objNoticias.getImagen_Noticia());
        String strRes = con.Ejecutar_Post(strURL, HashMapParams);
        if(strRes.contains("0")){
            return false;
        }else {
            return true;
        }
    }



}
