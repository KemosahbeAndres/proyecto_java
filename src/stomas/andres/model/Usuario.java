package stomas.andres.model;

public class Usuario {
    private int id;
    private String usuario;
    private String contraseña;
    private int fecha;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private String getContraseña() {
        return contraseña;
    }

    private void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getFecha() {
        return fecha;
    }

    private void setFecha(int fecha) {
        this.fecha = fecha;
    }
}
