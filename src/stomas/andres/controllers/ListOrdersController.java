package stomas.andres.controllers;

import stomas.andres.entitys.Orden;
import stomas.andres.entitys.Producto;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.OrderModel;

import java.sql.SQLException;
import java.util.Vector;

final public class ListOrdersController {
    private OrderModel model;
    public ListOrdersController(OrderModel model){
        this.model = model;
    }
    public Vector<Vectorizable> execute() throws SQLException {
        Vector<Vectorizable> ordenes = new Vector<>();
        for(Vector<Object> vector: model.selectAll()){
            ordenes.add(new Orden(vector));
        }
        return ordenes;
    }
}
