package Logica;

import java.util.List;

import Datos.clsNoticias;

public class Noticias {

    public List<clsNoticias> Listar_Noticias(){
        clsNoticias objNoticias = new clsNoticias();
        return objNoticias.Listar_Noticias(objNoticias);
    }

    public  Boolean Registrar_Noticia(String strNombreNoticia, String strDescripcionNoticia, String strURL){
        clsNoticias objNoticias = new clsNoticias();
        objNoticias.setNombre_Noticia(strNombreNoticia);
        objNoticias.setDescripcion_Noticia(strDescripcionNoticia);
        objNoticias.setImagen_Noticia(strURL);
        return objNoticias.Registrar_Noticia(objNoticias);
    }
}
