package stomas.andres.model;

import java.util.ArrayList;
import java.util.List;

public class Orden {
    private List<Item> items;
    public Orden(){
        setItems(new ArrayList<Item>());
    }

    private void setItems(List<Item> items) {
        this.items = items;
    }

    public void agregarProducto(Producto producto, int cantidad){
        items.add(new Item(producto, cantidad));
    }

    public List<Item> getProducts() {
        return items;
    }

    private int getTotal(){
        int total = 0;
        for(Item it: this.items){
            total += it.getSubtotal();
        }
        return total;
    }
}
