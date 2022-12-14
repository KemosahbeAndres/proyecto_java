package stomas.andres.entitys;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Orden implements Vectorizable{
    private int id, numero, year, id_cliente;
    private Timestamp fecha_compra;
    private double monto;
    private List<ProductoCompra> productoCompras;
    public Orden(int id, int numero, int year, int id_cliente, double monto, Timestamp fecha_compra){
        setId(id);
        setNumero(numero);
        setYear(year);
        setId_cliente(id_cliente);
        setMonto(monto);
        setFecha_compra(fecha_compra);
    }
    public Orden(Vector<Object> object){
        this(
                (int) object.get(0),
                (int) object.get(1),
                (int) object.get(2),
                (int) object.get(3),
                (double) object.get(4),
                (Timestamp) object.get(5)
        );
    }

    @Override
    public Vector<Object> toVector() {
        return new Vector<>(Arrays.asList(new Object[]{
                id, numero, year, id_cliente, monto, fecha_compra
        }));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Timestamp getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Timestamp fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public List<ProductoCompra> getProductoCompras() {
        return productoCompras;
    }

    public void setProductoCompras(List<ProductoCompra> productoCompras) {
        this.productoCompras = productoCompras;
    }
}
