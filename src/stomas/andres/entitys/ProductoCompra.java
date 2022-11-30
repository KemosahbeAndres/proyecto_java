package stomas.andres.entitys;

import java.util.Arrays;
import java.util.Vector;

public class ProductoCompra implements Vectorizable{
    private int id, id_compra;
    private double precio, cantidad, total;
    private String nombre;
    private ProductoCompra(int id, String nombre, double precio, double cantidad, double total, int id_compra) {
        this(id, nombre, precio, cantidad);
    }
    public ProductoCompra(Vector<Object> object){
        this(
                (int) object.get(0),
                (String) object.get(1),
                (double) object.get(2),
                (double) object.get(3),
                (double) object.get(4),
                (int) object.get(5)
        );
    }
    public ProductoCompra(int id, String nombre, double precio, double cantidad, int id_compra){
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setCantidad(cantidad);
        setTotal(precio * cantidad);
    }

    @Override
    public Vector<Object> toVector() {
        return new Vector<>(Arrays.asList(new Object[]{id, nombre, precio, cantidad, total, id_compra}));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}