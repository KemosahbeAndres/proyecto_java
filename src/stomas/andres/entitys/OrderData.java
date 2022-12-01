package stomas.andres.entitys;

import java.util.Vector;

public class OrderData {
    private Orden orden;
    private Cliente cliente;
    public OrderData(Orden o, Cliente c){
        orden = o;
        cliente = c;
    }

    public Orden getOrden() {
        return orden;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
