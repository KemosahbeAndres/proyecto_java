package stomas.andres;

import java.util.ArrayList;
import java.util.List;

public class Orden {
    private List<Item> items;
    private int total;
    public Orden(){
        setItems(new ArrayList<Item>());
        setTotal(0);
    }

    private void setItems(List<Item> items) {
        this.items = items;
    }

    public void addProduct(Producto producto, int cantidad){
        items.add(new Item(producto, cantidad));
        setTotal(sumaItems());
    }

    public List<Item> getProducts() {
        return items;
    }

    private int sumaItems(){
        int total = 0;
        for(Item it: this.items){
            total += it.getSubtotal();
        }
        return total;
    }
    private void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}
