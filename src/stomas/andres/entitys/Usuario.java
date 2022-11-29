package stomas.andres.entitys;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Vector;

public class Usuario implements Vectorizable{
    private int id;
    private String usuario;
    private String contraseña;
    private Timestamp fecha;

    public Usuario(int id, String usuario, String contraseña, Timestamp fecha){
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
                (Timestamp) object.get(3)
        );
    }

    @Override
    public Vector<Object> toVector() {
        return new Vector<>(Arrays.asList(new Object[]{ id, usuario, contraseña, fecha }));
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

    public Timestamp getFecha() {
        return fecha;
    }

    private void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
