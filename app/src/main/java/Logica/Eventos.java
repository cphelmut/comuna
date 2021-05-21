package Logica;

import java.util.List;

import Datos.clsEventos;

public class Eventos {

    public List<clsEventos> Listar_Eventos(int intOpcion){
        clsEventos objEventos = new clsEventos();
        return objEventos.Listar_Eventos(intOpcion);
    }

    public Boolean Registrar_Eventos(String strNombreEvento, String strDescripcion, String strFechaInicio, String strImagenEvento){
        clsEventos objEventos = new clsEventos();
        objEventos.setNombre_Evento(strNombreEvento);
        objEventos.setDescripcion_Evento(strDescripcion);
        objEventos.setFecha_Evento(strFechaInicio);
        objEventos.setImagen_Evento(strImagenEvento);
        return objEventos.Registrar_Eventos(objEventos);
    }
}
