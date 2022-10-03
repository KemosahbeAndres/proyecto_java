package stomas.andres;

public class Cliente {
    public String nombre;
    private String run;
    public String direccion;
    public String mail;

    public Cliente(String nombre, String run, String direccion, String mail) {
        this.nombre = nombre;
        this.run = run;
        this.direccion = direccion;
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public void imprimir(){

    }
}
