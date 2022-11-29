package stomas.andres.entitys;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private List<ProductoCompra> productoCompras;
    public Compra(){
        setItems(new ArrayList<ProductoCompra>());
    }

    private void setItems(List<ProductoCompra> productoCompras) {
        this.productoCompras = productoCompras;
    }

    public void agregarProducto(Producto producto, int cantidad){
        productoCompras.add(new ProductoCompra(producto, cantidad));
    }

    public List<ProductoCompra> getProducts() {
        return productoCompras;
    }

    private int getTotal(){
        int total = 0;
        for(ProductoCompra it: this.productoCompras){
            total += it.getSubtotal();
        }
        return total;
    }
}
