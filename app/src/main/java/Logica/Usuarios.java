package Logica;

import android.content.Context;

import java.util.List;

import Datos.clsUsuarios;

public class Usuarios {
    public clsUsuarios Iniciar_Sesion(String strNombreUsuario, String strContrasenia){
        clsUsuarios objUsuario = new clsUsuarios();
        objUsuario.setNombre_Usuario(strNombreUsuario);
        objUsuario.setContrasenia(strContrasenia);
        return objUsuario.Iniciar_Sesion(objUsuario);
    }

    public boolean Registrar_VariableSession(int intId_Usuario,int intIdRol, Context context){
        clsUsuarios objUsuario = new clsUsuarios();
        objUsuario.setId_Usuario(intId_Usuario);
        objUsuario.setRol(intIdRol);
        return objUsuario.Registrar_VarialbleSesion(context, objUsuario);

    }

    public int Obtener_VariableSession(Context context, int intOpcion){
        clsUsuarios objUsuario = new clsUsuarios();
        return objUsuario.Obtener_VariableSesion(context, intOpcion);
    }

    public clsUsuarios Registrar_Usuario(String strNombreUsuario, String strContrasenia,  String strMovil, String strCorreo){
        clsUsuarios objUsuario = new clsUsuarios();
        objUsuario.setNombre_Usuario(strNombreUsuario);
        objUsuario.setContrasenia(strContrasenia);
        objUsuario.setCorreo(strCorreo);
        objUsuario.setMovil(strMovil);
        return objUsuario.Registrar_Usuario(objUsuario);
    }

    public String Registrar_Persona(String strNombres, String strApellidos, String strMovil, String strCorreo){
        clsUsuarios objUsuario = new clsUsuarios();
        objUsuario.setCorreo(strCorreo);
        objUsuario.setNombres(strNombres);
        objUsuario.setApellidos(strApellidos);
        objUsuario.setMovil(strMovil);
        return objUsuario.Registrar_Persona(objUsuario);
    }

    public int Verificar_Registro_Persona(int intId_Usuario){
        clsUsuarios objUsuario = new clsUsuarios();
        objUsuario.setId_Usuario(intId_Usuario);
        return objUsuario.Verificar_Registro_Persona(objUsuario);
    }

}
