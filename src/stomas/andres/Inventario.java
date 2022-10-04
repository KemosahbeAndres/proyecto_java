package stomas.andres;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> stock;

    public Inventario(){
        setStock(new ArrayList<Producto>());
    }
    private void setStock(List<Producto> stock) {
        this.stock = stock;
    }
    public List<Producto> getStock() {
        return stock;
    }

    public int size(){ return stock.size(); }

    public List<Producto> buscar(String nombre){
        List<Producto> lista = new ArrayList<Producto>();
        for (Producto p: stock){
            if(p.getNombre().equalsIgnoreCase(nombre.trim())){
                lista.add(p);
            }
        }
        return lista;
    }

    public List<Producto> buscar(int minimo, int maximo){
        List<Producto> lista = new ArrayList<Producto>();
        for (Producto p: stock){
            if(p.getPrecio() >= minimo && p.getPrecio() <= maximo){
                lista.add(p);
            }
        }
        return lista;
    }

}
