package stomas.andres.entitys;

public class Producto {
    private String nombre;
    private int precio;
    private String descripcion;

    public Producto(String nombre, int precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio>0 ? precio : 0;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.trim();
    }

    @Override
    public String toString (){
        return "| " +nombre + " | "+ String.format("$%,d", precio) + " | " + descripcion + " |\n";
    }
}
