package Logica;

import Datos.clsUsuarios;

public class Personas {
    public void Registrar_Persona(String strNombres, String strApellidos, String strCorreo, int intId_Usuario){
        clsUsuarios objUsuario = new clsUsuarios();
        objUsuario.setNombres(strNombres);
        objUsuario.setApellidos(strApellidos);
        objUsuario.setCorreo(strCorreo);
        objUsuario.setId_Usuario(intId_Usuario);
        //return objUsuario.Registrar_Persona(objUsuario);

    }

}
