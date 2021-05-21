package Datos;

public class clsTipoEvento {
    private int Id_TipoEvento;
    private String Nombre_TipoEvento;

    public clsTipoEvento(int intIdTipoEvento, String strNombreTipoEvento){
        this.setId_TipoEvento(intIdTipoEvento);
        this.setNombre_TipoEvento(strNombreTipoEvento);
    }

    public clsTipoEvento(){}

    public int getId_TipoEvento() {
        return Id_TipoEvento;
    }

    public void setId_TipoEvento(int id_TipoEvento) {
        Id_TipoEvento = id_TipoEvento;
    }

    public String getNombre_TipoEvento() {
        return Nombre_TipoEvento;
    }

    public void setNombre_TipoEvento(String nombre_TipoEvento) {
        Nombre_TipoEvento = nombre_TipoEvento;
    }
}
