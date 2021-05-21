package Datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Logica.Usuarios;

public class clsUsuarios extends clsPersonas {
    private int Id_Usuario;
    private String Nombre_Usuario;
    private String Contrasenia;
    private String Correo;
    private int Estado;
    private int Rol;


    public clsUsuarios(){
        this.setId_Usuario(0);
        this.setNombre_Usuario("");
        this.setContrasenia("");
        this.setEstado(0);
    }

    public clsUsuarios(int intId_Persona, String strNombres, String strApellidos, String strDireccion, String strIdentificacion, String strMovil, int intEstrato, int intId_Usuario, String strNombreUsuario, String strContrasenia, int intEstado){
        this.setId_Usuario(intId_Usuario);
        this.setNombre_Usuario(strNombreUsuario);
        this.setContrasenia(strContrasenia);
        this.setEstado(intEstado);
        super.setId_Persona(intId_Persona);
        super.setNombres(strNombres);
        super.setApellidos(strApellidos);
        super.setDireccion(strDireccion);
        super.setMovil(strMovil);
    }


    public int getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        Id_Usuario = id_Usuario;
    }

    public String getNombre_Usuario() {
        return Nombre_Usuario;
    }

    public void setNombre_Usuario(String nombre_Usuario) {
        Nombre_Usuario = nombre_Usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    public String Registrar_Persona(clsUsuarios objUsuarios){
        List<clsUsuarios> listUsuarios = new ArrayList<clsUsuarios>();
        int strIdReturn = 0;
        String strURL = "index_usuarios.php?opcion=Registrar_Persona&Nombres="+objUsuarios.getNombres()+"&Apellidos="+objUsuarios.getApellidos()+"&Movil="+objUsuarios.getMovil()+"&Correo="+objUsuarios.getCorreo();
        Conexion con = new Conexion();
        String StrResult = con.HttpRequest(strURL);
        return StrResult;
    }

    public clsUsuarios Registrar_Usuario(clsUsuarios objUsuarios) {
        List<clsUsuarios> listUsuarios = new ArrayList<clsUsuarios>();
        clsUsuarios objUsuario = new clsUsuarios();
        String strURL = "index_usuarios.php?opcion=Registrar_Usuario&Nombre_Usuario=" + objUsuarios.getNombre_Usuario() + "&Contrasenia=" + objUsuarios.getContrasenia() + "&Correo=" + objUsuarios.getCorreo() + "&Movil=" + objUsuarios.getMovil();
        Conexion con = new Conexion();
        String strResult = con.HttpRequest(strURL);
        JSONObject jsonObjectListarUsuarios = con.Convertir_JsonObject(strResult);
            if (jsonObjectListarUsuarios == null) {
                return null;
            } else {
                try {
                    JSONArray jsonArrayNoticias = jsonObjectListarUsuarios.getJSONArray("Registrar_Usuario");

                    JSONObject jsonObjectFeed = (JSONObject) jsonArrayNoticias.get(0);
                    objUsuario.setId_Usuario(jsonObjectFeed.getInt("Id_Usuario"));
                    objUsuario.setRol(jsonObjectFeed.getInt("Id_Rol"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            return objUsuario;
        }

    }


    public clsUsuarios Iniciar_Sesion(clsUsuarios objUsuario){
        String url_noticias = "index_usuarios.php?opcion=Iniciar_Sesion&Nombre_Usuario="+objUsuario.getNombre_Usuario()+"&Contrasenia="+objUsuario.getContrasenia();
        Conexion con = new Conexion();
        clsUsuarios objUsuarios = null;

        String StrResult = con.HttpRequest(url_noticias);
        JSONObject jsonObjectListarUsuarios = con.Convertir_JsonObject(StrResult);
        if(jsonObjectListarUsuarios == null){
            return null;
        }else {
            try {
                JSONArray jsonArrayNoticias = jsonObjectListarUsuarios.getJSONArray("Iniciar_Sesion");
                if(jsonArrayNoticias.length() > 0){
                    JSONObject jsonObjectFeed = (JSONObject) jsonArrayNoticias.get(0);
                    objUsuarios = new clsUsuarios();
                    objUsuarios.setId_Usuario(jsonObjectFeed.getInt("Id_Usuario"));
                    objUsuarios.setRol(jsonObjectFeed.getInt("Id_Rol"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return objUsuarios;
    }


    public int Verificar_Registro_Persona(clsUsuarios objUsuario){
        int intIdReturn = 0;
        String strURL = "index_usuarios.php?opcion=Verificar_UsuarioPersona&Id_Usuario="+objUsuario.getId_Usuario();
        Conexion con = new Conexion();
        String StrResult = con.HttpRequest(strURL);
        JSONObject jsonObjectListarUsuarios = con.Convertir_JsonObject(StrResult);
        if(jsonObjectListarUsuarios == null){
            return 0;
        }else {
            try {
                JSONArray jsonArrayNoticias = jsonObjectListarUsuarios.getJSONArray("Verificar_UsuarioPersona");
                for (int i = 0; i < jsonArrayNoticias.length(); i++) {
                    JSONObject jsonObjectFeed = (JSONObject) jsonArrayNoticias.get(i);
                    intIdReturn = jsonObjectFeed.getInt("Id_Persona");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return intIdReturn;
        }
    }


    public boolean Registrar_VarialbleSesion(Context context, clsUsuarios objUsuario){
        SharedPreferences.Editor variableSesion = context.getSharedPreferences("comuna", Context.MODE_PRIVATE).edit();
        variableSesion.putString("Id_Usuario", ""+objUsuario.getId_Usuario());
        variableSesion.putString("Id_Rol", ""+objUsuario.getRol());
        variableSesion.commit();
        return true;
    }

    public int Obtener_VariableSesion(Context context,int intOpcion){
        SharedPreferences sharedPreferencesSesion = context.getSharedPreferences("comuna",	Context.MODE_PRIVATE);
        String strReturn = "0";
        switch (intOpcion) {
            case 1:
                if (sharedPreferencesSesion.getString("Id_Usuario", "").equals("")) {
                    return 0;
                } else {
                    strReturn = sharedPreferencesSesion.getString("Id_Usuario", "");
                }
                break;
            case 2:
                if (sharedPreferencesSesion.getString("Id_Rol", "").equals("")) {
                    return 0;
                } else {
                    strReturn = sharedPreferencesSesion.getString("Id_Rol", "");
                }
                break;
        }
        return Integer.parseInt(strReturn);
    }

    public int getRol() {
        return Rol;
    }

    public void setRol(int rol) {
        Rol = rol;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
