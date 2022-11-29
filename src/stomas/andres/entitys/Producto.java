package stomas.andres.entitys;

import java.util.Vector;

public class Producto implements Vectorizable{
    private int id;
    private String nombre;
    private int precio;
    private int stock;

    public Producto(int id, String nombre, int precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    public Producto(Vector<Object> object){
        this(
                (int) object.get(0),
                (String) object.get(1),
                (int) object.get(2),
                (int) object.get(3)
        );
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio>0 ? precio : 0;
    }

    public int getStock() {
        return stock;
    }

    private void setStock(int stock) {
        this.stock = stock>0 ? stock : 0;
    }

    @Override
    public String toString (){
        return "| " +nombre + " | "+ String.format("$%,d", precio) + " | " + stock + " |\n";
    }

    @Override
    public Vector<Object> toVector() {
        Vector<Object> vector = new Vector<>();
        vector.add(id);
        vector.add(nombre);
        vector.add(precio);
        vector.add(stock);
        return vector;
    }
}
