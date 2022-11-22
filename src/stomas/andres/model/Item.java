package stomas.andres.model;

public class Item {
    private Producto producto;
    private int cantidad;

    private Item() {
    }

    public Item(Producto producto, int cantidad) {
        this.setProducto(producto);
        this.setCantidad(cantidad);
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.setCantidad(1);
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return this.producto.getPrecio() * this.cantidad;
    }
}