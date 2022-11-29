package stomas.andres.entitys;

import java.util.Arrays;
import java.util.Vector;

public class Usuario implements Vectorizable{
    private int id;
    private String usuario;
    private String contraseña;
    private int fecha;

    public Usuario(int id, String usuario, String contraseña, int fecha){
        setId(id);
        setUsuario(usuario);
        setContraseña(contraseña);
        setFecha(fecha);
    }
    public Usuario(Vector<Object> object){
        this(
                (int) object.get(0),
                (String) object.get(1),
                (String) object.get(2),
                (int) object.get(3)
        );
    }

    @Override
    public Vector<Object> toVector() {
        return new Vector<>(Arrays.asList(new Object[]{ id, usuario, fecha }));
    }

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
