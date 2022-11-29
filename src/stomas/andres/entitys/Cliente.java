package stomas.andres.entitys;

import java.sql.Timestamp;
import java.util.Vector;

public class Cliente implements Vectorizable{
    private int id, telefono;
    private String nombre, run, direccion;
    private Timestamp fecha;
    public Cliente(int id, String nombre, int telefono, String direccion, String run, Timestamp fecha) {
        setId(id);
        setNombre(nombre);
        setTelefono(telefono);
        setDireccion(direccion);
        setRun(run);
        setFecha(fecha);
    }
    public Cliente(Vector<Object> object){
        this(
                (int) object.get(0),
                (String) object.get(1),
                (int) object.get(2),
                (String) object.get(3),
                (String) object.get(4),
                (Timestamp) object.get(5)
        );
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString(){
        return "";
    }

    @Override
    public Vector<Object> toVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(id);
        vector.add(nombre);
        vector.add(telefono);
        vector.add(direccion);
        vector.add(run);
        vector.add(fecha);
        return vector;
    }
}
