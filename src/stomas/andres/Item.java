package stomas.andres;

public class Item {
    private Producto producto;
    private int cantidad;

    private Item(){}
    public Item(Producto producto, int cantidad){
        setProducto(producto);
        setCantidad(cantidad);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        setCantidad(1);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

}
