package Datos;

public abstract class clsPersonas {
        private int Id_Persona;
        private String Nombres;
        private String Apellidos;
        private String Movil;
        private String Direccion;
        private String Correo;
        private int Estrato;



        public clsPersonas(){
            this.setId_Persona(0);
            this.setNombres("");
            this.setApellidos("");
            this.setMovil("");
            this.setDireccion("");
            this.setEstrato(0);
            this.setCorreo("");
        }

        public clsPersonas(int intIdPersona, String strNombres, String strApellidos, String strMovil, String strDireccion, int intEstrato){
            this.setId_Persona(intIdPersona);
            this.setNombres(strNombres);
            this.setApellidos(strApellidos);
            this.setMovil(strMovil);
            this.setDireccion(strDireccion);
            this.setEstrato(intEstrato);
        }

    public int getId_Persona() {
        return Id_Persona;
    }

    public void setId_Persona(int id_Persona) {
        Id_Persona = id_Persona;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getMovil() {
        return Movil;
    }

    public void setMovil(String movil) {
        Movil = movil;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getEstrato() {
        return Estrato;
    }

    public void setEstrato(int estrato) {
        Estrato = estrato;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public void setPersona(int intId_Persona, String strNombres, String strApellidos, String strIdentificacion, String strDireccion, int intEstrato, String strMovil){
        this.setId_Persona(intId_Persona);
        this.setNombres(strNombres);
        this.setApellidos(strApellidos);
        this.setDireccion(strDireccion);
        this.setMovil(strMovil);
        this.setEstrato(intEstrato);
    }


}
